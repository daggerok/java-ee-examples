package daggerok;

import daggerok.rest.Resource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

public class App {
  public static void main(String[] args) throws Exception {

    final Swarm swarm = new Swarm();
    final JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
    final boolean recursive = true;

    deployment.addPackages(recursive, App.class.getPackage());
    deployment.addClass(Resource.class);
    swarm.start(deployment);
  }
}
