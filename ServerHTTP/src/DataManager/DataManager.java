package DataManager;

import Client.ClientThread;
import Responses.ServerResponse;

import java.io.*;
import java.util.Scanner;

public class DataManager {
    private static DataManager pm;
    private DataManager() {}
    public static DataManager getInstance() {
        if (pm == null)
            pm = new DataManager();
        return pm;
    }
    public static void returnFile(ClientThread clientThread) {
        File fileRequested = new File(clientThread.getRequestedPath());
        System.out.println("Cerco file richiesto da " + clientThread.getName() + " alla path : " + fileRequested.getAbsolutePath());
        if(fileRequested.exists()){
            System.out.println("il file da " + clientThread.getName() + " richiesto esiste");
            try {
                int index = 0;
                Scanner scanner = new Scanner(fileRequested);
                ServerResponse.SendGoodResponse(clientThread);
                System.out.println("Inizio a leggere dal file richiesto da " + clientThread.getName());

                InputStream input = new FileInputStream(fileRequested);
                byte[] buf = new byte[8192];
                int n;
                while((n = input.read(buf)) != -1){
                    clientThread.getClientConnection().getOut().write(buf, 0, n);
                }

                scanner.close();
                System.out.println("Chiudo connessione con client " + clientThread.getName());
                clientThread.getSocket().close();
                clientThread.setRunning(false);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Il file non esiste");
            ServerResponse.SendBadResponse(clientThread);
            System.out.println("Chiudo connessione con client " + clientThread.getName());
            try {
                clientThread.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clientThread.setRunning(false);
        }
    }
}
