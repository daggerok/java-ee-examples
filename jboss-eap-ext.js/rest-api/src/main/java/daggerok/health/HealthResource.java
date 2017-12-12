package daggerok.health;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthResource {

  @Inject
  HealthService healthService;

  @GET
  public JsonObject health() {
    return Json.createObjectBuilder()
               .add("status", healthService.status())
               .build();
  }
}
