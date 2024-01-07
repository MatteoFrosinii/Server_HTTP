package RequestHandlers;

import Client.ClientThread;

public class RequestManager {
    private static RequestManager pm;
    private RequestDecoder requestDecoder = RequestDecoder.getInstance();
    private RequestManager() {}
    public static RequestManager getInstance() {
        if (pm == null)
            pm = new RequestManager();
        return pm;
    }
    public void requestRead(String packet, ClientThread clientThread){
        requestDecoder.definePacket(packet, clientThread);
    }
}