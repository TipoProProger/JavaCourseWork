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
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 *
 * @author michael
 */
@ApplicationScoped
@Path("/")
public class AdvertisementService {

    //На проверке эксперта, на модерации, размещено,отказано в размещении, завершено        
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

    @Inject
    EntityManager em;
    @Inject
    SecurityIdentity securityIdentity;
    @Inject
    JsonWebToken jwt;

    @Inject
    UserService userService;

    private Approvement createApprovement() {

        Approvement approvement = new Approvement();
        Approvement app = Approvement.findAll(Sort.by("number").descending()).firstResult();
        approvement.number = app.number + 1;

        Role expertRole = Role.find("sysname", "expert-user").firstResult();
        User user = User.find("role_id", expertRole.id).firstResult();
        approvement.user = user;
        approvement.info = "";
        approvement.scanCourtApr = 0;
        approvement.scanTaxsApr = 0;

        return approvement;
    }

    private BusinessExtended createBusinessExtended() {
        BusinessExtended businessExtended = new BusinessExtended();

        return businessExtended;
    }

    @Transactional
    @POST
    @Path("user/advertisement/place")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("simple-user")
    public Advertisement userPlaceAdvertisement(Business business) {
        System.err.println(business);

        if (business.id == null) {
            //need to add business first
            System.err.println("create new");
            Approvement approvement = createApprovement();
            em.persist(approvement);
            System.err.println("approvement persisted");
            business.approvement = approvement;

            em.persist(business);
            System.err.println("business persisted");

            BusinessExtended businessExtended = business.businessExtended;
            businessExtended.business = business;
            em.persist(businessExtended);
            
            business.businessExtended = businessExtended;
            em.persist(business);
            System.err.println("businessExtended persisted");
            //businessExtended.business = business;
            //business.businessExtended = businessExtended;
        } else {
            //записи в таблицах уже существуют. Нужно их только перезаписать
            business.businessExtended.business = business;
            em.merge(business);
        }

        System.err.println(business);
        System.err.println(business.businessExtended.business);
        System.err.println(business.businessExtended);
        
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

    @Transactional
    @GET
    @Path("expert/advertisements")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("expert-user")
    public List<Advertisement> getExpertAdvertisement() {
        return Advertisement.list("status", APPROVEMENT_STATUS.ON_EXPERT_CHECK.toString());
    }

    @Transactional
    @GET
    @Path("admin/advertisements")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("admin-user")
    public List<Advertisement> getModeratorAdvertisement() {
        return Advertisement.list("status", APPROVEMENT_STATUS.ON_MODERATOR_CHECK.toString());
    }

    @Transactional
    @GET
    @Path("admin/advertisement/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed({"simple-user", "expert-user", "admin-user"})
    public Advertisement getAdvertisementById(@PathParam("id") Long id) {
        return Advertisement.findById(id);
    }

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

}
