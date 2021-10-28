package com.example.projectappchat.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Message_Send_Id", nullable = false)
    private User messageSendId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Message_Receiver_Id", nullable = false)
    private User messageReceiverId;

    private String messageBody;
    private Date messageDateCreation;

    public Message() {
    }


    public Message(Long messageId, User messageSendId, User messageReceiverId,
                   String messageBody, Date messageDateCreation) {
        this.messageId = messageId;
        this.messageSendId = messageSendId;
        this.messageReceiverId = messageReceiverId;
        this.messageBody = messageBody;
        this.messageDateCreation = messageDateCreation;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public User getMessageSendId() {
        return messageSendId;
    }

    public void setMessageSendId(User messageSendId) {
        this.messageSendId = messageSendId;
    }

    public User getMessageReceiverId() {
        return messageReceiverId;
    }

    public void setMessageReceiverId(User messageReceiverId) {
        this.messageReceiverId = messageReceiverId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getMessageDateCreation() {
        return messageDateCreation;
    }

    public void setMessageDateCreation(Date messageDateCreation) {
        this.messageDateCreation = messageDateCreation;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageSendId=" + messageSendId +
                ", messageReceiverId=" + messageReceiverId +
                ", messageBody='" + messageBody + '\'' +
                ", messageDateCreation=" + messageDateCreation +
                '}';
    }
}
