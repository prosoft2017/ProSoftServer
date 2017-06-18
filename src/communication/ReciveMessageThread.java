/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import constant.ConstantOperations;
import controller.Controller;
import domain.chat.Message;
import domain.chat.MessageType;
import domain.user.AppUser;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.TransferObjectRequest;
import transfer.TransferObjectResponse;

/**
 *
 * @author Nikola
 */
public class ReciveMessageThread extends Thread {

    private final Socket socket;
    private AppUser user;

    public ReciveMessageThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            runThread();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        Communication.activeUsers.remove(this);
        System.out.println("Client disconnected.");
    }

    private void runThread() throws IOException, ClassNotFoundException {
        while (true) {
            ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
            TransferObjectRequest request = (TransferObjectRequest) inSocket.readObject();
            TransferObjectResponse response = new TransferObjectResponse();
            Message message = (Message) request.getParameter();

            response.setResult(message);
            response.setMesssage(ConstantOperations.SUCCESS_MSG);

            for (AppUser appUser : message.getAppUserReciver()) {
                for (ReciveMessageThread activeUserThread : Communication.activeUsers) {
                    if (appUser.getUsername().equals(activeUserThread.getUser().getUsername())) {
                        sendMessage(response, activeUserThread.getSocket());
                        break;
                    }
                }
            }
        }
    }

    private void sendMessage(TransferObjectResponse response, Socket socket) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(response);
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    private Socket getSocket() {
        return socket;
    }

    public void sendMessageToThisUser(Message message) throws IOException {
        TransferObjectResponse response = new TransferObjectResponse();
        response.setResult(message);
        response.setMesssage(ConstantOperations.SUCCESS_MSG);

        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(response);
    }
}
