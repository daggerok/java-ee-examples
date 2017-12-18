package daggerok;

import com.fasterxml.jackson.databind.ObjectMapper;
import daggerok.api.ejb.local.AsyncWorkerEjbLocal;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.var;
import lombok.val;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@WebServlet(urlPatterns = { "/*" }, loadOnStartup = 3)
public class AsyncPersonResourceServlet extends HttpServlet {

  @EJB
  AsyncWorkerEjbLocal asyncService;

  @Override
  public void init() throws ServletException {
    super.init();
    System.out.println("AsyncPersonResourceServlet started.");
  }

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
    val user = asyncService.createAsyncPerson(name).get(5, TimeUnit.SECONDS);
    val json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user);
    writer.println(json);

    response.setContentType(APPLICATION_JSON);
  }

  @Override
  public void destroy() {
    super.destroy();
    System.out.println("AsyncPersonResourceServlet killed.");
  }
}
