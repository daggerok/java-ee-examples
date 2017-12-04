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
    name = "GetUsersResponseMessage",
    propOrder = { "users" }
)
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class GetUsersResponseMessage implements Serializable {

  private static final long serialVersionUID = -3176585323774112712L;

  @XmlElement(required = true)
  UserList users;
}
