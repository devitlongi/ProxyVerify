package sk.longi.proxy.websocket.entity;

import sk.longi.proxy.proxyparser.entity.ProxyFull;
import sk.longi.proxy.proxyparser.entity.ListIpHost;

import javax.enterprise.context.ApplicationScoped;
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
    private List<ProxyFull> proxyList;


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

    public void setProxysLenght(Integer proxysLenght) {
        this.proxysLenght = proxysLenght;
    }

    public void setProxyNew(String proxyNew) {
        this.proxyNew = proxyNew;
    }


}
