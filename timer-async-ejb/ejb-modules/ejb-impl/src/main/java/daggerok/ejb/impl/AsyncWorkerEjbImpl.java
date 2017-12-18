package daggerok.ejb.impl;

import daggerok.api.ejb.local.AsyncWorkerEjbLocal;
import lombok.SneakyThrows;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.TimeUnit;

@Stateless
public class AsyncWorkerEjbImpl implements AsyncWorkerEjbLocal {

  @SneakyThrows
  @Asynchronous
  public void executeAsync() {
    System.out.println(this + " - start async");
    Thread.sleep(3456);
    System.out.println(this +" - async done.");
  }
}
