package daggerok.xmlrpc;

import lombok.Data;
import lombok.SneakyThrows;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import java.net.URL;

import static java.lang.String.format;

@Data
@Singleton
//@ApplicationScoped
public class Config {

  public static final int port = 8081;

  public static final String url = format("http://127.0.0.1:%d", port);

  @SneakyThrows
  public static final URL getUrl() {
    return new URL(url);
  }
}
