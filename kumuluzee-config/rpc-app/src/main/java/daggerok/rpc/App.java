package daggerok.rpc;

import daggerok.rpc.client.Client;
import daggerok.rpc.server.Server;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Slf4j
@ApplicationScoped
@ApplicationPath("")
public class App extends Application {

  @Inject
  Server server;

  @Inject
  Client client;

  @SneakyThrows
  @PostConstruct
  public void init() {
    log.info("daggerok.rpc.server injected: {}", server.getService());
    log.info("daggerok.rpc.client injected: {}", client);
  }
}
