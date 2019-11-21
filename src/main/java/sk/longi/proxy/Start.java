package sk.longi.proxy;

import sk.longi.proxy.proxyparser.boundary.JsonStrings;
import sk.longi.proxy.proxyparser.control.JsonParser;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.inject.Inject;
import javax.servlet.ServletContextListener;

import java.util.List;

import static javax.ejb.ConcurrencyManagementType.BEAN;


@Singleton
@Startup
public class Start  {
    @EJB
    JsonParser jsonParser;
    @Inject
    JsonStrings jsonStrings;



    @Schedule(second="0", minute="*", hour="*",persistent = false)

    protected void initParsringAndVerify(){

        List<String> jsons = jsonStrings.getJsonsFromWeb();
        for (String json : jsons) {
            jsonParser.parse(json);
        }

    }

//    @PreDestroy
//    void atShutdown() { ... }
}
