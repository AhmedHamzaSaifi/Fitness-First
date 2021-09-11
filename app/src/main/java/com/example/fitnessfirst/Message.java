package com.example.fitnessfirst;

public class Message {
    private String messageId,message,senderId;


    public Message(String message, String byte_four) {
        this.message = message;
        this.byte_four = byte_four;
    }

    public Message(String message, String senderId, String byte_four, long timestamp) {
        this.message = message;
        this.senderId = senderId;

        this.byte_four = byte_four;
        this.timestamp = timestamp;
    }



    public String getByte_four() {
        return byte_four;
    }

    public void setByte_four(String byte_four) {
        this.byte_four = byte_four;
    }

    private String byte_four;
    private long timestamp;

    public Message() {
    }

    public Message( String message, String senderId, long timestamp) {

        this.message = message;
        this.senderId = senderId;
        this.timestamp = timestamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
