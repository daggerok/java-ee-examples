package daggerok.rest

import daggerok.domain.User
import daggerok.svc.ContextService
import javax.ejb.Stateless
import javax.inject.Inject
import javax.servlet.ServletContext
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response

@Path("")
@Stateless
@Produces(APPLICATION_JSON)
class MainResource {

  @Inject private lateinit var svc: ContextService

  @GET
  @Path("")
  fun index(@Context headers: HttpHeaders, @Context ctx: ServletContext) = Response
      .ok(svc.parse(headers, ctx))
      .build()

  @GET
  @Path("get-all")
  fun getAll() = Response
      .ok(svc.getAll())
      .build()

  @POST
  @Path("save-some")
  fun saveSome() = Response
      .ok(svc.saveSome(createUser()))
      .build()

  private fun createUser(): User {
    val user = User()
    user.name = "ololo"
    return user
  }
}
