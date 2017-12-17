package daggerok.ejb.impl;

import daggerok.api.egb.local.GreeterLocal;
import daggerok.api.ejb.remote.GreeterRemote;

import javax.ejb.Stateless;

@Stateless(mappedName = "greet", name = "GreeterImpl")
public class GreeterImpl implements GreeterLocal, GreeterRemote {

  public GreeterImpl() {
    System.out.println("Did I lazy initialized? :)");
  }

  public String helloMessage() {
    return "Hello from EJB Facade!";
  }
}
