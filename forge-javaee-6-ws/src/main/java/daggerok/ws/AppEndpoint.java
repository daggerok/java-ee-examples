package daggerok.ws;

import daggerok.ws.domain.Factory;
import daggerok.ws.domain.GetUsersResponseMessage;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

  @Inject
  private Factory factory;

  @WebMethod(operationName = "GetUsers", action = "http://daggerok.github.io/javaee/jax-ws/api/v1/users/GetUsers")
  @WebResult(name = "output")
  @ResponseWrapper(
      localName = "GetUsersResponse",
      targetNamespace = "http://daggerok.github.io/javaee/jax-ws/api/v1/users",
      className = "daggerok.ws.domain.GetUsersResponse"
  )
  public GetUsersResponseMessage getUsers() {

    return new GetUsersResponseMessage()
        .setUsers(factory.getUsers());
  }
}
