package sk.longi.proxy.proxyparser.boundary;

import sk.longi.proxy.logging.Log;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class JsonStrings {

    List<String> listJson = new ArrayList<>();


    public List<String> getJsonsFromWeb() {

        URL url;
        InputStream is = null;
        BufferedReader br;
        String line = null;


        try {
            url = new URL("http://www.gatherproxy.com/");
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));


            while ((line = br.readLine()) != null) {
                if (line.contains("gp.insertPrx")) {
                    String json = line.substring(line.indexOf("gp.insertPrx") + 13, line.length() - 2);
                    listJson.add(json);
                }
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {
                System.out.println("Page http://www.gatherproxy.com/  is Empty");
            }
        }
        System.out.println(" Complete");
        return listJson;
    }
}
