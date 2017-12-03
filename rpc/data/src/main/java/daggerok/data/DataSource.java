package daggerok.data;

import javax.validation.constraints.NotNull;

public interface DataSource {

  String get(@NotNull final String key);

  void add(@NotNull final String key, @NotNull final String value);
}
