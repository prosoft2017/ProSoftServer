/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import constant.ConstantOperations;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import transfer.TransferObjectResponse;
import transfer.TransferObjectRequest;
import constant.ConstantMessages;
import controller.Controller;
import controller.UserBackendController;
import domain.issue.Issue;
import domain.user.AppUser;
import domain.user.Country;
import java.util.ArrayList;

/**
 *
 * @author student1
 */
public class ClientThread extends Thread {

    private final Socket socket;
    private boolean clientConnected;
    private final ReciveMessageThread messageThread;

    public ClientThread(Socket socket, ReciveMessageThread messageThread) {
        this.socket = socket;
        this.clientConnected = true;
        this.messageThread = messageThread;
    }

    @Override
    public void run() {
        try {
            runThread();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            clientConnected = false;
        }
        System.out.println("Client disconnected.");
    }

    private void runThread() throws IOException, ClassNotFoundException {
        while (clientConnected) {
            ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
            TransferObjectRequest request = (TransferObjectRequest) inSocket.readObject();
            TransferObjectResponse response = new TransferObjectResponse();
            try {
                switch (request.getOperation()) {
                    case ConstantOperations.VALIDATED_USER:
                        AppUser appUser = Controller.getController().validateUser(request.getParameter());
                        response.setResult(appUser);
                        messageThread.setUser(appUser);
                        break;
                    case ConstantOperations.GET_ALL_ACTIVE_USERES:
                        List<AppUser> usersAll = new ArrayList<>();
                        Communication.activeUsers.forEach((activeUserThread) -> {
                            usersAll.add(activeUserThread.getUser());
                        });
                        response.setResult(usersAll);
                        break;
                    case ConstantOperations.GET_ALL_USERS_FRONTEND:
                        List<AppUser> usersAll2 = UserBackendController.getController().getAllUsersFromDB();
                        response.setResult(usersAll2);
                        break;
                    case ConstantOperations.GET_ALL_COUNTRIES_FRONTEND:
                        List<Country> countries = Controller.getController().getAllAvailableCountries();
                        response.setResult(countries);
                        break;
                    case ConstantOperations.UPDATE_LOGED_USER:
                        UserBackendController.getController().editAppUser((AppUser) request.getParameter());
                        break;
                    case ConstantOperations.REPORT_ISSUE:
                        Controller.getController().reportIssue((Issue) request.getParameter());
                        break;
                    case ConstantOperations.GET_ALL_MESSAGES:
                        String content = controller.Controller.getController().getAllMessages(messageThread.getUser(), (AppUser) request.getParameter());
                        response.setResult(content);
                        break;
                    case ConstantOperations.CLIENT_DISCONECTED:
                        clientConnected = false;
                        break;
                }

                response.setMesssage(ConstantOperations.SUCCESS_MSG);
            } catch (Exception ex) {
                response.setMesssage(ConstantOperations.ERROR_MSG);
                response.setResponseException(ex);
            }
            sendResponse(response);
        }
    }

    private void sendResponse(TransferObjectResponse response) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(response);
    }

}
