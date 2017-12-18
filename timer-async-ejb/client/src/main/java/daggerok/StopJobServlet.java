package daggerok;

import daggerok.api.ejb.local.EjbLocal;
import lombok.SneakyThrows;
import lombok.val;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/stop/*", loadOnStartup = 2)
public class StopJobServlet extends HttpServlet {

  @EJB
  EjbLocal service;

  @Override
  public void init() throws ServletException {
    super.init();
    System.out.println("StopJobServlet started.");
  }

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) throws ServletException, IOException {

    val writer = response.getWriter();
    service.stop();
    writer.println("stop job");
    writer.close();
  }

  @Override
  public void destroy() {
    super.destroy();
    System.out.println("StopJobServlet killed.");
  }
}
