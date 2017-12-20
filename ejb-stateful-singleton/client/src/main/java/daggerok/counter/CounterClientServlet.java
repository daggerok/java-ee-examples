package daggerok.counter;

import daggerok.api.ejb.local.StatefulEjbLocal;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static daggerok.session.SessionListener.statefulServiceName;

@Slf4j
@WebServlet(urlPatterns = "/counter/*", loadOnStartup = 1)
public class CounterClientServlet extends HttpServlet {

  private static final long serialVersionUID = -7317916470184383301L;

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) {

    val session = request.getSession();
    val statefulService = (StatefulEjbLocal) session.getAttribute(statefulServiceName);
    @Cleanup val writer = response.getWriter();

    if (null == statefulService) {
      writer.println("EJB bean wasn't found");
      return;
    }

    val paths = request.getPathInfo().split("/");

    if (paths.length < 1) {
      writer.println("supported commands: 'increment' and 'decrement'");
      return;
    }

    val command = paths[paths.length - 1];
    if (command.toLowerCase().startsWith("incr")) writer.println(statefulService.incrementCounter());
    if (command.toLowerCase().startsWith("decr")) writer.println(statefulService.decrementCounter());
  }
}
