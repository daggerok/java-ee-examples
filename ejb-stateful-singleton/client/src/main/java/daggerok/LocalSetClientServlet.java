package daggerok;

import daggerok.api.ejb.local.StatefulEjbLocal;
import lombok.Cleanup;
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

@Slf4j
@WebServlet(urlPatterns = "/set/*", loadOnStartup = 1)
public class LocalSetClientServlet extends HttpServlet {

  private static final long serialVersionUID = 6621759119315184794L;

  @EJB
  StatefulEjbLocal statefulService;

  @Override
  public void init() throws ServletException {
    log.info("LocalClientServlet {} started.", this);
  }

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) {

    var key = "EJB";
    key = request.getParameter("key");

    var value = "" + System.currentTimeMillis();
    value = request.getParameter("value");

    log.info("query string: {}", request.getQueryString());
    log.info("key: '{}', value: '{}'", key, value);

    statefulService.setState(key, value);

    @Cleanup val writer = response.getWriter();
//    writer.println(format("Local Stateful EJB client says: %s", ));
    response.sendRedirect("./get?key=" + key);
  }

  @Override
  public void destroy() {
    log.info("LocalClientServlet {} killed.", this);
  }
}
