package RequestHandlers;

import Client.ClientThread;
import DataManager.DataManager;

public class RequestDecoder {
    private static RequestDecoder pd;
    private DataManager dataManager = DataManager.getInstance();
    private RequestDecoder() {}
    public static RequestDecoder getInstance() {
        if (pd == null) {
            pd = new RequestDecoder();
        }
        return pd;
    }
    public void definePacket(String request, ClientThread clientThread) {
        if (clientThread.isRequestLine()) {
            String[] dataBySpace = request.split(" ");
            if (dataBySpace.length == 3) {
                if (dataBySpace[2].contains("HTTP")) {
                    clientThread.setConnectionState(false);
                    System.out.println("Requested Path by " + clientThread.getName() + " : " + dataBySpace[1]);
                    clientThread.setRequestedPath(dataBySpace[1]);
                    System.out.println("Iniziato il data stream da parte di " + clientThread.getName() + " :");
                    System.out.println("Data from " + clientThread.getName() + " : " + request);
                    return;
                }
            }
            System.out.println("Errore : il client " + clientThread.getName() + " non ha inviato il primo pacchetto in modo corretto");
        } else {
            if (request.trim().isEmpty()) {
                System.out.println("Data stream finito da " + clientThread.getName());
                clientThread.setConnectionState(true);
                System.out.println("Processo la richiesta ricevuta da client " + clientThread.getName());
                dataManager.returnFile(clientThread);
                return;
            }
            System.out.println("Data from " + clientThread.getName() + " : " + request);
        }
    }
}