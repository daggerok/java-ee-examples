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

@WebServlet(urlPatterns = "/start/*", loadOnStartup = 1)
public class StartJobServlet extends HttpServlet {

  private static final long serialVersionUID = 7634534691601699415L;

  @EJB
  EjbImpl service;

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) throws ServletException, IOException {

    val writer = response.getWriter();
    service.start();
    writer.println("start job");
    writer.close();
  }
}
