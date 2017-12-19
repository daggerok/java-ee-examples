package daggerok.api.ejb.local;

import javax.ejb.Local;

@Local
public interface EjbLocal {
  String businessLogic(final String in);
}
