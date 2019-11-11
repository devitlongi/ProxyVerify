package sk.longi.proxy;

import sk.longi.proxy.proxyparser.control.JsonParser;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContextListener;



@Singleton
public class Start implements javax.servlet.ServletContextListener {
    @Inject
    JsonParser jsonParser;


    @Schedule(second = "0")
    void atStartup() {
        jsonParser.parsreToProxy();
    }

//    @PreDestroy
//    void atShutdown() { ... }
}
