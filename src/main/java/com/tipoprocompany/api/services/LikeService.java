package com.tipoprocompany.api.services;

import com.tipoprocompany.api.entity.Advertisement;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Класс сервиса для обработки событий лайков
 * @author michael
 * @version 1.0
 */
@Path("/")
public class LikeService {

    @Inject
    EntityManager em;

    /**
     * Ставит лайк необходимому объявлению
     *
     * @param id id бизнеса, которому нужно поставить лайк
     * @return 200 - ok; 500 - error
     */
    @Transactional
    @POST
    @Path("likes/like/{id}")
    public Response addLike(@PathParam("id") Long id) {
        Advertisement advertisement = Advertisement.findByBusinessId(id);

        if (advertisement != null) {
            advertisement.likes++;
            em.persist(advertisement);

            return Response.ok().build();
        }

        //not the best decision. How to make it better?
        return Response.serverError().build();
    }

    /**
     * Ставит дизлайк необходимому объявлению
     *
     * @param id id бизнеса, которому нужно поставить лайк
     * @return 200 - ok; 500 - error
     */
    @Transactional
    @POST
    @Path("likes/dislike/{id}")
    public Response addDislike(@PathParam("id") Long id) {
        Advertisement advertisement = Advertisement.findByBusinessId(id);
        if (advertisement != null) {
            advertisement.dislikes++;
            em.persist(advertisement);

            return Response.ok().build();
        }

        //not the best decision. How to make it better?
        return Response.serverError().build();
    }
}
