package daggerok.ejb.impl.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 6434715326731042687L;

  UUID id = UUID.randomUUID();
  @NonNull
  String name;
}
