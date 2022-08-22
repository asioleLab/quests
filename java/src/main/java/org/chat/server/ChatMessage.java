package org.chat.server;

import java.io.Serializable;
import java.util.Calendar;

public class ChatMessage implements Serializable {
    String sender;
    String message;
    Calendar dateTime;

    public ChatMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
        this.dateTime=Calendar.getInstance();
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

}
