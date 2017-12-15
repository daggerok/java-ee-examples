package daggerok.xmlrpc;

import daggerok.xmlrpc.cfg.Config;
import daggerok.xmlrpc.handlers.GreaterHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.WebServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Slf4j
@Startup
@Singleton
public class XmpRpcServer {

  @Inject
  Config config;

  WebServer webServer;

  @SneakyThrows
  @PostConstruct
  public void startXmlRpcServer() {

    this.webServer = new WebServer(config.getPort());
    val xmlRpcServer = webServer.getXmlRpcServer();
    val propertyHandlerMapping = new PropertyHandlerMapping();

    propertyHandlerMapping.addHandler("daggerok.xmlrpc.handlers.GreaterHandler", GreaterHandler.class);
    xmlRpcServer.setHandlerMapping(propertyHandlerMapping);
    webServer.start();

    log.warn("xml rpc web server {} started on {} port", webServer, config.getPort());
  }

  @PreDestroy
  public void stopXmlRpcServer() {
    log.warn("shutting down {} server", webServer);
    webServer.shutdown();
  }
}
