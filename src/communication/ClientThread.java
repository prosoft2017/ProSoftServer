/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author student1
 */
public class ClientThread extends Thread {

    private Socket socket;
    private boolean aktivna;

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.aktivna = true;
    }

    @Override
    public void run() {
        try {
            runThread();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            aktivna = false;
        }
        System.out.println("Client disconnected.");
    }

    private void runThread() throws IOException, ClassNotFoundException {
           
    }


}
