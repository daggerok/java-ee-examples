package daggerok.soap;

import lombok.val;

import javax.jws.WebMethod;
import javax.jws.WebService;

import java.util.Objects;

import static java.lang.String.format;

@WebService
public class User {

  @WebMethod
  public String hey(final String name) {
    val finalName = Objects.isNull(name) || "".equals(name.trim())
        ? "guest" : name;
    return format("hola, %s!", finalName);
  }
}
