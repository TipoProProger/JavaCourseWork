package com.tipoprocompany.api.services;

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
import org.hibernate.criterion.CriteriaQuery;

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
    public Business getBusiness(@PathParam("id") Long id) {
        System.err.println("sended");
        //return BusinessMapping.map(Business.findById(id));
        return Business.findById(id);
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
        
        //approvement will be null
        ///add
        //System.err.println("entered");
        /*
        if (businessDTO.id == null) {
            //System.err.println("in if");
            Approvement approvement = new Approvement();
            Approvement app = Approvement.findAll(Sort.by("number").descending()).firstResult();
            approvement.number = app.number + 1;
            Role expertRole = Role.find("sysname", "expert").firstResult();
            User user = User.find("role_id", expertRole.id).firstResult();///fucking shit
            approvement.user = user;
            approvement.info = "";
            em.persist(approvement);
            //System.err.println("persist approvement");
            
            //BusinessExtended businessExtended = new BusinessExtended();            
            //em.persist(businessExtended);
            //System.err.println("persiste business extended");
            
            businessDTO.approvementId = approvement.id;
            //businessDTO.businessExtendedId = businessExtended.id;
        }

        //System.err.println("start reverse mapping");
        Business business = BusinessMapping.reverseMap(businessDTO);
        //System.err.println(business);
        //System.err.println("reverse mapping end");
        
        
        //business.approvement = approvement;
        //System.err.println(business.toString());       
        //System.err.println(business.id);
        em.persist(business);
        System.err.println("business persisted");
        return BusinessMapping.map(business);
        */
    }
}
