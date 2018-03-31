package daggerok;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@FacesConfig
@ApplicationScoped
@ApplicationPath("")
public class App extends Application {}
