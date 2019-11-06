package sk.longi.proxy.proxyparser.boundary;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/webapi/*")
public class RootResource extends Application {
}
