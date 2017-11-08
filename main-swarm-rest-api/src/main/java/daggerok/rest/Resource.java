package daggerok.rest;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@Stateless
@Produces(APPLICATION_JSON)
public class Resource {

  private static final Logger log = Logger.getLogger(Resource.class.getName());

  @GET
  @Path("/")
  public Response api() {
    /*
    asList(
        Json.createObjectBuilder()
            .add("GET", "/api")
            .build(),
        Json.createObjectBuilder()
            .add("GET", "/")
            .build()
    )
    */

    return Response.ok(Json.createObjectBuilder()
                           .add("api", Json.createArrayBuilder()
                                           .add(Json.createObjectBuilder()
                                                    .add("GET", "/api/jsonp/json-pointer")
                                                    .build())
                                           .add(Json.createObjectBuilder()
                                                    .add("GET", "/api/{name}")
                                                    .build())
                                           .add(Json.createObjectBuilder()
                                                    .add("GET", "/api")
                                                    .build())
                                           .add(Json.createObjectBuilder()
                                                    .add("GET", "/")
                                                    .build())
                                           .build())
                           .build())
                   .build();
  }

  @GET
  @Path("/api")
  public Response hiAll() {

    return Response.ok(Json.createObjectBuilder()
                           .add("message", "hola!")
                           .build())
                   .build();
  }

  @GET
  @Path("/api/{name}")
  public Response getMessage(@PathParam("name") final String name) {

    return Response.ok(Json.createObjectBuilder()
                           .add("message", format("hey, %s!", name))
                           .build())
                   .build();
  }

  @POST
  @Path("/api/cors")
  @Consumes(APPLICATION_JSON)
  public Response postMessage(final HashMap requestBody) {

    log.info(requestBody.toString());

    return Response.accepted()
                   .build();
  }

  @POST
  @Path("/api/jsonp/json-pointer")
  @Consumes(APPLICATION_JSON)
  public Response jsonPointer(final HashMap<String, Map<String, String>> requestBody) {

    final Map<String, String> users = requestBody.getOrDefault("users", new HashMap<>());

    if (users.isEmpty()) return Response.status(Response.Status.BAD_REQUEST)
                                        .build();

    final JsonArrayBuilder builder = Json.createArrayBuilder();
    users.forEach((k, v) -> builder.add(Json.createObjectBuilder()
                                            .add(k, v)
                                            .build()));
    final JsonArray in = builder.build();
//    log.info("in: " + in);
//    final JsonPointer jsonPointer = Json.createPointer("/0/users/username");
//    final JsonArray out = jsonPointer.replace(in, Json.createValue("Fax!"));
//    log.info("out: " + out);
//
//    return Response.ok(out)
    return Response.ok(in)
                   .build();
  }
}
