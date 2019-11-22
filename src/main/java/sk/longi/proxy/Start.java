package sk.longi.proxy;

import sk.longi.proxy.proxyparser.boundary.JsonStrings;
import sk.longi.proxy.proxyparser.control.JsonParser;
import sk.longi.proxy.proxyparser.entity.ListIpHost;
import sk.longi.proxy.proxyparser.entity.ProxyFull;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.inject.Inject;
import javax.servlet.ServletContextListener;
import javax.validation.constraints.Max;

import java.util.List;

import static javax.ejb.ConcurrencyManagementType.BEAN;


@Singleton
@Startup
public class Start  {
    @Inject
    @ListIpHost
    private List<ProxyFull> proxyList;

    @EJB
    JsonParser jsonParser;
    @Inject
    JsonStrings jsonStrings;



    @Schedule(second="0", minute="*", hour="*",persistent = false)

    protected void initParsringAndVerify(){
       List<String> jsons = jsonStrings.getJsonsFromWeb();
        for (String json : jsons) {

            if(proxyList.size() >2 ) break;
            jsonParser.parse(json);
        }

    }

//    @PreDestroy
//    void atShutdown() { ... }
}
