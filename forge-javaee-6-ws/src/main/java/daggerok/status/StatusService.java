package daggerok.status;

import javax.inject.Singleton;

@Singleton
public class StatusService {

  public String status() {
    return "UP";
  }
}
