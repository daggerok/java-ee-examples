package daggerok;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "", loadOnStartup = 1)
public class SimpleServlet extends HttpServlet {

  private static final long serialVersionUID = -3265128782229939793L;

  @Override
  public void destroy() {
    System.out.println("killing simple servlet...");
    super.destroy();
    System.out.println("simple servlet killed.");
  }

  @Override
  public void init() throws ServletException {
    System.out.println("starting simple servlet....");
    super.init();
    System.out.println("simple servlet started.");
  }

  @Override
  protected void service(final HttpServletRequest request, final HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    final PrintWriter writer = response.getWriter();
    writer.append("Hey!");
    writer.close();
  }
}
