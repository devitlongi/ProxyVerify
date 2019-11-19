package sk.longi.proxy.proxyparser.control;

import sk.longi.proxy.logging.Log;
import sk.longi.proxy.proxyparser.boundary.JsonStrings;
import sk.longi.proxy.proxyparser.entity.IpHost;
import sk.longi.proxy.proxyparser.entity.ListIpHost;
import sk.longi.proxy.verify.entity.IpVerify;
import sk.longi.proxy.websocket.entity.Message;
import sk.longi.proxy.websocket.entity.MessageQ;


import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.sound.midi.Soundbank;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class JsonParser {
    LocalDateTime dateTime = LocalDateTime.now();
    int num =0;

    @Inject
    Event <Message> messageEvent;


    @Inject
    IpVerify ipVerify;

    @Inject
    @ListIpHost
    private List<IpHost> proxyList;

    @Inject
    @MessageQ
    Message message;




    @Asynchronous
    @RequestScoped
    public void parse(String json) {


        System.out.println("-----------------------------start parse"+num);
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = reader.readObject();

        IpHost ipHost = new IpHost();

        ipHost.setIp(jsonObject.getString("PROXY_IP"));
        ipHost.setPort(getDecFromHex(jsonObject.getString("PROXY_PORT")));
        ipHost.setCountry(jsonObject.getString("PROXY_COUNTRY"));
        ipHost.setAnonymityLevel(jsonObject.getString("PROXY_TYPE"));
        ipHost.setUptimeL(parsreUptimeL(jsonObject.getString("PROXY_UPTIMELD")));
        ipHost.setUptimeD(parsreUptimeD(jsonObject.getString("PROXY_UPTIMELD")));
        ipHost.setLastUpdate(parsreTimeLastUpdate(jsonObject.getString("PROXY_LAST_UPDATE")));

        ipHost.setVerify(ipVerify.verifyVisibility(ipHost.getIp(), ipHost.getPort()));
        System.out.printf(ipHost.toString());
        if (ipHost.getVerify()){

            proxyList.add(ipHost);
            message.setMessage();
            messageEvent.fire(message);

        }
        System.out.println("***************************Proxy finished :"+"  -ProxyList size: "+(proxyList.size()));
    }

    public String getDecFromHex(java.lang.String hex) {

        int decimal = Integer.parseInt(hex, 16);
        return String.valueOf(decimal);
    }

    private Integer parsreUptimeL(java.lang.String s) {
        int i = s.indexOf('/');
        return Integer.valueOf(s.substring(0, i));


    }

    private Integer parsreUptimeD(java.lang.String s) {
        int i = s.indexOf('/');
        return Integer.valueOf(s.substring(i + 1));

    }

    private LocalDateTime parsreTimeLastUpdate(java.lang.String t) {

        int i = t.indexOf(' ');
        LocalDateTime time = dateTime;

        long minute = Long.valueOf(t.substring(0, i));
        long second = Long.valueOf(t.substring(i + 1));
        time.plusMinutes(minute);
        time.plusSeconds(second);
        return time;
    }


}
