package daggerok.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Service {

  public String sendMessage(final String name) {
    return "Hey, " + name + "!";
  }
}
