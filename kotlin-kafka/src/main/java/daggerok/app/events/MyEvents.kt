package daggerok.app.events

import org.slf4j.LoggerFactory
import java.io.Serializable
import java.time.Instant
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import javax.ejb.Singleton
import javax.ejb.Startup
import javax.ejb.Stateless
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Event
import javax.enterprise.event.Observes
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

abstract class MyEvent : Serializable
data class HelloEvent(var id: UUID? = UUID.randomUUID(),
                      var at: Instant? = Instant.now()) : MyEvent()

@ApplicationScoped
class HelloEventsContainerFactory {

  companion object {
    val helloEvents = ConcurrentHashMap<UUID, MyEvent>()
  }

  @HelloEventsMap
  @javax.enterprise.inject.Produces
  fun helloEvents() = helloEvents
}

fun UriInfo.to(resource: Class<*>, methodName: String, id: UUID?) = this
    .absolutePathBuilder
    .path(resource, methodName)
    .build(id)
    .toString()

@Stateless
@Path("hello")
@Produces(APPLICATION_JSON)
class HelloEventCommandResource/*(@Inject var events: Event<HelloEvent>? = null)*/ {

  companion object {
    val log = LoggerFactory.getLogger(HelloEventCommandResource::class.java)
  }

  @Inject
  private lateinit var events: Event<HelloEvent>

  @POST
  @Path("")
  fun post(@Context uri: UriInfo): Response {
    val helloEvent = HelloEvent()
    log.info("sending {}", helloEvent)
    events.fire(helloEvent)
    return Response
        .accepted()
        .header("location", uri.to(HelloEventQueryProjectionResource::class.java, "get", helloEvent.id))
        .build()
  }
}

@Startup
@Singleton
class HelloEventHandler {

  companion object {
    val log = LoggerFactory.getLogger(HelloEventHandler::class.java)
  }

  @Inject @HelloEventsMap
  private lateinit var helloEvents: ConcurrentHashMap<UUID, MyEvent>

  fun on(@Observes helloEvent: HelloEvent) {
    helloEvents.put(helloEvent.id!!, helloEvent)
    log.info("received {}", helloEvent)
  }
}

@Stateless
@Path("hello")
@Produces(APPLICATION_JSON)
class HelloEventQueryProjectionResource {

  @Inject @HelloEventsMap
  private lateinit var helloEvents: ConcurrentHashMap<UUID, MyEvent>

  @GET
  @Path("query")
  fun getAll() = helloEvents

  @GET
  @Path("query/{id}")
  fun get(@PathParam("id") id: String) =
      helloEvents[UUID.fromString(id)]
}
