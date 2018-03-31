package daggerok.faces;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.annotation.MultipartConfig;
import java.io.Serializable;

@Named
@RequestScoped
@MultipartConfig
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
