package sk.longi.proxy.websocket.entity;

import sk.longi.proxy.proxyparser.entity.IpHost;
import sk.longi.proxy.proxyparser.entity.ListIpHost;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@MessageQ
public  class Message {

    Integer  proxysLenght = 0;
    String proxyToGet = "a";
    String proxyNew= "b" ;


    public Message() {
    }

    @Inject
    @ListIpHost
    private List<IpHost> proxyList;


    public void setProxyToGet(String proxyToGet) {




        this.proxyToGet = proxyToGet;
    }

    public Integer getProxysLenght() {
        return proxysLenght;
    }

    public String getProxyToGet() {
        return proxyToGet;
    }

    public String getProxyNew() {
        return proxyNew;
    }

    public void setMessage( ){

        this.proxysLenght = proxyList.size();
        this.proxyNew = proxyList.get(this.proxysLenght-1).toString();


    }
}
