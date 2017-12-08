package daggerok.business;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("api")
@ApplicationScoped
@Produces(APPLICATION_JSON)
public class AppResource {

  @Inject
  InfoService infoService;

  @GET
  public Response get() {
    return Response.ok(infoService.getAvailableResources())
                   .build();
  }
}
