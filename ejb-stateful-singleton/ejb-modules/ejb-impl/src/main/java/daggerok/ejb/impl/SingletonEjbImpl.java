package daggerok.ejb.impl;

import daggerok.api.ejb.local.SingletonEjbLocal;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Singleton
public class SingletonEjbImpl implements SingletonEjbLocal {

  Map<String, String> db;

  @PostConstruct
  public void postConstruct() {
    log.info("PostConstruct {}...", this);
    db = new ConcurrentHashMap<String, String>();
  }

  @PostActivate
  public void postActivate() {
    log.info("PostActivate {}...", this);
    db.put("EJB", "default value");
  }

  @PrePassivate
  public void prePassivate() {
    log.info("PrePassivate {}...", this);
  }

  @PreDestroy
  public void preDestroy() {
    log.info("PreDestroy {}...", this);
  }

  public void save(final String key, final String value) {
    db.put(key, value);
  }

  public String find(final String key) {
    return db.get(key);
  }
}
