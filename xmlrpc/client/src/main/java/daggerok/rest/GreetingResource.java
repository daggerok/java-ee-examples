package daggerok.rest;

import daggerok.xmlrpc.XmlRpcClient;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("greet")
@Produces(APPLICATION_JSON)
public class GreetingResource {

  @Context
  UriInfo uriInfo;

  @GET
  @Path("{name}")
  public JsonObject sayHi(@PathParam("name") String name) {

    return Json.createObjectBuilder()
               .add("result", XmlRpcClient.sayHi(name))
               .add("_self",
                    uriInfo.getBaseUriBuilder()
                           .path(GreetingResource.class)
                           .path(GreetingResource.class, "sayHi")
                           .build(name)
                           .toString())
               .build();
  }
}
