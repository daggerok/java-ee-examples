package daggerok.app;

import io.vavr.collection.HashMap;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Stateless
@Path("")
@Produces(APPLICATION_JSON)
public class AppResource {

  static final Map<String, String> pingPongMap = HashMap
      .of("ping", "pong", "pong", "ping").toJavaMap();

  @GET
  @Path("{path: ^(ping|pong)}")
  public Response pingPong(@PathParam("path") final String path) {
    return Response.ok(Json.createObjectBuilder()
                           .add("message", pingPongMap.get(path))
                           .build())
                   .build();
  }
}
