package daggerok.app;

import javax.json.Json;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class GlobalErrorHandler implements ExceptionMapper<Throwable> {

  @Override
  @Produces(APPLICATION_JSON)
  public Response toResponse(Throwable e) {

    final String err = e.getLocalizedMessage();
    final String message = null == err ? "empty" : err;

    return Response.status(BAD_REQUEST)
                   .entity(Json.createObjectBuilder()
                               .add("error", message)
                               .build()
                               .toString())
                   .build();
  }
}
