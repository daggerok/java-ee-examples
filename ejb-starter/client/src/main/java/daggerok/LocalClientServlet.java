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

import static java.lang.String.format;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class LocalClientServlet extends HttpServlet {

  private static final long serialVersionUID = 6621759119315184794L;

  @EJB
  EjbLocal service;

  @Override
  public void init() throws ServletException {
    super.init();
    System.out.println("LocalClientServlet started.");
  }

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) throws ServletException, IOException {

    val writer = response.getWriter();
    writer.println(format("remote EJB client says: %s", service.businessLogic("EJB")));
    writer.close();
  }

  @Override
  public void destroy() {
    super.destroy();
    System.out.println("LocalClientServlet killed.");
  }
}
