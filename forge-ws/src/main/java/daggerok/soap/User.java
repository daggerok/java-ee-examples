package daggerok.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import static java.lang.String.format;

@WebService
public class User {

  @WebMethod
  public String hey(final String name) {
    return format("hola, %s!", name);
  }
}
