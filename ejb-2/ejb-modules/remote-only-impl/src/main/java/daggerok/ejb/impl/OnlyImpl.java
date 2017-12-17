package daggerok.ejb.impl;

import daggerok.api.ejb.remote.OnlyRemote;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

import static java.lang.String.format;

@Stateless(mappedName = "only", name = "OnlyImpl")
public class OnlyImpl implements OnlyRemote {

  public OnlyImpl() {
    System.out.println("Only lazy initialization! (:");
  }

  public String getMessage() {
    return "I remote you!";
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
