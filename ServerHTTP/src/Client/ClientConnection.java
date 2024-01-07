package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientConnection {
    private Socket s;
    private BufferedReader in;
    private DataOutputStream out;

    public ClientConnection(Socket s) {
        this.s = s;
        try {
            this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.out = new DataOutputStream(s.getOutputStream());
        } catch (Exception e) {
            System.out.println("Errore istanza Connessione: " + e.getMessage());
        }
    }
    public String getLine(){
        try {
            return this.in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void send(String data){
        try {
            this.out.writeBytes(data + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public DataOutputStream getOut() {
        return out;
    }
    public void close() {
        try {
            s.close();
        } catch (Exception e) {}
    }
}
