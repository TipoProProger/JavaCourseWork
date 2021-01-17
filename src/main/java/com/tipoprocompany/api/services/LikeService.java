package com.tipoprocompany.api.services;

/**
 *
 * @author michael
 */

import com.tipoprocompany.api.entity.Advertisement;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/")
public class LikeService {
    @Inject EntityManager em;
    
    @Transactional
    @POST
    @Path("likes/like/{id}")
    public Response addLike(@PathParam("id") Long id) {
        Advertisement advertisement = Advertisement.findByBusinessId(id);
        advertisement.likes++;
        em.persist(advertisement);
        
        return Response.ok().build();
    }
    
    @Transactional
    @POST
    @Path("likes/dislike/{id}")
    public Response addDislike(@PathParam("id") Long id) {
        Advertisement advertisement = Advertisement.findByBusinessId(id);
        advertisement.dislikes++;
        em.persist(advertisement);
        
        return Response.ok().build();
    }
}
