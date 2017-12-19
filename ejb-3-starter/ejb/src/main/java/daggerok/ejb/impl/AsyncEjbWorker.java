package daggerok.ejb.impl;

import lombok.SneakyThrows;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.concurrent.Future;

import static java.lang.String.format;

@Stateless
@LocalBean
public class AsyncEjbWorker {

  @Asynchronous
  @SneakyThrows
  public Future<String> sendMessage(final String name) {
    System.out.println(this + " - async person start");
    Thread.sleep(1234);
    System.out.println(this + " - async person done.");
    return new AsyncResult<String>(format("hi, %s!", name));
  }
}
