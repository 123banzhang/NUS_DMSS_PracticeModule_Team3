package com.client.entitiy;

public class ChatRequest {
    private String sessionId;
    private String userMessage;
    private long metahumanId;
    private String chatModel;

    public ChatRequest() {}

    public ChatRequest(String sessionId, String userMessage, long metahumanId) {
        this.sessionId = sessionId;
        this.userMessage = userMessage;
        this.metahumanId = metahumanId;
    }

//    public ChatRequest(String sessionId, String userMessage) {
//        this.sessionId = sessionId;
//        this.userMessage = userMessage;
//    }

    public Long getMetahumanId() {
        return metahumanId;
    }

    public void setMetahumanId(Long metahumanId) {
        this.metahumanId = metahumanId;
    }

    public String getChatModel(){return chatModel;}

    public void setChatModel(String chatModel){this.chatModel = chatModel;}

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

