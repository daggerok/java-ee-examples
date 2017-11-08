package daggerok.rest;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.logging.Logger;

import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@Stateless
@Produces(APPLICATION_JSON)
public class Resource {

  private static final Logger log = Logger.getLogger(Resource.class.getName());

  @GET
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
  @Path("api")
  public Response hiAll() {

    return Response.ok(Json.createObjectBuilder()
                           .add("message", "hola!")
                           .build())
                   .build();
  }

  @GET
  @Path("api/{name}")
  public Response getMessage(@PathParam("name") final String name) {

    return Response.ok(Json.createObjectBuilder()
                           .add("message", format("hey, %s!", name))
                           .build())
                   .build();
  }

  @POST
  @Path("api/cors")
  @Consumes(APPLICATION_JSON)
  public Response postMessage(final HashMap requestBody) {

    log.info(requestBody.toString());

    return Response.accepted()
                   .build();
  }
}
