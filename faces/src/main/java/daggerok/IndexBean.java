package daggerok;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class IndexBean implements Serializable {

  private String name;
  private String message;

  public void createMessage() {
    message = null == name || "".equals(name.trim())
        ? "" : "Hello, " + name.trim() + "!";
    setName("");
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getMessage() {
    return message;
  }
}
