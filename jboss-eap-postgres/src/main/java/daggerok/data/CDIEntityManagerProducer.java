package daggerok.data;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * see https://stackoverflow.com/questions/51756761/jboss-eap-7-1-spring-data-jpa-cdi-extension
 */
@Slf4j
@ApplicationScoped
public class CDIEntityManagerProducer {

  @Produces
  @Dependent
  @PersistenceUnit(unitName = "")
  EntityManagerFactory entityManagerFactory;

  @Produces
  @RequestScoped
  public EntityManager createEntityManager() {
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    log.debug("hi {}", entityManager);
    return entityManager;
  }

  public void close(@Disposes EntityManager entityManager) {
    log.debug("bye {}", entityManager);
    entityManager.close();
  }
}
