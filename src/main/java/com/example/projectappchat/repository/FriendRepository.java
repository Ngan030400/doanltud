package com.example.projectappchat.repository;


import com.example.projectappchat.entity.Friend;
import com.example.projectappchat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    @Query(value = "select fr from Friend fr " +
            "where fr.friendSendId.userId = :userId and fr.friendStatus = :friendStatus" )
    List<Friend> findFriendByUserIdAndFriendStatus(@Param("userId") Long userId,
                                                       @Param("friendStatus") byte friendStatus);
}
