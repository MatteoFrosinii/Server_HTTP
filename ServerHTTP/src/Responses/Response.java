package Responses;

import Client.ClientThread;

import java.time.LocalDateTime;

public class Response {
    private String replyLine;
    private String date;
    private String server = "Server: server275";
    private String contenType;
    private String contentLength;
    private String end = "";
    private String addInfo;
    public Response(int statusCode, ClientThread clientThread) {
        setStatusCode(statusCode);
    }
    public void setStatusCode(int statusCode, String phrase){
        String reason;
        switch (statusCode) {
            case 200:
                reason = "OK";
                if(!phrase.isEmpty())
                    reason = phrase;
                setReplyLine("HTTP/1.1 200 " + reason);
                setDate("Date : " + LocalDateTime.now().toString());
                break;
            case 404:
                reason = "Not found";
                if(!phrase.isEmpty())
                    reason = phrase;
                setReplyLine("HTTP/1.1 404 " + reason);
                setDate("Date : " + LocalDateTime.now().toString());
                break;
            default:
        }
    }
    public void setStatusCode(int statusCode){
        this.setStatusCode(statusCode,"");
    }

    public String getReplyLine() {
        return replyLine;
    }
    public void setReplyLine(String requestLine) {
        this.replyLine = requestLine;
    }
    public String getAndReturnReplyLine(String nomeClient) {
        System.out.println("Invio Reply line a " + nomeClient + " : " + replyLine);
        return replyLine;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getAndReturnDate(String nomeClient) {
        System.out.println("Invio Date a " + nomeClient + " : " + date);
        return date;
    }

    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }
    public String getAndReturnServer(String nomeClient) {
        System.out.println("Invio Server a " + nomeClient + " : " + server);
        return server;
    }

    public String getContenType() {
        return contenType;
    }
    public void setContenType(String contenType) {
        this.contenType = contenType;
    }
    public String getAndReturnContentType(String nomeClient) {
        System.out.println("Invio Content Type a " + nomeClient + " : " + contenType);
        return contenType;
    }

    public String getContentLength() {
        return contentLength;
    }
    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }
    public String getAndReturnContentLength(String nomeClient) {
        System.out.println("Invio Content Length a " + nomeClient + " : " + contentLength);
        return contentLength;
    }

    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }

    public String getAddInfo() {
        return addInfo;
    }
    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }
}
