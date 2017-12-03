package daggerok.rpc.server.data;

import daggerok.data.DataSource;
import daggerok.rpc.api.Service;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.rmi.RemoteException;

@Slf4j
@NoArgsConstructor
@ApplicationScoped
public class InMemoryRepositoryServiceImpl implements Service {

  @Inject
  DataSource dataSource;

  @Override
  public String find(final String id) throws RemoteException {
    if (log.isInfoEnabled()) log.info("repo find {}", id);
    return dataSource.get(id);
  }

  @Override
  public void save(final String id, final String value) throws RemoteException {
    if (log.isInfoEnabled()) log.info("repo save {}:{}", id, value);
    dataSource.add(id, value);
  }
}
