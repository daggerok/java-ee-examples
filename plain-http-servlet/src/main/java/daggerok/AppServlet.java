package daggerok;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/*")
public class AppServlet extends HttpServlet {

  private static final long serialVersionUID = -8407130301988284151L;

  @Override
  @SneakyThrows
  public void service(final ServletRequest req, final ServletResponse res) {

    @Cleanup val writer = res.getWriter();
    writer.println("<b>hi!</b>");
  }
}
