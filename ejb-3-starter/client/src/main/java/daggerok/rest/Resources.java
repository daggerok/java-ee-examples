package daggerok.rest;

import daggerok.ejb.impl.AsyncEjbWorker;
import daggerok.services.Service;
import lombok.SneakyThrows;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.concurrent.TimeUnit;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Stateless
@Path("api")
@Produces(APPLICATION_JSON)
public class Resources {

  @EJB
  AsyncEjbWorker greeter;

  @Inject
  Service service;

  @Context
  UriInfo uriInfo;

  @GET
  @Path("hello")
  @SneakyThrows
  public Response greeting() {

    return Response.ok(Json.createObjectBuilder()
                           .add("cdi", service.sendMessage("CDI"))
                           .add("ejb", greeter.sendMessage("EJB")
                                              .get(3, TimeUnit.SECONDS))
                           .add("_self", uriInfo.getBaseUriBuilder()
                                                .path(Resources.class)
                                                .path(Resources.class, "greeting")
                                                .build()
                                                .toString())
                           .build())
                   .build();
  }
}
