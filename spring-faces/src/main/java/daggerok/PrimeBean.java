package daggerok;

import daggerok.services.MessageService;
import lombok.Getter;
import org.springframework.context.annotation.Scope;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ManagedBean
@SessionScoped
@Scope("session")
public class PrimeBean implements Serializable {

  @Inject
  MessageService messageService;

  private String slogan = "O.o";

  public String getSlogan() {
    return messageService.sayHi(slogan);
  }
}
