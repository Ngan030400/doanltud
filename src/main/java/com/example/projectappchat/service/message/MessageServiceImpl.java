package com.example.projectappchat.service.message;


import com.example.projectappchat.entity.Message;
import com.example.projectappchat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public List<Message> findMessageByMessageSendIdAndMessageReceiverId(Long messageSendId, Long messageReceiverId) {
        return messageRepository.findMessageByMessageSendIdAndMessageReceiverId(messageSendId, messageReceiverId);
    }

    @Override
    public List<Message> findMessageByMessageSendId1AndMessageReceiverId1OrByMessageSendId2AndMessageReceiverId2
            (Long messageSendId1, Long messageSendId2) {
        return messageRepository
                .findMessageByMessageSendId1AndMessageReceiverId1OrByMessageSendId2AndMessageReceiverId2
                        (messageSendId1,messageSendId2);
    }


}
