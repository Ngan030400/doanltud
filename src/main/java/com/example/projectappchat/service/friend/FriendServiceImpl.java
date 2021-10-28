package com.example.projectappchat.service.friend;


import com.example.projectappchat.entity.Friend;
import com.example.projectappchat.entity.User;
import com.example.projectappchat.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendRepository friendRepository;

    @Override
    public List<Friend> findFriendByUserIdAndFriendStatus(Long friendSendId, byte friendStatus) {
        return friendRepository.findFriendByUserIdAndFriendStatus(friendSendId, friendStatus);
    }
}
