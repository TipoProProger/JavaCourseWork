package com.tipoprocompany.api;

import com.tipoprocompany.api.entity.Okopf;
import io.quarkus.panache.common.Sort;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    @Inject EntityManager em;
    
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
}
