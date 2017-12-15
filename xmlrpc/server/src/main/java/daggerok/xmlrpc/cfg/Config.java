package daggerok.xmlrpc.cfg;

import lombok.Data;

import javax.inject.Singleton;

@Data
@Singleton
public class Config {

  int port = 8081;
}
