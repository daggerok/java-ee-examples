package daggerok;

import daggerok.api.ejb.local.StatefulEjbLocal;
import lombok.SneakyThrows;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

@Slf4j
@WebServlet(urlPatterns = "/get/*", loadOnStartup = 1)
public class GetClientServlet extends HttpServlet {

  private static final long serialVersionUID = 6621759119315184794L;

  @EJB
  StatefulEjbLocal statefulService;

  @Override
  public void init() throws ServletException {
    log.info("GetClientServlet {} started.", this);
  }

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) {

    var key = "EJB";
    key = request.getParameter("key");

    log.info("query string: {}", request.getQueryString());
    log.info("key: {}", key);

    val writer = response.getWriter();
    writer.println(format("Local Stateful EJB client says: %s", statefulService.getSomeState(key)));
    writer.close();
  }

  @Override
  public void destroy() {
    log.info("GetClientServlet {} killed.", this);
  }
}
