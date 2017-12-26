package daggerok.ejb.impl;

import daggerok.ejb.impl.domain.User;
import lombok.SneakyThrows;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.concurrent.Future;

@Stateless
@LocalBean
public class AsyncWorkerEjbImpl {

  @Asynchronous
  @SneakyThrows
  public void executeAsync() {
    System.out.println(this + " - start async");
    Thread.sleep(3456);
    System.out.println(this + " - async done.");
  }

  @Asynchronous
  @SneakyThrows
  public Future<User> createAsyncPerson(final String name) {
    System.out.println(this + " - async person start");
    final User person = new User(name);
    Thread.sleep(3456);
    System.out.println(this + " - async person done.");
    return new AsyncResult<User>(person);
  }
}
