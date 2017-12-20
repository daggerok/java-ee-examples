package daggerok;

import daggerok.api.ejb.local.StatefulEjbLocal;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@WebServlet(urlPatterns = "/reset/*", loadOnStartup = 1)
public class ResetClientServlet extends HttpServlet {

  private static final long serialVersionUID = -2695481210432979654L;

  @EJB
  StatefulEjbLocal statefulService;

  @Override
  public void init() throws ServletException {
    log.info("ResetClientServlet {} started.", this);
  }

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) {

    val session = request.getSession();
    session.getId();

    //statefulService.removeBean();
    // @Cleanup val writer = response.getWriter();
    response.sendRedirect(
        "./?id=" + session.getId()
            + "&isNew=" + session.isNew()
        + "&created=" + session.getCreationTime()
        + "&last-time=" + session.getLastAccessedTime()
    );
  }

  @Override
  public void destroy() {
    log.info("ResetClientServlet {} killed.", this);
  }
}
