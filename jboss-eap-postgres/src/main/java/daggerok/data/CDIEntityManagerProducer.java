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

//tag::content[]
/**
 * see https://stackoverflow.com/questions/51756761/jboss-eap-7-1-spring-data-jpa-cdi-extension
 */
@Slf4j
@ApplicationScoped
public class CDIEntityManagerProducer {

  @Produces
  @Dependent
  @PersistenceUnit(unitName = "")
  EntityManagerFactory emf;

  @Produces
  @RequestScoped
  public EntityManager em() {
    final EntityManager em = emf.createEntityManager();
    log.debug("hi {}", em);
    return em;
  }

  public void close(@Disposes EntityManager em) {
    log.debug("bye {}", em);
    em.close();
  }
}
//end::content[]
