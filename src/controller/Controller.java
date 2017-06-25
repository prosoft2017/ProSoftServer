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
import domain.issue.Issue;
import domain.user.AppUser;
import domain.user.Country;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.user.SOAllCountries;
import so.user.SOAllIssues;
import so.user.SOIssueSave;
import so.user.SOUpdateIssues;
import so.user.SOValidateUser;
import so.user.chat.SOAllMessages;
import so.user.chat.SOSaveMessage;

/**
 *
 * @author Nikola
 */
public class Controller {

    public final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
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
            message.setMessageType(MessageType.GLOBAL);
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
                    message.setMessageType(MessageType.PRIVATE);
                    activeUserThread.sendMessageToThisUser(message);
                    break;
                }
            }
        }
    }

    public List<Issue> getAllIssues() throws Exception {
        SOAllIssues allCountries = new SOAllIssues();
        allCountries.executeSO(null);
        return allCountries.getIssues();
    }

    public void changeIssueStatus(Issue issue) throws Exception {
        SOUpdateIssues allCountries = new SOUpdateIssues();
        allCountries.executeSO(issue);
    }

    public void reportIssue(Issue issue) throws Exception {
        SOIssueSave issueSave = new SOIssueSave();
        issueSave.executeSO(issue);
    }

    public String getAllMessages(AppUser logedUser, AppUser appUser) throws Exception {
        SOAllMessages allMessages = new SOAllMessages();
        allMessages.executeSO(Arrays.asList(logedUser, appUser));
        return allMessages.getMessage();
    }

    public void saveMessage(Message message) {
        try {
            SOSaveMessage allMessages = new SOSaveMessage();
            allMessages.executeSO(message);
        } catch (Exception ex) {
            System.out.println("greska" + ex.getMessage());
        }
    }
}
