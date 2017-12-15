package daggerok.rpc.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.enterprise.context.ApplicationScoped;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@ApplicationScoped
@ConfigBundle("app-cfg")
@FieldDefaults(level = PRIVATE)
public class ConfigProps {

  String strng;
  Integer intgr;
  Boolean bln;
}
