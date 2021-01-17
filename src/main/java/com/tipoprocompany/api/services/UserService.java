package com.tipoprocompany.api.services;

import com.tipoprocompany.api.entity.Role;
import com.tipoprocompany.api.entity.User;
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
public class UserService {
    @Inject EntityManager em;
    @Inject JsonWebToken jwt;
    
    @Transactional
    public User getUser() {
        System.err.println(jwt);
        String email = jwt.getClaim("email");
        User user = User.findByEmail(email);
        if (user == null) {
            //should create new
            user = new User();
            user.FIO = jwt.getClaim("familyName") + " " + jwt.getClaim("givenName");
            user.email = email;
            user.post = "пользователь";
            user.telephoneNumber = "none";///FIXME
            
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
    public User checkUser(User user) {
        System.err.println(user);
        System.err.println(user.FIO);
        System.err.println(user.email);        
        System.err.println(user.telephoneNumber);
        User u = User.findByEmail(user.email);
        System.err.println(u);
        if (u == null) {
            //dont exsit in out db
            u = new User();
            u.FIO = user.FIO;
            u.advertisements = null;
            u.approvements = null;
            u.email = user.email;
            u.post = user.post;
            Role r = Role.findBySysname("simple_user");
            u.telephoneNumber = user.telephoneNumber;

            em.persist(u);
        }

        return u;
    }
}
