package daggerok.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import static java.lang.String.format;

@Named
@Component
@ManagedBean
@ApplicationScoped
@Scope("singleton")
public class MessageService {

  public String sayHi(final String name) {
    final String guest = null == name || "".equals(name) ? "Guest" : name;
    return format("Hello, %s!", guest);
  }
}
