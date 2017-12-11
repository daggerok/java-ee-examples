package daggerok.health;

import daggerok.status.StatusService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Stateless
@Path("health")
@Produces(APPLICATION_JSON)
public class HealthResource {

  @Inject
  StatusService statusService;

  @GET
  public Response health() {
    return Response.ok(singletonMap("status", statusService.status()))
                   .build();
  }
}
