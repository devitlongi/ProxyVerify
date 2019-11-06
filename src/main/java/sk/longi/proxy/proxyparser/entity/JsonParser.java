package sk.longi.proxy.proxyparser.entity;

import sk.longi.proxy.proxyparser.boundary.JsonStrings;
import sk.longi.proxy.verify.entity.IpVerify;

import javax.faces.bean.RequestScoped;
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

    List<IpHost> ipHosts = new ArrayList<>();

    @Inject
    IpVerify ipVerify;

    @Inject
    JsonStrings jsonStrings;

    public List<IpHost> getIpHosts() {
        return ipHosts;
    }

    public List<IpHost> parsreToProxy() {


        List<java.lang.String> jsons = jsonStrings.getJsonsFromWeb();
        for (java.lang.String json : jsons) {
            JsonReader reader = Json.createReader(new StringReader(json));
            JsonObject jsonObject = reader.readObject();
            IpHost ipHost = new IpHost();
            ipHost.setIp(jsonObject.getString("PROXY_IP"));
            ipHost.setPort(getDecFromHex(jsonObject.getString("PROXY_PORT")));
            ipHost.setCountry(jsonObject.getString("PROXY_COUNTRY"));
            ipHost.setAnonimityLevel(jsonObject.getString("PROXY_TYPE"));
            ipHost.setUptimeL(parsreUptimeL(jsonObject.getString("PROXY_UPTIMELD")));
            ipHost.setUptimeD(parsreUptimeD(jsonObject.getString("PROXY_UPTIMELD")));
            ipHost.setLastUpdate(parsreTimeLastUpdate(jsonObject.getString("PROXY_LAST_UPDATE")));

            ipHost.setVeryfy(ipVerify.verifyVisibility(ipHost.getIp(), ipHost.getPort()));
            System.out.printf(ipHost.toString());
            if (ipHost.getVeryfy()) ipHosts.add(ipHost);
        }

        return ipHosts;
    }

    public java.lang.String getDecFromHex(java.lang.String hex) {

        int decimal = Integer.parseInt(hex, 16);
        return java.lang.String.valueOf(decimal);
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
