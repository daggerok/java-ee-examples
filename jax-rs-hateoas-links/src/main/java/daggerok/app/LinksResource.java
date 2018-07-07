package daggerok.app;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.Link.REL;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@Stateless
@Produces(APPLICATION_JSON)
public class LinksResource {

  @Context
  UriInfo uriInfo;

  @GET
  @Path("{path: .*}")
  public Response links(@PathParam("path") final String path) {
    return Response.ok(Json.createObjectBuilder()
                           .add("_links",
                                Json.createArrayBuilder()
                                    .add(Json.createObjectBuilder()
                                             .add(REL, "UriBuilder")
                                             .add("href",
                                                  uriInfo.getBaseUriBuilder()
                                                         .path(LinksResource.class)
                                                         .path(LinksResource.class, "links")
                                                         .build(path)
                                                         .toString()))
                                    .add(Json.createObjectBuilder()
                                             .add(REL, "Link.fromUri('/ololo/trololo').build()")
                                             .add("href", Link.fromUri("/ololo/trololo").build().toString()))
                                    .add(Json.createObjectBuilder()
                                             .add(REL, "Link.fromMethod(LinksResource.class, 'links').build(path)")
                                             .add("href", Link.fromMethod(LinksResource.class, "links").build(path).toString()))
                                    .add(Json.createObjectBuilder()
                                             .add(REL, "Link.fromResource(LinksResource.class)")
                                             .add("href", Link.fromResource(LinksResource.class).build().toString()))
                                    .add(Json.createObjectBuilder()
                                             .add(REL, "empty")))
                           .build())
                   .build();
  }
}
