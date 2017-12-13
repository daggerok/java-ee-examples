package daggerok.health;

import javax.inject.Singleton;

@Singleton
public class HealthService {

  public String status() {
    return "UP";
  }
}
