package daggerok.data;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import static daggerok.data.MyEntity.*;
import static javax.persistence.TemporalType.TIMESTAMP;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

//tag::content[]
@Data
@Entity
@Table(name = "my_entities")
@NoArgsConstructor(access = PROTECTED)
@RequiredArgsConstructor(staticName = "of")
@NamedQueries({
    @NamedQuery(name = FIND_ANY, query = "SELECT me FROM MyEntity me WHERE LOWER(me.data) LIKE LOWER(CONCAT('%',:q,'%'))"),
    @NamedQuery(name = FIND_ALL, query = "SELECT me FROM MyEntity me ORDER BY me.createdAt DESC"),
    @NamedQuery(name = COUNT, query = "SELECT COUNT(me) FROM MyEntity me")
})
public class MyEntity implements Serializable {

  public static final String COUNT = "MyEntity.count";
  public static final String FIND_ALL = "MyEntity.findAll";
  public static final String FIND_ANY = "MyEntity.findAny";

  @Id
  @Setter(PRIVATE)
  @GeneratedValue(generator = "UUID2")
  @GenericGenerator(name = "UUID2", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Column
  @NonNull
  String data;

  @Temporal(TIMESTAMP)
  @Column(name = "created")
  Date createdAt;

  @Temporal(TIMESTAMP)
  @Column(name = "updated")
  Date updatedAt;

  @PrePersist
  public void onCreate() {
    createdAt = new Date();
  }

  @PreUpdate
  public void onMerge() {
    final long time = new Date().getTime();
    createdAt = new Date(time);
    updatedAt = new Date(time);
  }
}
//end::content[]
