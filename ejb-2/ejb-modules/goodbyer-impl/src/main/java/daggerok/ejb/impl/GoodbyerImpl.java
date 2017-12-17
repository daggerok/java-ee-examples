package daggerok.ejb.impl;

import daggerok.api.egb.local.GoodbyerLocal;

import javax.ejb.Stateless;

@Stateless(mappedName = "bye", name = "GoodbyerImpl")
public class GoodbyerImpl implements GoodbyerLocal {

  public String byeMessage() {
    return "bye-bye and see u soon...";
  }
}
