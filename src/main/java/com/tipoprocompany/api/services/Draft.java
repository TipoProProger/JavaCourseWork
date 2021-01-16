package com.tipoprocompany.api.services;

import com.tipoprocompany.api.entity.Advertisement;
import com.tipoprocompany.api.entity.Approvement;
import com.tipoprocompany.api.entity.Business;
import com.tipoprocompany.api.entity.BusinessExtended;
import com.tipoprocompany.api.entity.Okopf;
import com.tipoprocompany.api.entity.Role;
import com.tipoprocompany.api.entity.User;
import io.quarkus.panache.common.Sort;
import java.util.List;
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

/**
 *
 * @author michael
 */
@Path("/")
@Produces("application/json")
@Consumes("application/json")
public class Draft {

    @Inject
    EntityManager em;
    
    @GET
    @Path("test")
    public Response helloWorld() {
        return Response.ok("HelloWorld").status(200).build();
    }

    @GET
    @Path("testCombolist")
    public List<Okopf> getOkopf() {
        return Okopf.listAll(Sort.by("id"));
    }

    @Transactional
    @GET
    @Path("test_business_info/{id}")
    @Produces("application/json")
    public Business getBusiness(@PathParam("id") Long id) {
        System.err.println("sended");
        //return BusinessMapping.map(Business.findById(id));
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
    @Path("test_business_info/add")
    @Produces("application/json")
    @Consumes("application/json")
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
    
    @Transactional
    @GET
    @Path("advertisements")
    @Produces("application/json")
    public List<Advertisement> getUserAdvertisement() {
        //На проверке эксперта, на модерации, размещено,отказано в размещении, завершено
        return Advertisement.findResolved();
    }
}
