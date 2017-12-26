package daggerok;

import lombok.SneakyThrows;
import lombok.val;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "", loadOnStartup = 1)
public class JNDIContextServlet extends HttpServlet {

  private static final long serialVersionUID = -3265128782229939793L;

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {

    val context = new InitialContext();
    val namingEnumeration = context.list("");
    val printWriter = response.getWriter();

    context.bind("ololo", "trololo");

    while (namingEnumeration.hasMore())
      render(printWriter, "next: " + namingEnumeration.next());

    render(printWriter, context.lookup("ololo").toString());
    render(printWriter, "I'm done with " + namingEnumeration);
    printWriter.close();
  }

  @SneakyThrows
  private void render(final PrintWriter writer, final String message) {
    writer.println(message);
  }
}
