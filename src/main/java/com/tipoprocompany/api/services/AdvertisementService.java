package com.tipoprocompany.api.services;

import com.tipoprocompany.api.entity.Advertisement;
import com.tipoprocompany.api.entity.Business;
import com.tipoprocompany.api.entity.User;
import com.tipoprocompany.api.utils.UtilityFunctions;
import io.quarkus.security.identity.SecurityIdentity;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 *
 * @author michael Класс сервиса для обработки запросов связанных с объявлениями
 * @see Advertisement
 * @version 1.0
 */
@ApplicationScoped
@Path("/")
public class AdvertisementService {

    /**
     * Статусы объявления. Возможные значения: <b>размещено</b> <b>на проверке
     * эксперта</b> <b>на модерации</b> <b>завершено</b> <b>отказано в
     * публикации</b>
     */
    private enum APPROVEMENT_STATUS {
        PLACED {
            public String toString() {
                return "размещено";
            }
        },
        ON_EXPERT_CHECK {
            public String toString() {
                return "на проверке эксперта";
            }
        },
        ON_MODERATOR_CHECK {
            public String toString() {
                return "на модерации";
            }
        },
        FINISHED {
            public String toString() {
                return "завершено";
            }
        },
        REJECTED {
            public String toString() {
                return "отказано в размещении";
            }
        }
    }

    /**
     * Менеджер для сущностей
     */
    @Inject
    EntityManager em;
    /**
     * Проверка ролей по токену
     */
    @Inject
    SecurityIdentity securityIdentity;
    /**
     * Для получения информации из jwt токена
     */
    @Inject
    JsonWebToken jwt;

    /**
     * Сервис пользователей
     */
    @Inject
    UserService userService;
    @Inject
    UtilityFunctions utilityFunctions;
    @Inject
    BusinessService businessService;
    /**
     * Endpoint обработки запроса на размещение объявления пользователем
     *
     * @param бизнес пользователя
     * @return размещенное объявление. null - при возникновении ошибки при записи
     * @see Business
     */
    @Transactional
    @POST
    @Path("user/advertisement/place")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("simple-user")
    public Advertisement userPlaceAdvertisement(Business business) {
        business = businessService.addBusinessTemplate(business);
        if (business == null) {
            System.err.println("null");
            return null;
        }
        //try to find advertisement
        Advertisement advertisement = Advertisement.findByBusinessId(business.id);
        if (advertisement == null) {
            //требуется создать новое объявление
            advertisement = new Advertisement();
            advertisement.placeDate = new Date();
            advertisement.likes = 0;
            advertisement.dislikes = 0;
            advertisement.status = APPROVEMENT_STATUS.ON_EXPERT_CHECK.toString();
            advertisement.business = business;
            advertisement.user = userService.getUser();

            em.persist(advertisement);
        } else {
            //edit
            advertisement.status = APPROVEMENT_STATUS.ON_EXPERT_CHECK.toString();
            advertisement.placeDate = new Date();

            em.merge(advertisement);
        }

        return advertisement;
    }

    /** Перезапись объявления экспертом
     * @param business новая информация о бизнесе
     * @return отредактированное объявления
     */
    @Transactional
    @POST
    @Path("expert/advertisement/place")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("expert-user")
    public Advertisement expertPlaceAdvertisement(Business business) {
        Advertisement adv = Advertisement.find("business", business).firstResult();
        
        //Нельзя редактировать уже отредактированное объявление
        if (!adv.status.equals(APPROVEMENT_STATUS.ON_EXPERT_CHECK.toString())) {
            return null;
        }
        
        adv.business = business;
        em.merge(business.approvement);

        adv.status = APPROVEMENT_STATUS.ON_MODERATOR_CHECK.toString();

        em.merge(adv);

        return adv;
    }

    /** Возвращает список объявлений конкртеного пользователя
     * @return список объявлений конкртеного пользователя
     */
    @Transactional
    @POST
    @Path("user/advertisements")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("simple-user")
    public List<Advertisement> getUserAdvertisements() {
        User user = userService.getUser();
        return Advertisement.listUserResolved(user);
    }

    /** Возвращает список объявлений для проверки экспертом
     * @return список объявлений со статусом "на проверке эксперта"
     */
    @Transactional
    @GET
    @Path("expert/advertisements")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("expert-user")
    public List<Advertisement> getExpertAdvertisement() {
        return Advertisement.list("status", APPROVEMENT_STATUS.ON_EXPERT_CHECK.toString());
    }

    /** Возвращает список объявлений для проверки модератором
     * @return список объявлений со статусом "на модерации"
     */
    @Transactional
    @GET
    @Path("admin/advertisements")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("admin-user")
    public List<Advertisement> getModeratorAdvertisement() {
        return Advertisement.list("status", APPROVEMENT_STATUS.ON_MODERATOR_CHECK.toString());
    }
   
    /** Подтверждение публикации выбранного бизнеса
     * @param id id бизнеса для изменений
     * @return отредактированно объявление. null - при некорректной записи
     */
    @Transactional
    @GET
    @Path("/admin/advertisement/accept/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("admin-user")
    public Advertisement acceptAdvertisement(@PathParam("id") Long businessId) {
        Advertisement adv = Advertisement.findByBusinessId(businessId);
        
        //Нельзя редактировать уже отредактированное объявление
        if (!adv.status.equals(APPROVEMENT_STATUS.ON_MODERATOR_CHECK.toString())) {
            return null;
        }
        
        adv.status = APPROVEMENT_STATUS.PLACED.toString();

        adv.persist();

        return adv;
    }

    /** Отказ в публикации выбранного бизнеса
     * @param id id бизнеса для изменений
     * @return отредактированно объявление. null - при некорректной записи
     */
    @Transactional
    @GET
    @Path("/admin/advertisement/reject/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("admin-user")
    public Advertisement rejectAdvertisement(@PathParam("id") Long businessId) {
        Advertisement adv = Advertisement.findByBusinessId(businessId);
        
        //Нельзя редактировать уже отредактированное объявление
        if (!adv.status.equals(APPROVEMENT_STATUS.ON_MODERATOR_CHECK.toString())) {
            return null;
        }
        adv.status = APPROVEMENT_STATUS.REJECTED.toString();

        adv.persist();

        return adv;
    }

    /** Возвращает список объявлений со статусом "размещено"
     * @return список объявлений со статусом "размещено"
     */
    @Transactional
    @GET
    @Path("advertisements")
    @Produces("application/json")
    @RolesAllowed({"simple-user", "expert-user", "admin-user"})
    public List<Advertisement> getUserAdvertisement() {
        return Advertisement.findResolved();
    }

    /** Изменение статуса объявления на "закрыто"
     * @param id id бизнеса для закрытия
     * @return отредактированно объявление. null - при некорректной записи
     */
    @Transactional
    @GET
    @Path("user/finish/advertisement/{id}")
    @Produces("application/json")
    @RolesAllowed("simple-user")
    public Advertisement finishAdvertisement(@PathParam("id") Long id) {
        Advertisement advertisement = Advertisement.findByBusinessId(id);
        System.err.println(advertisement);
        User recievedUser = userService.getUser();
        System.err.println(recievedUser);
        User realUser = advertisement.user;
        System.err.println(realUser);
        if (recievedUser.id != realUser.id) {
            return null;
        }
        
        advertisement.status = APPROVEMENT_STATUS.FINISHED.toString();

        em.persist(advertisement);
        
        return advertisement;
    }
}
