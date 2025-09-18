package com.example.duanlonmain.chat_bot;

public class Message {
    private String text;
    private boolean isUser; // true = user, false = AI

    public Message(String text, boolean isUser) {
        this.text = text;
        this.isUser = isUser;
    }

    public String getText() { return text; }
    public boolean isUser() { return isUser; }
}
