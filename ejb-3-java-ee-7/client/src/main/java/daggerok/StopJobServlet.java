package daggerok;

import daggerok.ejb.impl.EjbImpl;
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

  private static final long serialVersionUID = 6380386591160803361L;

  @EJB
  EjbImpl service;

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) throws ServletException, IOException {

    val writer = response.getWriter();
    service.stop();
    writer.println("stop job");
    writer.close();
  }
}
