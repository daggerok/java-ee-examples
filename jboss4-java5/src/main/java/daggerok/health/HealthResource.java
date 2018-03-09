package daggerok.health;

import lombok.Cleanup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/*")
public class HealthResource extends HttpServlet {

  @Override
  protected void service(final HttpServletRequest req, final HttpServletResponse resp)
      throws ServletException, IOException {

    @Cleanup final PrintWriter writer = resp.getWriter();
    writer.printf("<p>Hello <b>%s</b>!</p>", "Maksimko");
  }
}
