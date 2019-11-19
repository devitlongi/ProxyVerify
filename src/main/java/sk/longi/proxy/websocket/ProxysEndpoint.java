package sk.longi.proxy.websocket;

import sk.longi.proxy.proxyparser.entity.IpHost;
import sk.longi.proxy.proxyparser.entity.ListIpHost;
import sk.longi.proxy.websocket.entity.Message;
import sk.longi.proxy.websocket.entity.MessageQ;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;

//@ApplicationScoped
@ServerEndpoint(
        value="/socket",
//        decoders = MessageDecoder.class,
        encoders = {MessageEncoder.class}
        )
public class ProxysEndpoint {


    private String message;
    //    private static List<Session> mySession = new ArrayList<>();
    static  Session mySession = null;


    @Inject
    @MessageQ
    Message myMessage;

    @Inject
    @ListIpHost
    private List<IpHost> proxyList;


    public ProxysEndpoint() {
        System.out.println("class loaded " + this.getClass());
    }

    @OnOpen

    public void onOpen(Session session) {
        mySession = session;
        System.out.printf("Session opened, id: %s%n", session.getId());
        try {
            mySession.getBasicRemote()
                    .sendObject(myMessage);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
        }


    @OnMessage
    public void onMessage(@NotNull @Min(1)  String message, Session session) {

        mySession = session;
        int num =Integer.valueOf(message);
        System.out.printf("***************************************Message received. Session id: %s Message: %s%n ",
                session.getId(), message);

        try {
            if(num<proxyList.size()){
                session.getBasicRemote().sendText(String.format("{\"proxyToGet\" : \"%s\" }",proxyList.get(num).toString() ));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.printf("Session closed with id: %s%n", session.getId());
    }


    public static void broadcast(@Observes   Message message ) {
                    if(mySession!=null) {
                        try {
                            mySession.getBasicRemote()
                                    .sendObject(message);
                        } catch (IOException | EncodeException e) {
                            e.printStackTrace();
                        }
                    }
        }
}


