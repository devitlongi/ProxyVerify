package sk.longi.proxy;

import sk.longi.proxy.proxyparser.boundary.PROXYJsonStringsFromWeb;
import sk.longi.proxy.proxyparser.control.JsonParser;
import sk.longi.proxy.proxyparser.entity.ListIpHost;
import sk.longi.proxy.proxyparser.entity.ProxyFull;

import javax.ejb.*;
import javax.inject.Inject;

import java.util.List;


@Singleton
@Startup
public class Start  {
    @Inject
    @ListIpHost
    private List<ProxyFull> proxyList;

    @EJB
    JsonParser jsonParser;
    @Inject
    PROXYJsonStringsFromWeb jsonStrings;



    @Schedule(second="0", minute="*", hour="*",persistent = false)

    protected void initParsringAndVerify(){
       List<String> jsons = jsonStrings.getJsonsFromWeb();
        for (String json : jsons) {

//            if(proxyList.size() >2 ) break;
            jsonParser.parse(json);
        }

    }

//    @PreDestroy
//    void atShutdown() { ... }
}
