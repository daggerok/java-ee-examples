package daggerok.app.events

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.*
import java.util.Collections.singletonList
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import javax.annotation.Resource
import javax.ejb.Stateless
import javax.enterprise.concurrent.ManagedExecutorService
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.context.Initialized
import javax.enterprise.context.RequestScoped
import javax.enterprise.event.Observes
import javax.enterprise.inject.Produces
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType

@ApplicationScoped
class KafkaConfigurationFactory {

  private lateinit var props: Properties

  @PostConstruct
  fun configure() {
    props = Properties()
  }

  fun initialized(@Observes @Initialized(ApplicationScoped::class) init: Any) {
/*
    val stream = KafkaConfigurationFactory::class.java
        .getResource("/kafka.properties")
        .openStream()
*/
    val stream = KafkaConfigurationFactory::class.java
        .getResourceAsStream("/kafka.properties")

    stream.use { inputStream ->
      props.load(inputStream)
    }
  }

  @Produces
  @RequestScoped
  fun kafkaProperties(): Properties {
    val kafkaProperties = Properties()
    kafkaProperties.putAll(props)
    return kafkaProperties
  }
}

@Stateless
@Path("kafka")
@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
class KafkaPropertiesQueryProjectionResource {

  companion object {
    val log = LoggerFactory.getLogger(KafkaPropertiesQueryProjectionResource::class.java)
  }

  @Inject
  private lateinit var kafkaProperties: Properties

  @Resource
  private lateinit var executorService: ManagedExecutorService

  lateinit var producer: KafkaProducer<String, String>
  lateinit var consumer: KafkaConsumer<String, String>
  val atomicBool = AtomicBoolean()

  @PostConstruct
  fun initKafka() {

    kafkaProperties.put("group.id", "group")

    producer = KafkaProducer(kafkaProperties)

    consumer = KafkaConsumer(kafkaProperties)
    consumer.subscribe(singletonList("topic"))

    try {
      executorService.execute({
        log.info("execute...")
        while (atomicBool.get()) {
          val records = consumer.poll(kotlin.Long.MAX_VALUE)
          records.forEach {
            log.info("consumed: $it")
          }
          consumer.commitSync()
          TimeUnit.SECONDS.sleep(1)
          log.info("waiting...")
        }
      })
    } finally {
      log.info("finally...")
      consumer.close()
//      atomicBool.set(true)
//      consumer.wakeup()
    }
  }

  @PreDestroy
  fun shutdownKafka() {
    producer.close()
    consumer.close()
  }

  @GET
  @Path("query")
  fun getAll() = kafkaProperties

  @GET
  @Path("produce")
  fun produce() {
    log.info("sending message...")
    val producerRecord = ProducerRecord<String, String>("topic", "hello ${Instant.now()}!")
    producer.send(producerRecord)
    producer.flush()
    log.info("message sent.")
  }
}
//
//class HelloEventDeserializer : Deserializer<HelloEvent> {
//
//  companion object {
//    val log = LoggerFactory.getLogger(HelloEventDeserializer::class.java)
//  }
//
//  override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {}
//
//  override fun deserialize(topic: String?, data: ByteArray?): HelloEvent? {
//    try {
//      ByteArrayInputStream(data).use { input ->
//        log.info("data {}", data)
//        val jsonObject = Json.createReader(input).readObject()
//        log.info("jsonObject {}", jsonObject)
//        val eventClass = Class.forName(jsonObject.getString("class"))
//        log.info("eventClass {}", eventClass)
//        if (eventClass.isAssignableFrom(HelloEvent::class.java)) {
//          val json = jsonObject.getJsonObject("data")
//          log.info("json[data] {}", json)
//          val idString = json.getString("id")
//          log.info("idString {}", idString)
//          val id = UUID.fromString(idString)
//          log.info("id {}", id)
//          val atString = json.getString("at")
//          log.info("atString {}", atString)
//          val atLong = Long.valueOf(atString)
//          log.info("atLong {}", atLong)
//          val at = Instant.ofEpochMilli(atLong)
//          log.info("at {}", at)
//          val constructor = eventClass.getConstructor()
//          return HelloEvent(id, at)
//        }
//      }
//    } catch (e: Exception) {
//      log.error("Could not deserialize event: {}", e.message, e)
//      throw SerializationException("Could not deserialize event", e)
//    }
//    return null
//  }
//
//  override fun close() {}
//}
//
//class UUIDAdapter : JsonbAdapter<UUID, String> {
//
//  @Throws(Exception::class)
//  override fun adaptToJson(uuid: UUID?): String = uuid.toString()
//
//  @Throws(Exception::class)
//  override fun adaptFromJson(string: String?): UUID = UUID.fromString(string)
//}
//
//class EventJsonbSerializer : JsonbSerializer<HelloEvent> {
//
//  override fun serialize(event: HelloEvent, generator: JsonGenerator, ctx: SerializationContext) {
//    generator.write("class", event.javaClass.canonicalName)
//    generator.writeStartObject("data")
//    ctx.serialize("data", event, generator)
//    generator.writeEnd()
//  }
//}
//
//class HelloEventSerializer : Serializer<HelloEvent> {
//
//  companion object {
//    val log = LoggerFactory.getLogger(HelloEventDeserializer::class.java)
//  }
//
//  override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {}
//
//  override fun serialize(topic: String?, event: HelloEvent?): ByteArray? {
//    try {
//
//      if (event == null) return null
//
//      val config = JsonbConfig()
//          .withAdapters(UUIDAdapter())
//          .withSerializers(EventJsonbSerializer())
//
//      val jsonb = JsonbBuilder.create(config)
//
//      return jsonb.toJson(event, HelloEvent::class.java).toByteArray(StandardCharsets.UTF_8)
//
//    } catch (e: Exception) {
//      log.error("Could not serialize event: {}", e.message, e)
//      throw SerializationException("Could not serialize event", e)
//    }
//
//  }
//
//  override fun close() {}
//}
