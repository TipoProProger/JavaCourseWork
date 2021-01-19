package com.tipoprocompany.api.services;

import com.tipoprocompany.api.entity.Advertisement;
import com.tipoprocompany.api.entity.Approvement;
import com.tipoprocompany.api.entity.Business;
import com.tipoprocompany.api.entity.BusinessExtended;
import com.tipoprocompany.api.entity.Okfs;
import com.tipoprocompany.api.entity.Okopf;
import com.tipoprocompany.api.entity.Role;
import com.tipoprocompany.api.entity.User;
import com.tipoprocompany.api.utils.UtilityFunctions;
import io.quarkus.panache.common.Sort;
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
 * Класс сервиса для обработки запросов бизнеса
 *
 * @author michael
 * @version 1.0
 */
@ApplicationScoped
@Path("/")
public class BusinessService {

    @Inject
    EntityManager em;
    @Inject
    JsonWebToken jwt;
    @Inject
    UtilityFunctions utilityFunctions;
    @Inject
    UserService userService;

    /**
     * Возвращает бизнес по его id
     *
     * @param id id бизнеса
     * @return бизнес по id
     */
    @Transactional
    @GET
    @Path("user/business/{id}")
    @Produces("application/json")
    @RolesAllowed({"simple-user", "expert-user", "admin-user"})
    public Business getBusiness(@PathParam("id") Long id) {
        //По идее нужно слать что-то лучше чем null, если не найдем
        return Business.findById(id);
    }

    /**
     * Функция для фиксации бизнеса и прилегающих к нему таблиц
     *
     * @param business бизнес для фиксации
     * @return отредактированный бизнес. null - при ошибке
     */
    @Transactional
    @RolesAllowed("simple-user")
    public Business addBusinessTemplate(Business business) {
        Okopf possibleOkopf = Okopf.find("number", business.okopf.substring(0, 1)).firstResult();
        if (possibleOkopf == null) {
            System.err.println("okopf null");
            return null;
        }
        Okfs possibleOkfs = Okfs.find("number", business.okfs.substring(0, 2)).firstResult();
        if (possibleOkfs == null) {
            System.err.println("okfs null");
            return null;
        }

        //Проверяем существует ли уже присланный бизнес
        if (business.id == null) {
            //Добавляем новый бизнесс

            //Создаем пустое подтверждение эксперта
            Approvement approvement = utilityFunctions.createApprovement();
            em.persist(approvement);
            business.approvement = approvement;

            //Сохраняем новый бизнес
            em.persist(business);

            //Создаем новое расширения для бизнеса
            BusinessExtended businessExtended = business.businessExtended;
            businessExtended.business = business;
            em.persist(businessExtended);

            //Соединяем разные блоки
            business.businessExtended = businessExtended;
            em.persist(business);
        } else {
            //Нужно проверить имеет ли пользователь право его менять!        
            User recievedUser = userService.getUser();
            User realUser = Advertisement.findByBusinessId(business.id).user;

            if (recievedUser.id != realUser.id) {
                System.err.println(recievedUser);
                System.err.println(realUser);
                ///FIXME
                //Наверное, есть решение лучше
                //С другой стороны, единственный вариант, когда такое происходит
                //- нас пытаются наеб... Что в таком случае мы должны отправлять?
                return null;
            }

            //Соединяем бизнес с его расширением и фиксируем
            business.businessExtended.business = business;
            em.merge(business);
        }

        return business;
    }

    /**
     * Endpoint для фиксации бизнеса
     *
     * @param business бизнес для фиксации
     * @return отредактированный бизнес. null - при ошибке
     */
    @Transactional
    @POST
    @Path("user/business/add")
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed("simple-user")
    public Business addBusiness(Business business) {
        return addBusinessTemplate(business);
    }
}
