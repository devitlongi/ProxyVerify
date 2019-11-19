package sk.longi.proxy;

import sk.longi.proxy.proxyparser.control.JsonParser;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.inject.Inject;
import javax.servlet.ServletContextListener;

import static javax.ejb.ConcurrencyManagementType.BEAN;


@Singleton
@Startup
public class Start  {
    @Inject
    JsonParser jsonParser;



    @Schedule(second="3", minute="*", hour="*",persistent = false)
    protected void init(){

        jsonParser.parsreToProxy();
    }

//    @PreDestroy
//    void atShutdown() { ... }
}
