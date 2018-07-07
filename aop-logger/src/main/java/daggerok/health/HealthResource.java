package daggerok.health;

import daggerok.app.SomeBusinessLogic;
import io.vavr.collection.HashMap;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.validation.constraints.Size;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

//tag::content[]
@Stateless
@Path("api")
@Produces(APPLICATION_JSON)
public class HealthResource {

  static final Map<String, String> pingPongMap
      = HashMap.of("ping", "pong",
                   "pong", "ping")
               .toJavaMap();

  @Inject
  SomeBusinessLogic logic;
  //end::content[]

  @Context
  UriInfo uriInfo;

  String linkTo(@Size(min = 1) final String... args) {
    return args.length > 1
        ? uriInfo.getBaseUriBuilder()
                 .path(HealthResource.class)
                 .path(HealthResource.class, args[0])
                 .build(args[1])
                 .toString()
        : uriInfo.getBaseUriBuilder()
                 .path(HealthResource.class)
                 .path(HealthResource.class, args[0])
                 .build()
                 .toString();
  }

  @GET
  @Path("")
  public Response index() {
    return Response.ok(Json.createObjectBuilder()
                           .add("health", linkTo("health"))
                           .add("ping", linkTo("pingPong", "ping"))
                           .add("pong", linkTo("pingPong", "pong"))
                           .build())
                   .build();
  }

  @GET
  @Path("health")
  public Response health() {
    return Response.ok(Json.createObjectBuilder()
                           .add("status", "UP")
                           .build())
                   .build();
  }
  //tag::content[]

  @GET
  @Path("{path: (ping|pong)}")
  public Response pingPong(@PathParam("path") final String path) {
    logic.doSomething();
    return Response.ok(Json.createObjectBuilder()
                           .add("status", pingPongMap.get(path))
                           .build())
                   .build();
  }
}
//end::content[]
