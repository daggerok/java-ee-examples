package daggerok.app.events

import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import javax.ejb.ConcurrencyManagement
import javax.ejb.ConcurrencyManagementType
import javax.ejb.Singleton
import javax.ejb.Startup
import javax.enterprise.event.Observes
import javax.inject.Inject

@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
class EventAggregator {

  companion object {
    val log = LoggerFactory.getLogger(EventAggregator::class.java)
  }

  @Inject @HelloEventsMap
  private lateinit var myEventsMap: ConcurrentHashMap<UUID, MyEvent>

  fun on(@Observes helloEvent: HelloEvent) {
    log.info("? {}", helloEvent)
    myEventsMap.put(helloEvent.id!!, helloEvent)
  }
}
