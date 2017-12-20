package daggerok;

import daggerok.api.ejb.local.StatefulEjbLocal;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static daggerok.session.SessionListener.statefulServiceName;

@Slf4j
@WebServlet(urlPatterns = "/reset/*", loadOnStartup = 1)
public class ResetClientServlet extends HttpServlet {

  private static final long serialVersionUID = -2695481210432979654L;

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) {

    val session = request.getSession();
    val statefulService = (StatefulEjbLocal) session.getAttribute(statefulServiceName);

    if (null == statefulService) {
      response.sendRedirect("./?counter=" + statefulService.incrementCounter());
      return;
    }

    session.invalidate();
    response.sendRedirect("./");
  }
}
