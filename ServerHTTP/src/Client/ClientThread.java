package Client;

import RequestHandlers.RequestManager;

import java.io.File;
import java.net.Socket;

public class ClientThread extends Thread{
    private final String defaultSearchDirectory = "/Sito";
    private Socket s;
    private Boolean running = true;
    private ClientConnection clientConnection;
    private boolean connectionState;
    private String requestedPath;
    private RequestManager requestManager = RequestManager.getInstance();
    public String getRequestedPath() {
        return requestedPath;
    }
    public void setRequestedPath(String requestedPath) {
        this.requestedPath = String.valueOf(new File("htdocs" +
                (((new File("htdocs" + requestedPath)).isDirectory()) ?
                        requestedPath + "/index.html" :
                        (new File("htdocs" + "/Sito" + requestedPath).exists() ?
                        "/Sito" + requestedPath :
                        requestedPath))));
    }
    public ClientThread (Socket s){
        this.s = s;
    }
    public Socket getSocket() {
        return s;
    }
    public void setSocket(Socket s) {
        this.s = s;
    }
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    public boolean isRequestLine() {
        return connectionState;
    }
    public void setConnectionState(boolean connectionState) {
        this.connectionState = connectionState;
    }
    public ClientConnection getClientConnection() {
        return clientConnection;
    }
    public void setClientConnection(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    @Override
    public void run(){
        ClientConnection connessione = new ClientConnection(s);
        setClientConnection(connessione);
        setConnectionState(true);
        do {
            requestManager.requestRead(connessione.getLine(), this);
        } while (isRunning());
    }

    public String getFileContentType(){
        try {
            return switch (this.getRequestedPath().split("\\.")[1]) {
                case "html" -> "text/html";
                case "png" -> "image/png";
                case "css" -> "text/css";
                case "txt" -> "text/plain";
                case "gif" -> "image/gif";
                default -> "";
            };
        } catch (ArrayIndexOutOfBoundsException e) {
            return "text/html";
        }
    }
}
