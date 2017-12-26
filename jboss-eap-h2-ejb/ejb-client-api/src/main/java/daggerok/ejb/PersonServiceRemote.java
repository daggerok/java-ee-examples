package daggerok.ejb;

import daggerok.domain.User;

import javax.ejb.Asynchronous;
import javax.ejb.Remote;
import java.util.List;
import java.util.concurrent.Future;

@Remote
public interface PersonServiceRemote {

  @Asynchronous
  Future<List<String>> removeAny(String name);

  @Asynchronous
  Future<User> createPerson(String name);

  @Asynchronous
  Future<List<User>> getPeople();
}
