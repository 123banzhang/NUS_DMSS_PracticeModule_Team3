package com.client.entitiy;

public class ChatRequest {
    private String sessionId;
    private String userMessage;

    public ChatRequest() {}

    public ChatRequest(String sessionId, String userMessage) {
        this.sessionId = sessionId;
        this.userMessage = userMessage;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}

