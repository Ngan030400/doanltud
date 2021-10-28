package com.example.projectappchat.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long friendId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Friend_Send_Id", nullable = false)
    private User friendSendId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Friend_Receiver_Id", nullable = false)
    private User friendReceiverId;
    private byte friendStatus;
    private Date friendDateCreation;
    private String lastMessage;

    public Friend() {
    }

    public Friend(Long friendId, User friendSendId, User friendReceiverId,
                  byte friendStatus, Date friendDateCreation, String lastMessage) {
        this.friendId = friendId;
        this.friendSendId = friendSendId;
        this.friendReceiverId = friendReceiverId;
        this.friendStatus = friendStatus;
        this.friendDateCreation = friendDateCreation;
        this.lastMessage = lastMessage;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public User getFriendSendId() {
        return friendSendId;
    }

    public void setFriendSendId(User friendSendId) {
        this.friendSendId = friendSendId;
    }

    public User getFriendReceiverId() {
        return friendReceiverId;
    }

    public void setFriendReceiverId(User friendReceiverId) {
        this.friendReceiverId = friendReceiverId;
    }

    public byte getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(byte friendStatus) {
        this.friendStatus = friendStatus;
    }

    public Date getFriendDateCreation() {
        return friendDateCreation;
    }

    public void setFriendDateCreation(Date friendDateCreation) {
        this.friendDateCreation = friendDateCreation;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "friendId=" + friendId +
                ", friendSendId=" + friendSendId +
                ", friendReceiverId=" + friendReceiverId +
                ", friendStatus=" + friendStatus +
                ", friendDateCreation=" + friendDateCreation +
                ", lastMessage='" + lastMessage + '\'' +
                '}';
    }
}
