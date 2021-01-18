package com.tipoprocompany.api.services;

import com.tipoprocompany.api.entity.Advertisement;
import com.tipoprocompany.api.entity.Approvement;
import com.tipoprocompany.api.entity.Business;
import com.tipoprocompany.api.entity.BusinessExtended;
import com.tipoprocompany.api.entity.Role;
import com.tipoprocompany.api.entity.User;
import io.quarkus.panache.common.Sort;
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
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 *
 * @author michael
 * Класс сервиса для обработки запросов связанных с объявлениями
 * @see Advertisement
 * @version 1.0
 */
@ApplicationScoped
@Path("/")
public class AdvertisementService {

    /** Статусы объявления. Возможные значения: <b>размещено</b> <b>на проверке эксперта</b> <b>на модерации</b> <b>завершено</b> <b>отказано в публикации</b> */        
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

    /** Менеджер для сущностей */
    @Inject
    EntityManager em;
    /** Проверка ролей по токену */
    @Inject
    SecurityIdentity securityIdentity;
    /** Для получения информации из jwt токена */
    @Inject
    JsonWebToken jwt;

    /** Сервис пользователей */
    @Inject
    UserService userService;

    /** Внутрення функция класса для добавления подтверждения эксперта по умолчанию 
     * @return заполненное по умолчанию подтверждение эксперта 
     * @see Approvement
     */
    private Approvement createApprovement() {
        Approvement approvement = new Approvement();
        //Получение последнего номера подтверждения эксперта
        Approvement app = Approvement.findAll(Sort.by("number").descending()).firstResult();
        //Установка новому подтверждению следующего номера
        approvement.number = app.number + 1;

        //Поиск пользователя-эксперта. Подходит любой
        Role expertRole = Role.find("sysname", "expert-user").firstResult();
        User user = User.find("role_id", expertRole.id).firstResult();
        approvement.user = user;
        //Заполнение обязательных полей значениями по умолчанию
        approvement.info = "";
        approvement.scanCourtApr = 0;
        approvement.scanTaxsApr = 0;

        return approvement;
    }

    /** Endpoint обработки запроса на размещение объявления пользователем
     * @param бизнес пользователя
     * @return размещенное объявление
     * @see Business
     */
    @Transactional
    @POST
    @Path("user/advertisement/place")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("simple-user")
    public Advertisement userPlaceAdvertisement(Business business) {
        //Проверяем существует ли уже присланный бизнес
        if (business.id == null) {
            //Добавляем новый бизнесс
            
            //Создаем пустое подтверждение эксперта
            Approvement approvement = createApprovement();
            em.persist(approvement);
            business.approvement = approvement;

            //Сохраняем новый бизнес
            em.persist(business);
            System.err.println("business persisted");

            //Создаем новое расширения для бизнеса
            BusinessExtended businessExtended = business.businessExtended;
            businessExtended.business = business;
            em.persist(businessExtended);
            
            //Соединяем разные блоки
            business.businessExtended = businessExtended;
            em.persist(business);
        } else {
            //Нужно проверить имеет ли пользователь право его менять!
            //Соединяем бизнес с его расширением и фиксируем
            business.businessExtended.business = business;
            em.merge(business);
        }

        //try to find advertisement
        Advertisement advertisement = Advertisement.findByBusinessId(business.id);
        if (advertisement == null) {
            //creatse new
            System.err.println("create new");
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
            System.err.println("edit");
            advertisement.status = APPROVEMENT_STATUS.ON_EXPERT_CHECK.toString();

            em.merge(advertisement);
        }

        return advertisement;
    }

    /**
     * @param
     * @return 
     */
    @Transactional
    @POST
    @Path("expert/advertisement/place")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("expert-user")
    public Advertisement expertPlaceAdvertisement(Business business) {
        Advertisement adv = Advertisement.find("business", business).firstResult();
        adv.business = business;
        em.merge(business.approvement);

        adv.status = APPROVEMENT_STATUS.ON_MODERATOR_CHECK.toString();

        em.merge(adv);

        return adv;
    }
    
    /**
     * @param
     * @return 
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

    /**
     * @param
     * @return 
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

    /**
     * @param
     * @return 
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

    /**
     * @param
     * @return 
     */
    @Transactional
    @GET
    @Path("admin/advertisement/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed({"simple-user", "expert-user", "admin-user"})
    public Advertisement getAdvertisementById(@PathParam("id") Long id) {
        return Advertisement.findById(id);
    }

    /**
     * @param
     * @return 
     */
    @Transactional
    @GET
    @Path("/admin/advertisement/accept/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("admin-user")
    public Advertisement acceptAdvertisement(@PathParam("id") Long businessId) {
        System.err.println("accepted");
        Advertisement adv = Advertisement.findByBusinessId(businessId);
        adv.status = APPROVEMENT_STATUS.PLACED.toString();

        adv.persist();

        return adv;
    }

    /**
     * @param
     * @return 
     */
    @Transactional
    @GET
    @Path("/admin/advertisement/reject/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("admin-user")
    public Advertisement rejectAdvertisement(@PathParam("id") Long businessId) {
        System.err.println("rejected");
        Advertisement adv = Advertisement.findByBusinessId(businessId);
        adv.status = APPROVEMENT_STATUS.REJECTED.toString();

        adv.persist();

        return adv;
    }

    /**
     * @param
     * @return 
     */
    @Transactional
    @GET
    @Path("advertisements")
    @Produces("application/json")
    @RolesAllowed({"simple-user", "expert-user", "admin-user"})
    public List<Advertisement> getUserAdvertisement() {
        System.err.println(jwt);
        System.err.println(jwt.getClaim("email").toString());
        return Advertisement.findResolved();
    }

    /**
     * @param
     * @return 
     */
    @Transactional
    @POST
    @Path("user/finish/advertisement/{id}")
    @Produces("application/json")
    @RolesAllowed("simple-user")
    public Response finishAdvertisement(@PathParam("id") Long id) {
        Advertisement advertisement = Advertisement.findByBusinessId(id);
        
        advertisement.status = APPROVEMENT_STATUS.FINISHED.toString();
        
        return Response.ok().build();
    }
}
