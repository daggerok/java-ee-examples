package daggerok.state;

import daggerok.api.ejb.local.StatefulEjbLocal;
import lombok.SneakyThrows;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static daggerok.session.SessionListener.statefulServiceName;
import static java.lang.String.format;

@Slf4j
@WebServlet(urlPatterns = "/get/*", loadOnStartup = 1)
public class GetClientServlet extends HttpServlet {

  private static final long serialVersionUID = 6621759119315184794L;

  /*
    @EJB
    StatefulEjbLocal statefulService;
  */
  @Override
  public void init() throws ServletException {
    log.info("GetClientServlet {} started.", this);
  }

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) {

    var key = request.getParameter("key");

    val writer = response.getWriter();
    val session = request.getSession();
    val statefulService = (StatefulEjbLocal) session.getAttribute(statefulServiceName);

    if (null == statefulService) {
      writer.println("EJB bean wasn't found");
      return;
    }

    writer.println(format("Local Stateful EJB client says: %s", statefulService.getSingletonSomeState(key)));
    writer.close();
  }

  @Override
  public void destroy() {
    log.info("GetClientServlet {} killed.", this);
  }
}
