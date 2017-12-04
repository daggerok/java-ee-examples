package daggerok.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@XmlAccessorType(FIELD)
@XmlType(
    name = "User",
    propOrder = {
        "id",
        "firstName",
        "lastName"
    }
)
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

  private static final long serialVersionUID = 1168720766170517366L;

  @XmlElement(required = true)
  String id;

  @XmlElement(required = true)
  String firstName;

  @XmlElement(required = true)
  String lastName;
}
