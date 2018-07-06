package daggerok

import javax.ejb.Stateless
import javax.ejb.TransactionAttribute
import javax.ejb.TransactionAttributeType
import javax.inject.Inject
import javax.persistence.*
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@Entity
data class Item(
    @Id @GeneratedValue var id: Long? = null,
    var value: String? = null
)

@TransactionAttribute(TransactionAttributeType.NEVER)
class ItemRepository {

  @PersistenceContext lateinit var em: EntityManager

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  fun save(item: Item): Item {
    em.persist(item)
    return item
  }

  fun findOne(id: Long): Item? = em.createQuery("select i from Item i where i.id = :id", Item::class.java)
      .setParameter("id", id)
      .singleResult

  fun findAll() = em.createQuery("select i from Item i", Item::class.java).resultList!!
}

@Stateless
@Path("items")
@Produces(APPLICATION_JSON)
class AppResource {

  @Context lateinit var uriInfo: UriInfo
  @Inject lateinit var itemRepository: ItemRepository

  @GET
  @Path("")
  fun getAll(): Response = Response.ok(itemRepository.findAll()).build()

  @GET
  @Path("{id}")
  fun get(@PathParam("id") id: Long): Response {
    val item = try { itemRepository.findOne(id) }
    catch (e: NoResultException) { return Response.status(Response.Status.NOT_FOUND).build() }
    return Response.ok(item).build()
  }

  @POST
  @Path("")
  fun post(@Valid item: Item): Response =
      Response.created(
          uriInfo.baseUriBuilder
              .path(AppResource::class.java)
              .path(AppResource::class.java, "get")
              .path("{id}")
              .build(itemRepository.save(item).id))
          .build()
}
