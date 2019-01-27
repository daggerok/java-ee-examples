package com.github.daggerok.health;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Stateless
@Path("v1")
@Produces(APPLICATION_JSON)
public class HealthResource {

  @Context
  UriInfo uriInfo;

  @GET
  @Path("")
  public Response index() {
    return Response.ok(Json.createObjectBuilder()
                           .add("health", uriInfo.getBaseUriBuilder()
                                                 .path(HealthResource.class)
                                                 .path(HealthResource.class, "health")
                                                 .build("health")
                                                 .toString())
                           .build())
                   .build();
  }

  @GET
  @Path("{path: (health|ping)}")
  public Response health(@PathParam("path") final String path) {
    return Response.ok(Json.createObjectBuilder()
                           .add("status", "UP")
                           .build())
                   .build();
  }
}
