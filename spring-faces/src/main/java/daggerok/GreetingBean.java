package daggerok;

import lombok.Data;
import org.springframework.context.annotation.Scope;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Data
@Named
@ManagedBean
@SessionScoped
@Scope("session")
public class GreetingBean implements Serializable {
  private String name;
}
