package daggerok.ejb.impl.domain;

import java.io.Serializable;
import java.util.UUID;

public interface Person extends Serializable {
  UUID getId();
}
