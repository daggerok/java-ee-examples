package daggerok.api.ejb.local;

import javax.ejb.Local;

@Local
public interface AsyncWorkerEjbLocal {
  void executeAsync();
}
