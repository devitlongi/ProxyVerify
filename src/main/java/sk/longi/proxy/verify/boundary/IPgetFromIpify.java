package sk.longi.proxy.verify.boundary;

import javax.enterprise.context.RequestScoped;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;


@Ipifi
@RequestScoped
public class IPgetFromIpify implements IpGet {



    @Override

    public String getMyIPFromWeb(String ip, String port) throws SocketException, IOException,Exception {
        String json = null;
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, Integer.valueOf(port)));
        Scanner scanner = null;
        try {
            scanner = new Scanner(new URL("https://api.ipify.org?format=json").openConnection(proxy).getInputStream(), "UTF-8").useDelimiter("\\A");
            json = scanner.next();

        } finally {

            if (scanner != null) {
                scanner.close();
            }
        }
        return json;
    }

//    public String getIP () {
//        URL url = null;
//        HttpURLConnection con = null;
//        String result= null;
//
//
//
//
//        try {
//            url = new URL("https://api.ipify.org?format=json");
//            con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            result = con.getResponseMessage();
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return result;
//    }

}
