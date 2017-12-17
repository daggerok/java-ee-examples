package daggerok.api.egb.local;

import javax.ejb.Local;

@Local
public interface GoodbyerLocal {
  String byeMessage();
}
