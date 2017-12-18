package daggerok.api.ejb.local;

import javax.ejb.Local;

@Local
public interface EjbLocal {
  void start();

  void stop();
}
