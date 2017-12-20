package daggerok.api.ejb.local;

import javax.ejb.Local;

@Local
public interface StatefulEjbLocal {
  void setSingletonState(final String key, final String value);

  String getSingletonSomeState(final String key);

  long incrementCounter();

  long decrementCounter();

  void removeBean();
}
