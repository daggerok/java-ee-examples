package daggerok.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@XmlAccessorType(FIELD)
@XmlType(
    name = "UserList",
    propOrder = { "users" }
)
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserList implements Serializable {

  private static final long serialVersionUID = 8395843069235961833L;

  @XmlElement(required = true)
  List<User> users;
}
