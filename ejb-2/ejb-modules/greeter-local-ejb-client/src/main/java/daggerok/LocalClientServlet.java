package daggerok;

import daggerok.api.egb.local.GreeterLocal;
import lombok.val;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class LocalClientServlet extends HttpServlet {

  private static final long serialVersionUID = -1190553619957404746L;

  GreeterLocal greeter;

  @Override
  public void init() throws ServletException {
    super.init();
    System.out.println("LocalClientServlet started.");
  }

  @Override
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) throws ServletException, IOException {

    val writer = response.getWriter();
    writer.println("local EJB client");
    writer.close();
  }

  @Override
  public void destroy() {
    super.destroy();
    System.out.println("LocalClientServlet killed.");
  }
}
