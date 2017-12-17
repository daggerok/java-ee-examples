package daggerok;

import daggerok.api.ejb.remote.GreeterRemote;
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
public class RemoteClientServlet extends HttpServlet {

  private static final long serialVersionUID = 6621759119315184794L;

  @EJB
  GreeterRemote greeter;

  @Override
  public void init() throws ServletException {
    super.init();
    System.out.println("RemoteClientServlet started.");
  }

  @Override
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) throws ServletException, IOException {

    val writer = response.getWriter();

    writer.println(format("remote EJB client says: %s", greeter.helloMessage()));
    writer.close();
  }

  @Override
  public void destroy() {
    super.destroy();
    System.out.println("RemoteClientServlet killed.");
  }
}
