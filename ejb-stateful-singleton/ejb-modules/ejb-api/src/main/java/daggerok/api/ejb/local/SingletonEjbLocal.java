package daggerok.api.ejb.local;

public interface SingletonEjbLocal {
  void save(final String key, final String value);

  String find(final String key);
}
