package daggerok.xmlrpc.handlers;

import lombok.extern.slf4j.Slf4j;

import static java.lang.String.format;

@Slf4j
public class GreaterHandler {

  public String handleSayHello(final String params) {
    if (log.isInfoEnabled()) log.info("handling '{}', params", params);
    return format("Hello, %s!", params);
  }
}
