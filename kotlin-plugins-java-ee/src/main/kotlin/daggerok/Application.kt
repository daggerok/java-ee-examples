package daggerok

import com.fasterxml.jackson.annotation.JsonProperty
import javax.ejb.Stateless
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.inject.Singleton
import javax.json.Json
import javax.json.JsonBuilderFactory
import javax.ws.rs.*
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType.APPLICATION_JSON

data class User(
    @param:JsonProperty("name") val name: String,
    @param:JsonProperty("email") val id: String
)

interface JavaEeService {
  fun hi(name: String): String
  fun all(): List<User>
}

@Stateless
@ApplicationScoped
class JavaEeServiceImpl : JavaEeService {
  override fun hi(name: String) = "hello, $name!"
  override fun all(): List<User> = listOf(
      User(id = "daggerok@gmailcom", name = "Maksim"),
      User("ololo", "trololo@lol.com")
  )
}

@Path("")
@Produces(APPLICATION_JSON)
class JavaEeResource @Inject @Singleton constructor(private val javaEeService: JavaEeService) {
  @GET
  fun all() = javaEeService.all()

  @GET
  @Path("hi")
  fun hiAll() = hiOne("world")

  @GET
  @Path("{name}")
  fun hiOne(@PathParam("name") name: String) =
      Json.createObjectBuilder()
          .add("data", javaEeService.hi(name))
          .build()
}

@ApplicationScoped
@ApplicationPath("")
class RestApplication : Application() {
  private val classes = setOf(
      JavaEeResource::class.java,
      JsonBuilderFactory::class.java
  )

  override fun getClasses() = classes

  private val singletons = setOf(
      JavaEeServiceImpl()
  )

  override fun getSingletons() = singletons
}
