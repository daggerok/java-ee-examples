package daggerok.ejb.impl;

import daggerok.api.egb.local.GreeterLocal;
import daggerok.api.ejb.remote.GreeterRemote;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;

@Stateless
@NoArgsConstructor
public class GreeterImpl implements GreeterLocal, GreeterRemote {

  public String helloMessage() {
    return "hello from impl!";
  }
}
