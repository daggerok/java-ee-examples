package daggerok.ejb.impl;

import daggerok.api.egb.local.GreeterLocal;
import daggerok.api.ejb.remote.GreeterRemote;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

import static java.lang.String.format;

@Stateless(mappedName = "greet", name = "GreeterImpl")
public class GreeterImpl implements GreeterLocal, GreeterRemote {

  public GreeterImpl() {
    System.out.println("Did I lazy initialized? :)");
  }

  public String helloMessage() {
    return "Hello from EJB Facade!";
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
