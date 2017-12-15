package daggerok;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.URL;

/**
 * Hello world!
 */
public class ClientApp {

  static final String url = "http://127.0.0.1:8081";

  @SneakyThrows
  public static void main(String[] args) {
    System.out.println("XML-RPC Client call to: " + url);
    val xmlRpcClientConfigImpl = new XmlRpcClientConfigImpl();
    xmlRpcClientConfigImpl.setServerURL(new URL(url));
    val xmlRpcClient = new XmlRpcClient();
    xmlRpcClient.setConfig(xmlRpcClientConfigImpl);
    val request = null == args || args.length < 1 ? new String[]{ "Guest" } : args;
    val response = xmlRpcClient.execute("daggerok.xmlrpc.handlers.GreaterHandler.handleSayHello", request);
//    String response = (String) xmlRpcClient.execute("daggerok.GreaterHandler.message", request);
    System.out.println("Message: " + response);
  }
}
