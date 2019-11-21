package sk.longi.proxy.proxyparser.entity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class Producers {

    @Produces
    @ApplicationScoped
    @ListIpHost
    public List<ProxyFull> produce(){
        return new ArrayList<>();
    }
}
