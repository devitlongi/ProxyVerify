package sk.longi.proxy.proxyparser.boundary;

import sk.longi.proxy.proxyparser.entity.ProxyFull;
import sk.longi.proxy.proxyparser.entity.ListIpHost;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/proxy")
public class MyResource {




    @Inject
    @ListIpHost
    private List<ProxyFull> proxyList;



    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
//        jsonParser.parsreToProxy();


        if (proxyList.isEmpty()||proxyList.size()<3) {
            return  Response.ok("No matches").build();
        }

        return Response.ok(proxyList.get(1).toString()+"\n"+proxyList.get(2).toString()).build();
    }

    @Path("/test")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIttest() {

        return System.getProperty("http.proxyHost") + "\t" + System.getProperty("http.proxyPort") + "---------------" + System.getProperties();
    }
}
