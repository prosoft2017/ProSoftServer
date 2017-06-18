/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import constant.ConstantOperations;
import domain.chat.Message;
import domain.chat.MessageType;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import transfer.TransferObjectResponse;

/**
 *
 * @author Nikola
 */
public class Communication {

    public final static List<ReciveMessageThread> activeUsers = new ArrayList<>();

    public void startServer() throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(9000);
        ServerSocket chatServer = new ServerSocket(9001);
        System.out.println("Server started.");
        while (true) {
            Socket socket = ss.accept();
            System.out.println("Client Connected.");

            Socket chatSocket = chatServer.accept();
            ReciveMessageThread reciveMessageThread = new ReciveMessageThread(chatSocket);
            reciveMessageThread.start();
            activeUsers.add(reciveMessageThread);

            new ClientThread(socket, reciveMessageThread).start();
        }
    }
}
