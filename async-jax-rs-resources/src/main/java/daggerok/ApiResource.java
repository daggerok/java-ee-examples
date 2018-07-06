package daggerok;

import daggerok.health.HealthResource;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@Stateless
@Produces(APPLICATION_JSON)
public class ApiResource extends Application {

  @GET
  @Path("")
  public Response api(@Context final UriInfo uriInfo) {

    return Response.ok(Json.createArrayBuilder()
                           .add(uriInfo.getBaseUriBuilder()
                                       .path(AppResource.class)
                                       .path(AppResource.class, "getAll")
                                       .build()
                                       .toString())
                           .add(uriInfo.getBaseUriBuilder()
                                       .path(AppResource.class)
                                       .path(AppResource.class, "get")
                                       .build("$id")
                                       .toString())
                           .add(uriInfo.getBaseUriBuilder()
                                       .path(AppResource.class)
                                       .path(AppResource.class, "post")
                                       .build()
                                       .toString())
                           .add(uriInfo.getBaseUriBuilder()
                                       .path(ApiResource.class)
                                       .path(ApiResource.class, "api")
                                       .build()
                                       .toString())
                           .add(uriInfo.getBaseUriBuilder()
                                       .path(HealthResource.class)
                                       .path(HealthResource.class, "health")
                                       .build()
                                       .toString())
                           .build())
                   .build();
  }
}
