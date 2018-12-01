package daggerok.rest;

import daggerok.data.MyEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static daggerok.data.MyEntity.FIND_ALL;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.0/html/developing_web_services_applications/developing_jax_rs_web_services
 */
@Path("")
@Stateless
@Produces(APPLICATION_JSON)
public class MyResource {
  //@Resource(lookup = "java:jboss/datasources/ExampleDS")
  //DataSource exampleDS;

  @Inject
  EntityManager em;

  @Context
  UriInfo uriInfo;

  @POST
  @Path("")
  @Transactional
  @Consumes(APPLICATION_JSON)
  public Response save(final Map<String, String> req) {

    final String maybeData = req.get("data");
    final String data = Optional.ofNullable(maybeData)
                                .orElseThrow(() -> new RuntimeException("cannot find data"));
    final MyEntity myEntity = MyEntity.of(data);

    em.persist(myEntity);
    return Response.created(uriInfo.getBaseUriBuilder()
                                   .path(MyResource.class)
                                   .path(MyResource.class, "getOne")
                                   .build(myEntity.getId()))
                   .build();
  }

  @GET
  @Path("")
  public List<MyEntity> getAll(@QueryParam("q") final String q) {
    return em.createNamedQuery(FIND_ALL, MyEntity.class)
             .setMaxResults(100)
             .getResultList();
  }

  @GET
  @Path("{id}")
  public MyEntity getOne(@PathParam("id") final UUID id) {
    return em.find(MyEntity.class, id);
  }
}
