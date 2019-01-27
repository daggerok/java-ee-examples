package com.github.daggerok.dwr.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Date;

@ApplicationScoped
public class MyProduces {

  @Produces
  public Date getDate() {
    return new Date();
  }
}
