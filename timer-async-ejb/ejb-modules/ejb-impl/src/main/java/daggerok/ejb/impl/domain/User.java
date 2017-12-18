package daggerok.ejb.impl.domain;

import daggerok.api.ejb.local.domain.Person;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User implements Person, Serializable {

  String id = UUID.randomUUID().toString();
  @NonNull String name;
}
