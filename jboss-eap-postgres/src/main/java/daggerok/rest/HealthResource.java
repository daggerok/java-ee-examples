package daggerok.rest;

import io.vavr.collection.HashMap;
import io.vavr.control.Try;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@ApplicationScoped
@Produces(APPLICATION_JSON)
public class HealthResource {

  private static final Map<String, String> variants = HashMap.of("ping", "pong",
                                                                 "pong", "ping")
                                                             .toJavaMap();
  @Context
  UriInfo uriInfo;

  @GET
  @Path("{path: (health|ping|pong)}")
  public Response health(@PathParam("path") final String path) {
    final String status = Try.of(() -> variants.getOrDefault(path, "UP"))
                             .getOrElseGet(throwable -> "DOWN");
    return Response.ok(Json.createObjectBuilder()
                           .add("status", status)
                           .build()
                           .toString())
                   .build();
  }
}
