package daggerok.ejb.impl;

import daggerok.api.ejb.remote.OnlyRemote;

import javax.ejb.Stateless;

@Stateless(mappedName = "only", name = "OnlyImpl")
public class OnlyImpl implements OnlyRemote {

  public OnlyImpl() {
    System.out.println("Only lazy initialization! (:");
  }

  public String getMessage() {
    return "I remote you!";
  }
}
