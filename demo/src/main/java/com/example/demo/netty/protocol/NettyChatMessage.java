package com.example.demo.netty.protocol;

public class NettyChatMessage {
    private String type;
    private Long sessionId;
    private Long senderId;
    private String senderType;
    private String content;
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Long getSessionId() { return sessionId; }
    public void setSessionId(Long sessionId) { this.sessionId = sessionId; }
    public Long getSenderId() { return senderId; }
    public void setSenderId(Long senderId) { this.senderId = senderId; }
    public String getSenderType() { return senderType; }
    public void setSenderType(String senderType) { this.senderType = senderType; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
