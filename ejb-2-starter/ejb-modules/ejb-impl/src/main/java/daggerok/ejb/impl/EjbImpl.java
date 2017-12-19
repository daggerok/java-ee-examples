package daggerok.ejb.impl;

import daggerok.api.ejb.local.EjbLocal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

import static java.lang.String.format;

@Stateless
public class EjbImpl implements EjbLocal {

  public String businessLogic(final String name) {
    return format("Hello, %s!", name);
  }

  // lifecycle

  @PostConstruct
  public void postConstruct() {
    System.out.println(format("%s created.", getClass().getSimpleName()));
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println(format("%s destroying...", getClass().getSimpleName()));
  }
}
