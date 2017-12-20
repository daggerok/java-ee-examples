package daggerok.ejb.impl;

import daggerok.api.ejb.local.SingletonEjbLocal;
import daggerok.api.ejb.local.StatefulEjbLocal;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.String.format;

@Slf4j
@Stateful
public class StatefulEjbImpl implements StatefulEjbLocal {

  @EJB
  SingletonEjbLocal db;

  AtomicLong counter;

  public String storeSomeState(final String name) {
    return format("Hello, %s!", name);
  }

  // lifecycle

  @PostConstruct
  public void postConstruct() {
    counter = new AtomicLong(0);
    log.info("PostConstruct {} of {}", this, counter.get());
  }

  @PostActivate
  public void postActivate() {
    log.info("PostActivate {} of {}", this, counter.get());
    db.save("EJB", "default value");
  }

  @PrePassivate
  public void prePassivate() {
    log.info("PrePassivate {} of {}", this, counter.get());
  }

  @PreDestroy
  public void preDestroy() {
    log.info("PreDestroy {} of {}", this, counter.get());
  }

  public void setSingletonState(final String key, final String value) {
    db.save(key, value);
  }

  public String getSingletonSomeState(final String key) {
    return db.find(key) + ":" + counter.get();
  }

  public long incrementCounter() {
    return counter.incrementAndGet();
  }

  public long decrementCounter() {
    return counter.decrementAndGet();
  }

  @Remove
  public void removeBean() {
    log.info("Remove {} of {}", this, counter.get());
  }
}
