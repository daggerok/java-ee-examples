package daggerok;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.TEXT_HTML;

@Path("")
@Stateless
public class IndexResource {

  @GET
  @Path("")
  @Produces(TEXT_HTML)
  public Response health(@Context final UriInfo uriInfo) {

    return Response.temporaryRedirect(uriInfo.getBaseUriBuilder()
                                             .path("/faces/{index}")
                                             .build("index.xhtml"))
                   .build();
  }
}
