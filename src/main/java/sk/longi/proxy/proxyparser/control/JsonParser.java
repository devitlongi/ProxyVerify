package sk.longi.proxy.proxyparser.control;

import sk.longi.proxy.proxyparser.boundary.ProxyDao;
import sk.longi.proxy.proxyparser.entity.ListIpHost;
import sk.longi.proxy.proxyparser.entity.ProxyFull;
import sk.longi.proxy.verify.entity.IpVerify;
import sk.longi.proxy.websocket.entity.Message;
import sk.longi.proxy.websocket.entity.MessageQ;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class JsonParser {


    @EJB
    ProxyDao proxyDao;

    @Inject
    Event <Message> messageEvent;


    @Inject
    IpVerify ipVerify;

    @Inject
    @ListIpHost
    private List<ProxyFull> proxyList;

    @Inject
    @MessageQ
    Message message;

   @Asynchronous
    @RequestScoped
    public void parse(String json) {


        System.out.println("-----------------------------start parse"+json);
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = reader.readObject();
        ProxyFull ipHost = new ProxyFull();

        ipHost.setIp(jsonObject.getString("PROXY_IP"));
        ipHost.setPort(getDecFromHex(jsonObject.getString("PROXY_PORT")));
        ipHost.setCountry(jsonObject.getString("PROXY_COUNTRY"));
        ipHost.setAnonymity_Level(jsonObject.getString("PROXY_TYPE"));
        ipHost.setUptime_L(parsreUptimeL(jsonObject.getString("PROXY_UPTIMELD")));
        ipHost.setUptime_D(parsreUptimeD(jsonObject.getString("PROXY_UPTIMELD")));
        ipHost.setLast_Update(LocalDateTime.now());
        long startTime = System.currentTimeMillis();
        ipHost.setVerify(ipVerify.verifyVisibility(ipHost.getIp(), ipHost.getPort()));
        long endTime = System.currentTimeMillis();
        ipHost.setResponse_Time((int)(endTime-startTime));



        if (ipHost.getVerify()==true){
            System.out.printf(ipHost.toString());

            proxyList.add(ipHost);
            message.setProxysLenght(proxyList.size());
            message.setProxyNew(proxyList.get(proxyList.size()-1).toString());
            messageEvent.fire(message);

            try {
                proxyDao.addProxy(ipHost);
            } catch (Exception e) {
                e.printStackTrace();
            }

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

//    private LocalDateTime parsreTimeLastUpdate(java.lang.String t) {
//
//        int i = t.indexOf(' ');
//        LocalDateTime time = dateTime;
//
//        long minute = Long.valueOf(t.substring(0, i));
//        long second = Long.valueOf(t.substring(i + 1));
//        time.plusMinutes(minute);
//        time.plusSeconds(second);
//        return time;
//    }


}
