package sk.longi.proxy.verify.entity;

import sk.longi.proxy.verify.boundary.IpGet;
import sk.longi.proxy.verify.boundary.Ipifi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;


public class IpVerify {

    @Inject
    @Ipifi
    IpGet ipGet;


    public boolean verifyVisibility(String ip, String port) {


        boolean check = false;
        try {
            String json = ipGet.getMyIPFromWeb(ip, port);
            check = ip.equals(parsreIP(json));
        } catch (IOException e) {
            System.err.println("Error: IOException");
        } catch (Exception e) {
            System.err.println("Error: Exception");
        }
        finally {
            return check;
        }


    }

    private String parsreIP(String json) {
        String match = "empty";

        JsonObject jsonObject;
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
            jsonObject = reader.readObject();
            match = jsonObject.getString("ip");
        } catch (NullPointerException e) {
            System.err.println("Error: Json empty");
        }
        return match;

    }


}
