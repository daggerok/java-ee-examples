package daggerok.api.egb.local;

import javax.ejb.Local;

@Local
public interface GreeterLocal {
  String helloMessage();
}
