package daggerok.ejb.impl;

import daggerok.api.egb.local.GoodbyerLocal;

import javax.ejb.Stateless;

@Stateless(mappedName = "bye", name = "GoodbyerImpl")
public class GoodbyerImpl implements GoodbyerLocal {

  public GoodbyerImpl() {
    System.out.println("Goodbye?");
  }

  public String byeMessage() {
    return "bye-bye and see u soon...";
  }
}
