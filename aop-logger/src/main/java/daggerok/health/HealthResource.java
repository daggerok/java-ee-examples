package daggerok.health;

import daggerok.app.AppResource;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Stateless
@Path("api")
@Produces(APPLICATION_JSON)
public class HealthResource {

  @Context
  UriInfo uriInfo;

  @GET
  @Path("")
  public Response index() {
    return Response.ok(Json.createObjectBuilder()
                           .add("ping", uriInfo.getBaseUriBuilder()
                                                .path(AppResource.class)
                                                .path(AppResource.class, "pingPong")
                                                .build("ping")
                                                .toString())
                           .add("pong", uriInfo.getBaseUriBuilder()
                                                .path(AppResource.class)
                                                .path(AppResource.class, "pingPong")
                                                .build("pong")
                                                .toString())
                           .add("health", uriInfo.getBaseUriBuilder()
                                                .path(HealthResource.class)
                                                .path(HealthResource.class, "health")
                                                .build()
                                                .toString())
                           .add("_self", uriInfo.getBaseUriBuilder()
                                                .path(HealthResource.class)
                                                .path(HealthResource.class, "index")
                                                .build()
                                                .toString())
                           .build())
                   .build();
  }

  @GET
  @Path("health")
  public Response health() {
    return Response.ok(Json.createObjectBuilder()
                           .add("status", "UP")
                           .add("_self", uriInfo.getBaseUriBuilder()
                                                .path(HealthResource.class)
                                                .path(HealthResource.class, "health")
                                                .build()
                                                .toString())
                           .build())
                   .build();
  }
}
