package daggerok.rest;

import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/")
@Produces(APPLICATION_JSON)
public class Api {

  @GET
  public Response getApi() {

    return Response.ok(Json.createArrayBuilder()
                           .add(Json.createObjectBuilder()
                                    .add("Address", "http://localhost:8000/api/v1/ws")
                                    .build())
                           .add(Json.createObjectBuilder()
                                    .add("WSDL", "http://localhost:8000/api/v1/ws?wsdl")
                                    .build())
                           .build())
                   .build();
  }
}
