package daggerok;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@Getter
@RequestScoped
@NoArgsConstructor
public class IndexBean implements Serializable {

  @Setter
  private String name;
  private String message;

  public void createMessage() {
    message = null == name || "".equals(name.trim())
        ? "" : "Hello, " + name.trim() + "!";
    setName("");
  }
}
