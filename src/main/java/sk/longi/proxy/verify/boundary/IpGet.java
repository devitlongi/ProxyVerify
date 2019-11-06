package sk.longi.proxy.verify.boundary;

import java.io.IOException;
import java.net.SocketException;

public interface IpGet {

    public String getMyIPFromWeb(String ip, String port) throws SocketException, IOException, Exception;


}
