package com.github.daggerok.dwr;

import org.directwebremoting.extend.AbstractCreator;
import org.directwebremoting.extend.Creator;
import org.directwebremoting.util.LocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Set;

/**
 * DWR CDI integration.
 */
@ApplicationScoped
public class MyCdiBeanCreator extends AbstractCreator implements Creator {

  private final static Logger log = LoggerFactory.getLogger(MyCdiBeanCreator.class);

  @Inject
  BeanManager beanManager;

  private Class<?> type;
  private String cdiBeanName;

  @Override
  public Class<?> getType() {
    return type;
  }

  /**
   * DWR Instance creator is using method org.directwebremoting.Creator#getInstance() if available
   */
  @Override
  public Object getInstance() throws InstantiationException {

    try {

      if (null == beanManager) {
        beanManager = lookupBeanManagerFromJNDI();
      }

      // Get a default managedBeanName if cdiBeanName is blank
      if (cdiBeanName == null || cdiBeanName.equals("")) {
        cdiBeanName = getDefaultManagedBeanName(); // may be cdiBeanName not equal with real cdi beanName
      }

      // Get cdi managedBeanName by beanName
      Set<Bean<?>> cdiBeans = beanManager.getBeans(cdiBeanName);
      if (!cdiBeans.isEmpty()) return aBean(cdiBeans);

      else {
        log.error("Fail to get CDI bean with cdiBeanName '{}'", cdiBeanName);
        return null;
      }
    }

    catch (Exception ex) {
      log.error(ex.getLocalizedMessage(), ex);
      throw new InstantiationException("Illegal Access to default constructor on " + type.getName());
    }
  }

  public void setClass(String className) {
    try {
      type = LocalUtil.classForName(className);
      if (getJavascript() == null) {
        setJavascript(type.getSimpleName());
      }
    } catch (ExceptionInInitializerError ex) {
      throw new IllegalArgumentException("Error loading class: " + className, ex);
    } catch (ClassNotFoundException ex) {
      throw new IllegalArgumentException("Class not found: " + className, ex);
    }
  }

  public void setClassName(String className) {
    setClass(className);
  }

  public String getClassName() {
    return getType().getName();
  }

  public void setCdiBeanName(String name) {
    this.cdiBeanName = name;
  }

  public String getCdiBeanName() {
    return this.cdiBeanName;
  }

  /* Private API: Helper methods */

  private BeanManager lookupBeanManagerFromJNDI() throws NamingException {
    // 1) Lookup BeanManger from JNDI
    InitialContext initCtx = new InitialContext();

    try { // 1.1) lookup JNDI BeanManager for jboss
      return (BeanManager) initCtx.lookup("java:comp/BeanManager");
    }
    catch (NamingException e) {
      log.warn("cant get BeanManager from java:comp/");
    }

    if (null == beanManager) { // 1.2) lookup JNDI BeanManager for tomcat
      try {
        beanManager = (BeanManager) initCtx.lookup("java:comp/env/BeanManager");
      }
      catch (NamingException e) {
        log.warn("Cannot get BeanManager from java:comp/env/");
      }
    }

    if (null == beanManager) {
      log.warn("CDI not support on this environment.");
    }

    return beanManager;
  }

  private String getDefaultManagedBeanName() {
    String className = type.getSimpleName();
    return className.substring(0, 1).toLowerCase() + className.substring(1);
  }

  private Object aBean(Set<Bean<?>> cdiBeans) {
    Bean<?> cdiBean = cdiBeans.iterator().next();
    CreationalContext<?> crtCtx = beanManager.createCreationalContext(cdiBean);
    return beanManager.getReference(cdiBean, type, crtCtx);
  }
}
