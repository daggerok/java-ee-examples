package daggerok.state;

import daggerok.api.ejb.local.StatefulEjbLocal;
import lombok.Cleanup;
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

@Slf4j
@WebServlet(urlPatterns = "/set/*", loadOnStartup = 1)
public class SetClientServlet extends HttpServlet {

  private static final long serialVersionUID = 6621759119315184794L;

  /*
    @EJB
    StatefulEjbLocal statefulService;
  */
  @Override
  public void init() throws ServletException {
    log.info("SetClientServlet {} started.", this);
  }

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) {

    val key = request.getParameter("key");
    var value = request.getParameter("value");

    val session = request.getSession();
    val statefulService = (StatefulEjbLocal) session.getAttribute(statefulServiceName);
    @Cleanup val writer = response.getWriter();

    if (null == statefulService) {
      writer.println("EJB bean wasn't found");
      return;
    }

    statefulService.setSingletonState(key, value);
    response.sendRedirect("./get?key=" + key);
  }

  @Override
  public void destroy() {
    log.info("SetClientServlet {} killed.", this);
  }
}
