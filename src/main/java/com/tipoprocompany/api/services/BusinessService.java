package com.tipoprocompany.api.services;

import com.tipoprocompany.api.entity.Approvement;
import com.tipoprocompany.api.entity.Business;
import com.tipoprocompany.api.entity.BusinessExtended;
import com.tipoprocompany.api.entity.Role;
import com.tipoprocompany.api.entity.User;
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
 *
 * @author michael
 */
@ApplicationScoped
@Path("/")
public class BusinessService {
    @Inject EntityManager em;
    @Inject JsonWebToken jwt;
    
    @Transactional
    @GET
    @Path("user/business/{id}")
    @Produces("application/json")
    @RolesAllowed({"simple-user", "expert-user", "admin-user"})    
    public Business getBusiness(@PathParam("id") Long id) {
        return Business.findById(id);
    }

    private Approvement createApprovement() {
        Approvement approvement = new Approvement();
        Approvement app = Approvement.findAll(Sort.by("number").descending()).firstResult();
        approvement.number = app.number + 1;
        Role expertRole = Role.find("sysname", "expert").firstResult();
        User user = User.find("role_id", expertRole.id).firstResult();
        approvement.user = user;
        approvement.info = "";

        return approvement;
    }

    private BusinessExtended createBusinessExtended() {
        BusinessExtended businessExtended = new BusinessExtended();

        return businessExtended;
    }

    @Transactional
    @POST
    @Path("user/business/add")
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed("simple-user")
    public Business addBusiness(Business business) {
        if (business.id == null) {
            //approvement не заполняется
            Approvement approvement = createApprovement();
            em.persist(approvement);
            business.approvement = approvement;

            em.persist(business);

            BusinessExtended businessExtended = createBusinessExtended();
            em.persist(businessExtended);
            businessExtended.business = business;
            business.businessExtended = businessExtended;
        } else {
            //записи в таблицах уже существуют. Нужно их только перезаписать
            business.businessExtended.business = business;
            em.merge(business);
            System.err.println("merged");
            //em.persist(business);
            System.err.println("persisted");
        }

        return business;
    }
}
