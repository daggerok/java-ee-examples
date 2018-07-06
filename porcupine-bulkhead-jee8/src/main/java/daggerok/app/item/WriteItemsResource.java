package daggerok.app.item;

import com.airhacks.porcupine.execution.boundary.Dedicated;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

//tag::write[]
@Slf4j
@Stateless
@Path("async-items")
@Produces(APPLICATION_JSON)
public class WriteItemsResource {

  @Inject
  ItemRepository itemRepository;

  @Inject
  @Dedicated("write-async-items")
  ExecutorService writeExecutor;

  @POST
  @Path("write1")
  public void post1(@Valid @NotNull final Item item, @Suspended final AsyncResponse asyncResponse) {
    writeExecutor.execute(() -> {
      final Item result = itemRepository.save(item);
      asyncResponse.resume(result);
    });
  }

  @POST
  @Path("write2")
  public void post2(@Valid @NotNull final Item item, @Suspended final AsyncResponse asyncResponse) {
    CompletableFuture.supplyAsync(() -> itemRepository.save(item), writeExecutor)
                     .thenAccept(asyncResponse::resume);
  }
}
//end::write[]
