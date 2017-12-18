package daggerok;

import daggerok.ejb.impl.AsyncWorkerEjbImpl;
import daggerok.ejb.impl.domain.User;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.var;
import lombok.val;

import javax.ejb.EJB;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@WebServlet(urlPatterns = "/*", loadOnStartup = 3)
public class AsyncPersonResourceServlet extends HttpServlet {

  private static final long serialVersionUID = -3757579368400077692L;

  @EJB
  AsyncWorkerEjbImpl asyncService;

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) throws ServletException, IOException {

    var name = "anonymous";

    val paths = request.getPathInfo().split("/");
    if (paths.length > 0) name = paths[paths.length - 1];
    System.out.println(format("request path: %s", request.getPathInfo()));

    val fromRequest = request.getParameter("name");
    if (null != fromRequest && !"".equals(fromRequest.trim())) name = fromRequest;
    System.out.println(format("request name query param: %s", fromRequest));

    // with @Cleanup - `writer.close()` not needed anymore
    @Cleanup val writer = response.getWriter();
    val user = (User) asyncService.createAsyncPerson(name).get(5, TimeUnit.SECONDS);

    writer.println(Json.createObjectBuilder()
                       .add("id", user.getId().toString())
                       .add("name", user.getName())
                       .build()
                       .toString());

    response.setContentType(APPLICATION_JSON);
  }
}
