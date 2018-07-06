package daggerok.app;

import daggerok.app.health.HealthResource;
import daggerok.app.item.ReadItemsResource;
import daggerok.app.item.WriteItemsResource;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@Stateless
@Produces(APPLICATION_JSON)
public class ApiResource {

  @Context
  UriInfo uriInfo;

  @GET
  @Path("")
  public Response api() {

    return Response.ok(Json.createArrayBuilder()
                           .add(pathTo(ReadItemsResource.class, "getAll1"))
                           .add(pathTo(ReadItemsResource.class, "getAll2"))
                           .add(pathTo(ReadItemsResource.class, "get1", "$id"))
                           .add(pathTo(ReadItemsResource.class, "get2", "$id"))
                           .add(pathTo(WriteItemsResource.class, "post1"))
                           .add(pathTo(WriteItemsResource.class, "post2"))
                           .add(pathTo(ApiResource.class, "api"))
                           .add(pathTo(HealthResource.class, "health"))
                           .build())
                   .build();
  }

  String pathTo(final Class aClass, final String... args) {
    return args.length > 1
        ? uriInfo.getBaseUriBuilder()
                 .path(aClass)
                 .path(aClass, args[0])
                 .build(args[1])
                 .toString()
        : uriInfo.getBaseUriBuilder()
                 .path(aClass)
                 .path(aClass, args[0])
                 .build()
                 .toString()
        ;
  }
}
