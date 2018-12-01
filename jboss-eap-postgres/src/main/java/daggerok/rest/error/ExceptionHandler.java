package daggerok.rest.error;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;

@Slf4j
@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {

  @Override
  public Response toResponse(Throwable error) {
    log.error("handle fallback error on: '{}'", error.getLocalizedMessage(), error);
    return Response.status(Response.Status.BAD_REQUEST)
                   .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                   .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                   .entity(singletonMap("error", error.getLocalizedMessage()))
                   .build();
  }
}
