package com.tipoprocompany.api.services;

import com.tipoprocompany.api.entity.Role;
import com.tipoprocompany.api.entity.User;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 *
 * @author michael
 */
@ApplicationScoped
@Path("/")
public class UserService {
    @Inject EntityManager em;
    @Inject JsonWebToken jwt;
    
    @Transactional
    @RolesAllowed({"simple-user","expert-user","admin-user"})
    public User getUser() {
        String email = jwt.getClaim("email");
        User user = User.findByEmail(email);
        if (user == null) {
            //should create new
            user = new User();
            user.FIO = jwt.getClaim("familyName") + " " + jwt.getClaim("givenName");
            user.email = email;
            user.post = "пользователь";
            user.telephoneNumber = "none";///FIXME
            
            String buffer = jwt.getClaim("user-role-name").toString();            
            Role r = null;
            if (buffer.contains("simple-user")) {
                r = Role.findBySysname("simple-user");
            } else if (buffer.contains("expert-user")) {
                r = Role.findBySysname("expert-user");
            } else if (buffer.contains("admin-user")) {
                r = Role.findBySysname("admin-user");
            }
            
            user.role = r;
            
            em.persist(user);
            return user;
        } else {
            //already exists
            return user;
        }        
    }
    
    @Transactional
    @POST
    @Path("users")
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed({"simple-user", "expert-user", "admin-user"})
    public User checkUser(User user) {
        User u = User.findByEmail(user.email);        
        if (u == null) {
            //dont exsit in out db
            System.err.println("don't exists");
            u = getUser();                       
        }

        return u;
    }
}
