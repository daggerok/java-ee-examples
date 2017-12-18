package daggerok.ejb.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import java.util.Date;

import static java.lang.String.format;

@Stateless
public class EjbImpl {

  static final String jobName = "TimerJob";

  // lifecycle
  @EJB
  AsyncWorkerEjbImpl asyncWorker;
  @Resource
  SessionContext sessionContext;

  // impl
  TimerHandle timerHandle;

  @PostConstruct
  public void postConstruct() {
    System.out.println(format("%s created.", getClass().getSimpleName()));
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println(format("%s destroying...", getClass().getSimpleName()));
  }

  public void start() {
    System.out.println("starting job...");

    if (null != timerHandle) {
      System.err.println("job already started!");
      return;
    }

    this.timerHandle = sessionContext.getTimerService()
                                     .createTimer(new Date(), 321, jobName)
                                     .getHandle();
  }

  @Timeout
  public void invokeJob(final Timer timer) {
    if (null == timer) {
      System.err.println("timer cannot be null!");
      return;
    }

    System.out.println(this + " - before execution");

    asyncWorker.executeAsync();

    if (timer.getInfo().equals(jobName)) {
      System.out.println(format("next at: %s", timer.getNextTimeout()));
    }

    System.out.println(this + " - after execution\n");
  }

  public void stop() {
    System.out.println("stopping job...");

    if (null == timerHandle) {
      System.err.println("job already stopped!");
      return;
    }

    timerHandle.getTimer().cancel();
    timerHandle = null;
  }
}
