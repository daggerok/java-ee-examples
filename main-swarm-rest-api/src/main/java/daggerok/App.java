package daggerok;

/*
import daggerok.rest.CORSFilter;
import daggerok.rest.Resource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import java.util.stream.Stream;
*/

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;

@ApplicationScoped
@ApplicationPath("")
public class App {
/*
  public static void main(String[] args) throws Exception {

    final Swarm swarm = new Swarm();
    final JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
    final boolean recursive = true;

    deployment.addPackages(recursive, App.class.getPackage());
    Stream.of(Resource.class, CORSFilter.class)
          .forEach(deployment::addClass);
    swarm.start(deployment);
  }
*/
}
