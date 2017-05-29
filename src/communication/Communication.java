/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Nikola
 */
public class Communication {

    public void startServer() throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(9000);
        System.out.println("Server started.");
        while (true) {
            Socket socket = ss.accept();
            System.out.println("Client Connected.");
            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();
        }
    }
}
