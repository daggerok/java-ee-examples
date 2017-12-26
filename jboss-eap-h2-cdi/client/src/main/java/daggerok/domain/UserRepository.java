package daggerok.domain;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.TransactionAttribute;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static javax.ejb.TransactionAttributeType.NEVER;
import static javax.ejb.TransactionAttributeType.REQUIRED;

@Slf4j
@Singleton
@TransactionAttribute(NEVER)
public class UserRepository {

  @PersistenceContext EntityManager em;

  @TransactionAttribute(REQUIRED)
  public User save(final User person) {
    log.info("saving user {}", person);
    em.persist(person);
    return person;
  }

  public List<User> findAll() {
    return em.createQuery("select u from User u", User.class)
             .getResultList();
  }

  public List<User> findByName(final String name) {
    return em.createQuery("select u from User u where lower(u.name) like lower(concat('%', :name,'%'))", User.class)
             .setParameter("name", name)
             .getResultList();
  }

  @TransactionAttribute(REQUIRED)
  public int deleteByName(final String name) {
    return em.createQuery("delete from User u where lower(u.name) like lower(concat('%', :name,'%'))")
             .setParameter("name", name)
             .executeUpdate();
  }
}
