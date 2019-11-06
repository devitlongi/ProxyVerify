package sk.longi.proxy.proxyparser.boundary;

import sk.longi.proxy.verify.boundary.IpGet;
import sk.longi.proxy.verify.boundary.Ipifi;
import sk.longi.proxy.proxyparser.entity.JsonParser;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/proxy")
public class MyResource {


    @Inject
    @Ipifi

    IpGet ipGet;

    @Inject
    JsonParser jsonParser;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
        jsonParser.parsreToProxy();
        if (jsonParser.getIpHosts().isEmpty()) {
            return "No Matches";
        }
        return jsonParser.getIpHosts().get(1).toString();
    }

    @Path("/test")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIttest() {

        return System.getProperty("http.proxyHost") + "\t" + System.getProperty("http.proxyPort") + "---------------" + System.getProperties();
    }
}
