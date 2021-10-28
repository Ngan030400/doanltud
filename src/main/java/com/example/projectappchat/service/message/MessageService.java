package com.example.projectappchat.service.message;

import com.example.projectappchat.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<Message> findMessageByMessageSendIdAndMessageReceiverId(Long messageSendId, Long messageReceiverId);
    List<Message> findMessageByMessageSendId1AndMessageReceiverId1OrByMessageSendId2AndMessageReceiverId2(Long messageSendId1, Long messageSendId2);

}
