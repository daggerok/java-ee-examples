package daggerok.app.item;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

//tag::content[]
@Stateful
@TransactionAttribute(NOT_SUPPORTED)
public class ItemRepository {

  @PersistenceContext
  EntityManager em;

  @TransactionAttribute(REQUIRES_NEW)
  public Item save(final Item item) {
    em.persist(item);
    return item;
  }

  public Item findOne(final Long id) {
    return em.find(Item.class, id);
  }

  public List<Item> findAll() {
    return em.createQuery("select i from Item i", Item.class)
             .getResultList();
  }
}
//end::content[]
