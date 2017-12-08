package daggerok.business;

import javax.faces.bean.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;

@ApplicationScoped
public class InfoService {

  public JsonObject getAvailableResources() {
    return Json.createObjectBuilder()
               .add("Home page", "http://0.0.0.0:8080/app/")
               .add("API v1", "http://0.0.0.0:8080/app/v1/api")
               .build();
  }
}
