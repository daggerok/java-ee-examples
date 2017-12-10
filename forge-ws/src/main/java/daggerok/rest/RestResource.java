package daggerok.rest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.net.MalformedURLException;

import static java.lang.String.format;

@Path("api")
@Produces(MediaType.APPLICATION_JSON)
public class RestResource {

  @Context
  UriInfo uriInfo;

  @GET
  @Path("")
  public JsonObject api() throws MalformedURLException {
    return Json.createObjectBuilder()
               .add("api", format("%s", uriInfo.getBaseUriBuilder()
                                               .path(RestResource.class)
                                               .path(RestResource.class, "api")
                                               .build().toURL().toString()))
               .build();
  }
}
