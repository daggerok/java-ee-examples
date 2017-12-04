package daggerok.ws;

import daggerok.domain.GetUsersResponseMessage;
import daggerok.services.UserFactoryImpl;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;

@Slf4j
@WebService(
    targetNamespace = "http://daggerok.github.io/javaee/jax-ws/api/v1",
    serviceName = "AppEndpoint",
    portName = "AppEndpointSOAP"
)
@ApplicationScoped
public class AppEndpoint {

  @WebMethod(operationName = "GetUsers", action = "http://daggerok.github.io/javaee/jax-ws/api/v1/users/GetUsers")
  @WebResult(name = "output")
  @ResponseWrapper(
      localName = "GetUsersResponse",
      targetNamespace = "http://daggerok.github.io/javaee/jax-ws/api/v1/users",
      className = "daggerok.domain.GetUsersResponse"
  )
  public GetUsersResponseMessage getUsers() {

    return new GetUsersResponseMessage()
        .setUsers(new UserFactoryImpl().getUsers());
  }
}
