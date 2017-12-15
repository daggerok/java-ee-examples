package daggerok;

import javax.faces.bean.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationScoped
@ApplicationPath("v1")
public class XmlRpcServerApplicationConfiguration extends Application {}
