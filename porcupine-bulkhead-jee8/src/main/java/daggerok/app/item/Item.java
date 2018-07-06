package daggerok.app.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

//tag::content[]
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Item implements Serializable {

  private static final long serialVersionUID = 1466287048756540922L;

  @Id
  @GeneratedValue
  Long id;

  String value;
}
//end::content[]
