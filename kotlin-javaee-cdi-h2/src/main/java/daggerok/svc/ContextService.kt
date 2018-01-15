package daggerok.svc

import daggerok.domain.User
import daggerok.domain.UserRepository
import javax.inject.Inject
import javax.inject.Singleton
import javax.json.Json
import javax.json.JsonArrayBuilder
import javax.servlet.ServletContext
import javax.ws.rs.core.HttpHeaders

fun HttpHeaders.toJsonBuilder(): JsonArrayBuilder {

  val jsonArrayBuilder = Json.createArrayBuilder()

  this.requestHeaders
      .map { it.key to it.value }
      .map {
        Json.createObjectBuilder()
            .add(it.first, it.second.toString())
      }
      .forEach { jsonArrayBuilder.add(it) }

  return jsonArrayBuilder
}

@Singleton
class ContextService {

  @Inject private lateinit var userRepository: UserRepository

  fun parse(headers: HttpHeaders, ctx: ServletContext) = Json
      .createObjectBuilder()
      .add("contextPath", ctx.contextPath ?: "nope...")
      .add("headers", headers.toJsonBuilder())
      .build()
      .toString()

  fun getAll() = userRepository.findAll()

  fun saveSome(user: User) = userRepository.save(user)
}
