package daggerok.xmlrpc;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class XmlRpcClient {

  @SneakyThrows
  public static String sayHi(final String name) {

    val xmlRpcClientConfigImpl = new XmlRpcClientConfigImpl();
    xmlRpcClientConfigImpl.setServerURL(Config.getUrl());

    val xmlRpcClient = new org.apache.xmlrpc.client.XmlRpcClient();
    xmlRpcClient.setConfig(xmlRpcClientConfigImpl);

    val request = new String[]{ null == name || "".equals(name) ? "Guest" : name };
    val response = xmlRpcClient.execute("daggerok.xmlrpc.handlers.GreaterHandler.handleSayHello", request);

    return String.valueOf(response);
  }
}
