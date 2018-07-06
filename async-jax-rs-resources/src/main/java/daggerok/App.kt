package daggerok

import javax.ejb.Stateless
import javax.ejb.TransactionAttribute
import javax.ejb.TransactionAttributeType
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.json.Json
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.ws.rs.*
import javax.ws.rs.core.Application
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@ApplicationScoped
@ApplicationPath("")
class App : Application()

@Stateless
@Path("")
@Produces(APPLICATION_JSON)
class ApiResource : Application() {

  @GET
  @Path("")
  fun api(@Context uriInfo: UriInfo): Response {

    return Response.ok(
        Json.createArrayBuilder()
            .add(uriInfo.baseUriBuilder
                .path(AppResource::class.java)
                .path(AppResource::class.java, "getAll")
                .build()
                .toString())
            .add(uriInfo.baseUriBuilder
                .path(AppResource::class.java)
                .path(AppResource::class.java, "get")
                .build("\$id")
                .toString())
            .add(uriInfo.baseUriBuilder
                .path(AppResource::class.java)
                .path(AppResource::class.java, "post")
                .build()
                .toString())
            .add(uriInfo.baseUriBuilder
                .path(ApiResource::class.java)
                .path(ApiResource::class.java, "api")
                .build()
                .toString())
            .add(uriInfo.baseUriBuilder
                .path(HealthResource::class.java)
                .path(HealthResource::class.java, "health")
                .build()
                .toString())
            .build())
        .build()
  }
}

@Path("")
@Stateless
@Produces(APPLICATION_JSON)
class HealthResource {

  @Context
  internal var uriInfo: UriInfo? = null

  @GET
  @Path("health")
  fun health(): Response = Response.ok(
      Json.createObjectBuilder()
          .add("status", "UP")
          .add("_self", uriInfo!!.baseUriBuilder
              .path(HealthResource::class.java)
              .path(HealthResource::class.java, "health")
              .build()
              .toString())
          .build())
      .build()
}

@Stateless
@Path("items")
@Produces(APPLICATION_JSON)
class AppResource {

  @Context
  lateinit var uriInfo: UriInfo

  @Inject
  lateinit var itemRepository: ItemRepository

  @GET
  @Path("")
  fun getAll(): Response = Response.ok(itemRepository.findAll()).build()

  @GET
  @Path("{id}")
  fun get(@PathParam("id") id: Long): Response =
      try { Response.ok(itemRepository.findOne(id)).build() }
      catch (e: NoResultException) { Response.status(Response.Status.NOT_FOUND).build() }

  @POST
  @Path("")
  fun post(@Valid @NotNull item: Item): Response =
      Response.created(
          uriInfo.baseUriBuilder
              .path(AppResource::class.java)
              .path(AppResource::class.java, "get")
              .path("{id}")
              .build(itemRepository.save(item).id))
          .build()
}

@TransactionAttribute(TransactionAttributeType.NEVER)
class ItemRepository {

  @PersistenceContext
  lateinit var em: EntityManager

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

@Entity
data class Item(
    @Id @GeneratedValue var id: Long? = null,
    @NotNull var value: String? = null
)
