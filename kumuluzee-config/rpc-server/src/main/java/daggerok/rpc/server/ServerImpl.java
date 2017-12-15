package daggerok.rpc.server;

import daggerok.rpc.api.Service;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@NoArgsConstructor
@ApplicationScoped
public class ServerImpl implements Server {

  @Getter
  @Inject
  Service service;

  @SneakyThrows
  @PostConstruct
  public void init() {
    log.info("repo injected: {}", service);
  }
}
