package daggerok;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.WebServer;

/**
 * Hello world!
 */
public class ServerApp {

  static final int port = 8081;

  @SneakyThrows
  public static void main(String[] args) {
    System.out.println("starting XML-RPC Server...");

    val webServer = new WebServer(port);
    val xmlRpcServer = webServer.getXmlRpcServer();
    val propertyHandlerMapping = new PropertyHandlerMapping();

    propertyHandlerMapping.addHandler("daggerok.xmlrpc.handlers.GreaterHandler", GreaterHandler.class);
    xmlRpcServer.setHandlerMapping(propertyHandlerMapping);
    webServer.start();

    System.out.println("Server Started successfully on port " + port);
  }
}
