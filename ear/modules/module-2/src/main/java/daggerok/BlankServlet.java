package daggerok;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class BlankServlet extends HttpServlet {

  private static final long serialVersionUID = -3265128782229939793L;

  @Override
  public void destroy() {
    System.out.println("killing blank servlet...");
    super.destroy();
    System.out.println("blank servlet killed.");
  }

  @Override
  public void init() throws ServletException {
    System.out.println("starting blank servlet....");
    super.init();
    System.out.println("blank servlet started.");
  }

  @Override
  protected void service(final HttpServletRequest request, final HttpServletResponse response) throws javax.servlet.ServletException, IOException { }
}
