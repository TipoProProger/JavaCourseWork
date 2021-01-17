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

/**
 *
 * @author michael
 */
@Path("/")
@Produces("application/json")
@Consumes("application/json")
@ApplicationScoped
public class Draft {
    
}
