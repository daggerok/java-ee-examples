package daggerok.api.ejb.local;

import javax.ejb.Local;

@Local
public interface StatefulEjbLocal {
  void setState(final String key, final String value);

  String getSomeState(final String key);
}
