package sk.longi.proxy.proxyparser.control;

import sk.longi.proxy.logging.Log;
import sk.longi.proxy.proxyparser.boundary.JsonStrings;
import sk.longi.proxy.proxyparser.entity.IpHost;
import sk.longi.proxy.proxyparser.entity.ListIpHost;
import sk.longi.proxy.verify.entity.IpVerify;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class JsonParser {
    LocalDateTime dateTime = LocalDateTime.now();

    @Inject
    IpVerify ipVerify;

    @Inject
    @ListIpHost
    private List<IpHost> ProxyList;

    @Inject
    JsonStrings jsonStrings;


    @Log
    public void parsreToProxy() {


        List<String> jsons = jsonStrings.getJsonsFromWeb();
        for (String json : jsons) {
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
            if (ipHost.getVerify()) ProxyList.add(ipHost);
        }

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
