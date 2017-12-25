package daggerok.ejb.impl.domain;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 6434715326731042687L;

//  @Id
//  //@GeneratedValue(strategy = GenerationType.AUTO)/*(generator = "UUID")
//  @GeneratedValue(generator = "UUID2")
//  @GenericGenerator(
//      name = "UUID2",
//      strategy = "org.hibernate.id.UUIDGenerator"
//  )
//
//  /*@GeneratedValue
//  @Column( columnDefinition = "uuid", updatable = false )
//  UUID id*//* = UUID.randomUUID();*/
//  @Setter @Getter
//  /*@GeneratedValue(generator="system-uuid")
//  @GenericGenerator(name="system-uuid", strategy = "uuid"*//*, strategy = "org.hibernate.id.UUIDGenerator"*//*)
//  */UUID id;

  @Id
  @GeneratedValue(generator = "UUID2")
  @GenericGenerator(name = "UUID2", strategy = "org.hibernate.id.UUIDGenerator")
  UUID id;
  @NonNull String name;
}
