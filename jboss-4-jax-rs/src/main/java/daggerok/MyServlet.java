package daggerok;

import lombok.Cleanup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/api/*")
public class MyServlet extends HttpServlet {

  @Override
  protected void service(final HttpServletRequest req, final HttpServletResponse res)
      throws ServletException, IOException {

    @Cleanup final PrintWriter writer = res.getWriter();
    res.addHeader("Content-Type", "application/json");
    writer.printf("holla! %s\n", new Date());
  }
}
