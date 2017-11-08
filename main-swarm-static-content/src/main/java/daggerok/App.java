package daggerok;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;

@ApplicationScoped
@ApplicationPath("")
public class App {
  public static void main(String[] args) throws Exception {

    final Swarm swarm = new Swarm();
    final JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
    final boolean recursive = true;

    deployment.addPackages(recursive, App.class.getPackage());
    deployment.addAsWebResource(new ClassLoaderAsset("static/index.html", App.class.getClassLoader()), "/index.html");
    deployment.staticContent("static");
    deployment.addAllDependencies(recursive);

    swarm.start();
    swarm.deploy(deployment);
  }
}
