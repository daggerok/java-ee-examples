package com.github.daggerok.dwr.bean;

import com.github.daggerok.dwr.MyCdiBeanCreator;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import java.io.Serializable;

@RemoteProxy(creator = MyCdiBeanCreator.class)
public class MyBean implements Serializable {

  @RemoteMethod
  public String myMethod() {
    return "Hola!";
  }
}
