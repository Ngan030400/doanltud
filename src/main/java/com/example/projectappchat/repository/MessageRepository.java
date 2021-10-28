package com.example.projectappchat.repository;

import com.example.projectappchat.entity.Friend;
import com.example.projectappchat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "select ms from Message ms " +
            "where ms.messageSendId.userId = :messageSendId and ms.messageReceiverId.userId = :messageReceiverId" )
    List<Message> findMessageByMessageSendIdAndMessageReceiverId(@Param("messageSendId") Long messageSendId,
                                                         @Param("messageReceiverId") Long messageReceiverId);




    @Query(value = "select ms from Message ms " +
            "where ms.messageSendId.userId = :messageSendId1 and ms.messageReceiverId.userId = :messageSendId2 " +
            "or ms.messageSendId.userId = :messageSendId2 and ms.messageReceiverId.userId = :messageSendId1 " +
            "order by ms.messageId asc " )
    List<Message> findMessageByMessageSendId1AndMessageReceiverId1OrByMessageSendId2AndMessageReceiverId2
            (@Param("messageSendId1") Long messageSendId1,
             @Param("messageSendId2") Long messageSendId2);

}
