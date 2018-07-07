package daggerok.aop;

import lombok.extern.slf4j.Slf4j;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

//tag::content[]
@Slf4j
public class LoggerInterceptor {

  @AroundInvoke
  public Object intercept(InvocationContext ic) throws Exception {
    log.info("intercepting: {} at: {}", ic.getContextData(), ic.getTimer());
    return ic.proceed();
  }
}
//end::content[]
