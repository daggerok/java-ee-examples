package daggerok.data.impl;

import daggerok.data.DataSource;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@NoArgsConstructor
@ApplicationScoped
public class DataSourceImpl implements DataSource {

  private final static Map<String, String> db = new ConcurrentHashMap<>();

  @Override
  public String get(final @NotNull String key) {
    if (log.isInfoEnabled()) log.info("ds get {}", key);
    return db.getOrDefault(key, "");
  }

  @Override
  public void add(final @NotNull String key, final @NotNull String value) {
    if (log.isInfoEnabled()) log.info("ds add {}:{}", key, value);
    db.put(key, value);
  }
}
