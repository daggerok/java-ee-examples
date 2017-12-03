package daggerok.rpc.config;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/config")
@Produces(APPLICATION_JSON)
public class ConfigResource {

  @Inject
  ConfigProps configProps;

  @GET
  public Response getConfig() {
    return Response.ok(Json.createArrayBuilder()
                           .add(configProps.getStrng())
                           .add(configProps.getIntgr())
                           .add(configProps.getBln())
                           .build())
                   .build();
  }
}
