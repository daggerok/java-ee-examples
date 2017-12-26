package daggerok.servlets;

import daggerok.cdi.PersonService;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.var;
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
@WebServlet(urlPatterns = "/update/*", loadOnStartup = 1)
public class UpdatePersonResourceServlet extends HttpServlet {

  private static final long serialVersionUID = 1439231173778627396L;

  @Inject PersonService personService;

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) throws ServletException, IOException {

    var name = "anonymous";
    var remove = "false";

    val paths = request.getPathInfo().split("/");
    if (paths.length > 0) name = paths[paths.length - 1];
    log.info("request path: {}", request.getPathInfo());

    val fromRequest = request.getParameter("remove");
    if (null != fromRequest && !"".equals(fromRequest.trim())) remove = fromRequest;
    log.info("request name query param: {}", fromRequest);

    // with @Cleanup - `writer.close()` not needed anymore
    @Cleanup val writer = response.getWriter();

    val jsonObject = "true".equals(remove)
        ? getRemoveObject(name) : getCreateObject(name);

    writer.println(jsonObject.toString());
    response.setContentType(APPLICATION_JSON);
  }

  @SneakyThrows
  private JsonObject getRemoveObject(final String name) {

    val jsonArray = Json.createArrayBuilder();
    val names = personService.removeAny(name)
                             .get(5, TimeUnit.SECONDS);

    for (final String n : names) {
      jsonArray.add(n);
    }

    return Json.createObjectBuilder()
               .add("removed", jsonArray)
//               .add("removed", jsonArray.build())
               .build();
  }

  @SneakyThrows
  private JsonObject getCreateObject(final String name) {

    val user = personService.createPerson(name)
                            .get(5, TimeUnit.SECONDS);

    return Json.createObjectBuilder()
               .add("id", user.getId().toString())
               .add("name", user.getName())
               .build();
  }
}
