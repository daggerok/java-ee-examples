package daggerok.ejb.impl;

import daggerok.api.ejb.local.SingletonEjbLocal;
import daggerok.api.ejb.local.StatefulEjbLocal;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;

import static java.lang.String.format;

@Slf4j
@Stateful
public class StatefulEjbImpl implements StatefulEjbLocal {

  @EJB
  SingletonEjbLocal db;

  public String storeSomeState(final String name) {
    return format("Hello, %s!", name);
  }

  // lifecycle

  @PostConstruct
  public void postConstruct() {
    log.info("PostConstruct {}...", this);
  }

  @PostActivate
  public void postActivate() {
    log.info("PostActivate {}...", this);
    db.save("EJB", "default value");
  }

  @PrePassivate
  public void prePassivate() {
    log.info("PrePassivate {}...", this);
  }

  @PreDestroy
  public void preDestroy() {
    log.info("PreDestroy {}...", this);
  }

  public void setState(final String key, final String value) {
    db.save(key, value);
  }

  public String getSomeState(final String key) {
    return db.find(key);
  }
}
