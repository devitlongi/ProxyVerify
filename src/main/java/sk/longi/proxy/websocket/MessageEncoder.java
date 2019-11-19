package sk.longi.proxy.websocket;


import com.google.gson.Gson;
import sk.longi.proxy.websocket.entity.Message;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {


    @Override
    public String encode(Message message) throws EncodeException {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("proxysLenght", message.getProxysLenght())
                .add("proxyToGet", message.getProxyToGet())
                .add("proxyNew", message.getProxyNew())
                .build();
        return jsonObject.toString();


    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }

}