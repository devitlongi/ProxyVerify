package sk.longi.proxy;

import sk.longi.proxy.proxyparser.control.JsonParser;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;



@Startup
@Singleton

public class Main {
    @Inject
    JsonParser jsonParser;

    @PostConstruct
    void atStartup() { jsonParser.parsreToProxy();}

//    @PreDestroy
//    void atShutdown() { ... }
}
