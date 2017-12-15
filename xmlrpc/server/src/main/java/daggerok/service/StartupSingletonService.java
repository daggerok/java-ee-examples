package daggerok.service;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Slf4j
@Startup
@Singleton
public class StartupSingletonService {

  public StartupSingletonService() {
    log.warn("Initialization constructor: {}", this.getClass().toString());
  }

  @PostConstruct
  public void onStartup() {
    log.warn("Initialization post construct: {}", this.getClass().toString());
  }
}
