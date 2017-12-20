package daggerok.ejb.impl;

import daggerok.api.ejb.local.SingletonEjbLocal;
import daggerok.api.ejb.local.StatefulEjbLocal;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;

import static java.lang.String.format;

@Slf4j
@Stateful
public class StatefulEjbImpl implements StatefulEjbLocal {

  @EJB
  SingletonEjbLocal db;

  String id;

  public String storeSomeState(final String name) {
    return format("Hello, %s!", name);
  }

  // lifecycle

  @PostConstruct
  public void postConstruct() {
    id = "" + System.currentTimeMillis();
    log.info("PostConstruct {} of {}", this, id);
  }

  @PostActivate
  public void postActivate() {
    log.info("PostActivate {} of {}", this, id);
    db.save("EJB", "default value");
  }

  @PrePassivate
  public void prePassivate() {
    log.info("PrePassivate {} of {}", this, id);
  }

  @PreDestroy
  public void preDestroy() {
    log.info("PreDestroy {} of {}", this, id);
  }

  public void setState(final String key, final String value) {
    db.save(key, value);
  }

  public String getSomeState(final String key) {
    return "" + id + ":" + db.find(key);
  }

  @Remove
  public void removeBean() {
    log.info("Remove {} of {}", this, id);
  }
}
