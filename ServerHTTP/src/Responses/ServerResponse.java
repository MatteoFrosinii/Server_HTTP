package Responses;

import Client.ClientThread;

import java.io.File;

public class ServerResponse {
    private static ServerResponse r;
    private ServerResponse(){}
    public static synchronized ServerResponse getInstance(){
        if (r == null) {
            r = new ServerResponse();
        }
        return r;
    }
    public static void SendGoodResponse(ClientThread clientThread){
        Response goodResponse = new Response(200, clientThread);
        goodResponse.setContenType("Content-Type: " + clientThread.getFileContentType());
        goodResponse.setContentLength("Content-Length: " + new File(clientThread.getRequestedPath()).length());

        clientThread.getClientConnection().send(goodResponse.getAndReturnReplyLine(clientThread.getName()));
        clientThread.getClientConnection().send(goodResponse.getAndReturnDate(clientThread.getName()));
        clientThread.getClientConnection().send(goodResponse.getAndReturnServer(clientThread.getName()));
        clientThread.getClientConnection().send(goodResponse.getAndReturnContentType(clientThread.getName()));       
        clientThread.getClientConnection().send(goodResponse.getAndReturnContentLength(clientThread.getName()));
        clientThread.getClientConnection().send("");

        System.out.println("Connesione con " + clientThread.getName() + " finita");
    }

    public static void SendBadResponse(ClientThread clientThread){
        Response badResponse = new Response(404, clientThread);
        badResponse.setContenType("Content-Type: text/plain; charset=UTF-8");
        badResponse.setContentLength("Content-Length: 26");
        badResponse.setAddInfo("The resource was not found");

        clientThread.getClientConnection().send(badResponse.getAndReturnReplyLine(clientThread.getName()));
        clientThread.getClientConnection().send(badResponse.getAndReturnDate(clientThread.getName()));
        clientThread.getClientConnection().send(badResponse.getAndReturnServer(clientThread.getName()));
        clientThread.getClientConnection().send(badResponse.getAndReturnContentType(clientThread.getName()));       
        clientThread.getClientConnection().send(badResponse.getAndReturnContentLength(clientThread.getName()));
        clientThread.getClientConnection().send("");
        clientThread.getClientConnection().send(badResponse.getAddInfo());
        System.out.println("Connesione con " + clientThread.getName() + " finita");
        
    }
}