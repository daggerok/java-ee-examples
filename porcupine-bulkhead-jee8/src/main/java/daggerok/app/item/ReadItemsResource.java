package daggerok.app.item;

import com.airhacks.porcupine.execution.boundary.Dedicated;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

//tag::read[]
@Slf4j
@Stateless
@Path("async-items")
@Produces(APPLICATION_JSON)
public class ReadItemsResource {

  @Inject
  ItemRepository itemRepository;

  @Inject
  @Dedicated("read-async-items")
  ExecutorService readExecutor;

  @GET
  @Path("read1")
  public void getAll1(@Suspended final AsyncResponse asyncResponse) {
    readExecutor.execute(() -> {
      final List<Item> result = itemRepository.findAll();
      asyncResponse.resume(result);
    });
  }

  @GET
  @Path("read2")
  public void getAll2(@Suspended final AsyncResponse asyncResponse) {
    CompletableFuture
        .supplyAsync(() -> itemRepository.findAll(), readExecutor)
        .thenAccept(asyncResponse::resume);
  }

  @GET
  @Path("read1/{id}")
  public void get1(@PathParam("id") final Long id, @Suspended final AsyncResponse asyncResponse) {
    readExecutor.execute(() -> {
      final Item result = itemRepository.findOne(id);
      asyncResponse.resume(result);
    });
  }

  @GET
  @Path("read2/{id}")
  public void get2(@PathParam("id") final Long id, @Suspended final AsyncResponse asyncResponse) {
    CompletableFuture.supplyAsync(() -> itemRepository.findOne(id), readExecutor)
                     .thenAccept(asyncResponse::resume);
  }
}
//end::read[]
