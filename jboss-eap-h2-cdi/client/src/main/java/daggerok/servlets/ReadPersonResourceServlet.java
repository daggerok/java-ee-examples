package daggerok.servlets;

import daggerok.cdi.PersonService;
import daggerok.domain.User;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Slf4j
@WebServlet(urlPatterns = "/*", loadOnStartup = 2)
public class ReadPersonResourceServlet extends HttpServlet {

  private static final long serialVersionUID = 1439231173778627396L;

  @Inject PersonService personService;

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) throws ServletException, IOException {

    @Cleanup val writer = response.getWriter();
    writer.println(getPeopleObject().toString());
    response.setContentType(APPLICATION_JSON);
  }

  @SneakyThrows
  private JsonObject getPeopleObject() {

    val array = Json.createArrayBuilder();
    val people = personService.getPeople()
                              .get(5, TimeUnit.SECONDS);

    for (final User user : people) {
      array.add(Json.createObjectBuilder()
                    .add("id", user.getId().toString())
                    .add("name", user.getName()));
    }

    return Json.createObjectBuilder()
               .add("people", array)
               .build();
  }
}
