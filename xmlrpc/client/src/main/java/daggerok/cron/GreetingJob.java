package daggerok.cron;

import daggerok.xmlrpc.XmlRpcClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.ejb.*;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

@Data
@Slf4j
@Startup
@Singleton
@Lock(LockType.READ)
public class GreetingJob {

  final static List<String> names = asList("Вася", "Peter", "Ali-ba-ba");

  @Schedules({
      @Schedule(second = "*/3", minute = "*", hour = "*")
  })
  public void helloForever() {
    val name = names.get(new Random().nextInt(names.size()));
    val result = XmlRpcClient.sayHi(name);
    log.warn("cron: {}", result);
  }
}
