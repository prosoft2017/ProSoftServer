/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.Communication;
import communication.ReciveMessageThread;
import domain.chat.Message;
import domain.chat.MessageType;
import domain.user.AppUser;
import domain.user.Country;
import java.io.IOException;
import java.util.List;
import so.user.SOAllCountries;
import so.user.SOValidateUser;

/**
 *
 * @author Nikola
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getController() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    public boolean checkUsername(String username) {
        return true;
    }

    public boolean checkEmail(String email) {
        return true;
    }

    public AppUser validateUser(Object parameter) throws Exception {
        SOValidateUser validateUser = new SOValidateUser();
        validateUser.executeSO(parameter);

        return validateUser.getAppUser();
    }

    public void sendGlobalMessage(String messageContent) throws IOException {
        for (ReciveMessageThread activeUserThread : Communication.activeUsers) {
            Message message = new Message();
            message.setMessageContent(messageContent);
            message.setMessageType(MessageType.Global);
            activeUserThread.sendMessageToThisUser(message);
        }
    }
    
    public List<Country> getAllAvailableCountries() throws Exception {
        SOAllCountries allCountries = new SOAllCountries();
        allCountries.executeSO(null);
        return allCountries.getAllCountries();
    }
        
    public void sendPrivateMessage(String messageContent, List<AppUser> userList) throws IOException {
        for (AppUser appUser : userList) {
            for (ReciveMessageThread activeUserThread : Communication.activeUsers) {
                if (appUser.getUsername().equals(activeUserThread.getUser().getUsername())) {
                    Message message = new Message();
                    message.setMessageContent(messageContent);
                    message.setMessageType(MessageType.Private);
                    activeUserThread.sendMessageToThisUser(message);
                    break;
                }
            }
        }
    }
}
