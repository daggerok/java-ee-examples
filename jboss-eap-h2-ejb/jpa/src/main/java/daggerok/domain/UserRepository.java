package daggerok.domain;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Singleton
public class UserRepository {

  //  @PersistenceContext(unitName = "ExampleDS")
//  @PersistenceContext(name = "ExampleDS")
  @PersistenceContext
  EntityManager em;

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

  public int deleteByName(final String name) {
    return em.createQuery("delete from User u where lower(u.name) like lower(concat('%', :name,'%'))")
             .setParameter("name", name)
             .executeUpdate();
  }
  /* // shit approach:
  public void deleteByName(final String name) {
    val users = findByName(name);
    for (val user : users) {
      em.remove(user);
    }
  }
  */
}
