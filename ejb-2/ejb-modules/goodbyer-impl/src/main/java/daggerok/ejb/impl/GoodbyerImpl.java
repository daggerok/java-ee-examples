package daggerok.ejb.impl;

import daggerok.api.egb.local.GoodbyerLocal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

import static java.lang.String.format;

@Stateless(mappedName = "bye", name = "GoodbyerImpl")
public class GoodbyerImpl implements GoodbyerLocal {

  public GoodbyerImpl() {
    System.out.println("Goodbye?");
  }

  public String byeMessage() {
    return "bye-bye and see u soon...";
  }

  // lifecycle

  @PostConstruct
  public void postConstruct() {
    System.out.println(format("%s created.", getClass().getSimpleName()));
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println(format("%s is destroying..", getClass().getSimpleName()));
  }
}
