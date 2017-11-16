package daggerok

import javax.ejb.Stateless
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.json.Json
import javax.ws.rs.*
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType.APPLICATION_JSON

interface JavaEeService {
  fun hi(name: String): String
}

@Stateless
open class JavaEeServiceImpl : JavaEeService {
  override fun hi(name: String) = "hello, $name!"
}

@Path("")
@Produces(APPLICATION_JSON)
open class JavaEeResource {

  @Inject
  private lateinit var javaEeService: JavaEeService

  @GET
  open fun hiAll() = hiOne("world")

  @GET
  @Path("{name}")
  open fun hiOne(@PathParam("name") name: String) =
      Json.createObjectBuilder()
          .add("data", javaEeService.hi(name))
          .build()
}

@ApplicationScoped
@ApplicationPath("")
open class RestApplication : Application() {

  private val classes = setOf(
      JavaEeResource::class.java
  )

  override fun getClasses() = classes

  private val singletons = setOf(
      JavaEeServiceImpl()
  )

  override fun getSingletons() = singletons
}
