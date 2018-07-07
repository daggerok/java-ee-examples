package daggerok.app;

import daggerok.aop.LoggerInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

//tag::content[]
@Stateless
@Interceptors(LoggerInterceptor.class)
public class SomeBusinessLogic {

  public void doSomething() {}
}
//end::content[]
