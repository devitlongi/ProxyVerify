package sk.longi.proxy.verify.boundary;


import javax.inject.Singleton;
import java.net.InetSocketAddress;
import java.net.Proxy;


@Myip
@Singleton
public class IPgetFromMyip implements IpGet {
    @Override

    public String getMyIPFromWeb(String ip, String port) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, Integer.valueOf(port)));
        java.lang.String json = "";
        try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.myip.com").openConnection(proxy).getInputStream(), "UTF-8").useDelimiter("\\A")) {

            json = s.next();


//            System.out.println("My current IP address is " + s.next());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
