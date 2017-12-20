package daggerok.session;

import daggerok.api.ejb.local.StatefulEjbLocal;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Slf4j
@WebListener()
public class SessionListener implements HttpSessionListener, ServletContextListener, HttpSessionAttributeListener {

  public static final String statefulServiceName = "java:module/StatefulEjbImpl";

  // Public constructor is required by servlet spec
  public SessionListener() {
    log.info("created.");
  }

  // -------------------------------------------------------
  // HttpSessionListener implementation
  // -------------------------------------------------------
  @SneakyThrows
  public void sessionCreated(final HttpSessionEvent se) {
    /* Session is created. */
    val statefulService = (StatefulEjbLocal) new InitialContext().lookup(statefulServiceName);
    val httpSession = se.getSession();
    httpSession.setAttribute(statefulServiceName, statefulService);
    log.info("created session: {} with {} bean: {}.", httpSession.getId(), statefulServiceName, statefulService);
  }

  @SneakyThrows
  public void sessionDestroyed(final HttpSessionEvent se) {
    /* Session is destroyed. */
    val httpSession = se.getSession();
    val statefulService = (StatefulEjbLocal) httpSession.getAttribute(statefulServiceName);
    log.info("destroying session: {}, with {} bean: {}...", httpSession.getId(), statefulServiceName, statefulService);
    statefulService.removeBean();
  }

  // -------------------------------------------------------
  // ServletContextListener implementation
  // -------------------------------------------------------
  public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
  }

  public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
  }

  // -------------------------------------------------------
  // HttpSessionAttributeListener implementation
  // -------------------------------------------------------

  public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
  }

  public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
  }

  public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
  }
}
