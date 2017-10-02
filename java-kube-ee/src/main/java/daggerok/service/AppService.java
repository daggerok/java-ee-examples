package daggerok.service;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import java.net.InetAddress;
import java.time.Instant;
import java.util.List;

import static java.util.Arrays.asList;

@Singleton
@Stateless
@ApplicationScoped
public class AppService {
  public List<String> getResult() {
    String fuck = Instant.now().toString();
    try {
      fuck = InetAddress.getLocalHost().getHostAddress();
    } catch (Exception e) {
      System.err.println(fuck);
    }
    return asList(
        "one",
        "two",
        "three",
        "kubeee"/*,
        fuck*/
    );
  }
}
