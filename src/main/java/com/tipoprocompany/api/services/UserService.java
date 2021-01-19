package com.tipoprocompany.api.services;

import com.tipoprocompany.api.entity.Role;
import com.tipoprocompany.api.entity.User;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 *
 * @author michael
 * @version 1.0
 * Класс сервиса пользователей
 */
@ApplicationScoped
@Path("/")
public class UserService {
    @Inject EntityManager em;
    @Inject JsonWebToken jwt;
    
    /** Получает пользователя из токена. Если его еще нет в базе, то добавляет
     * @return пользователь, согласно токену
     */
    @Transactional
    @RolesAllowed({"simple-user","expert-user","admin-user"})
    public User getUser() {
        //email также уникальное поле. Получим его из токена
        String email = jwt.getClaim("email");
        User user = User.findByEmail(email);        
        if (user == null) {
            //Если пользователя еще нет, то создадим его
            user = new User();
            user.FIO = jwt.getClaim("familyName") + " " + jwt.getClaim("givenName");
            user.email = email;
            user.post = "пользователь";
            user.telephoneNumber = "none";///FIXME не добавлен в keycloak
            
            String buffer = jwt.getClaim("user-role-name").toString();
            //Поиск роли пользователя
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
            //если пользователь уже существует, то возвращаем его
            return user;
        }        
    }
    
    /** ВОзвращает пользователя из базы по email     
     * @return пользователь из базы согласно email из токена
     */
    @Transactional
    @GET
    @Path("users")
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed({"simple-user", "expert-user", "admin-user"})
    public User checkUser() {
        return getUser();
    }
}
