package daggerok.api.ejb.local;

import daggerok.api.ejb.local.domain.Person;

import javax.ejb.Local;
import java.util.concurrent.Future;

@Local
public interface AsyncWorkerEjbLocal {
  void executeAsync();
  Future<Person> createAsyncPerson(final String name);
}
