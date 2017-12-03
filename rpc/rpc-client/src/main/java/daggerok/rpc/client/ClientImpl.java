package daggerok.rpc.client;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class ClientImpl implements Client {

  @PostConstruct
  public void init() {
    if (log.isInfoEnabled()) log.info("client created");
  }
}
