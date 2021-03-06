package com.example.projectappchat.controller;

import com.example.projectappchat.entity.Friend;

import com.example.projectappchat.entity.Message;
import com.example.projectappchat.entity.Role;
import com.example.projectappchat.entity.UserRole;
import com.example.projectappchat.service.friend.FriendService;
import com.example.projectappchat.service.message.MessageService;
import com.example.projectappchat.service.role.RoleService;
import com.example.projectappchat.service.user.UserService;
import com.example.projectappchat.service.userRole.UserRoleService;
import com.example.projectappchat.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private MessageService messageService;

    @GetMapping({"/login"})
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView("loginPage");
        return modelAndView;
    }

    @GetMapping("/logoutSuccessful")
    public ModelAndView logoutSuccessful() {
        ModelAndView modelAndView = new ModelAndView("loginPage");
        return modelAndView;
    }

    @PostMapping("/sign-up")
    public ModelAndView signUp(@RequestParam(value = "user-account", required = false) String userAccount,
                               @RequestParam(value = "password", required = false) String password,
                               RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:" + "/login");
        com.example.projectappchat.entity.User user = new com.example.projectappchat.entity.User();
        user.setUserAccount(userAccount);
        String encryptedPassword = passwordEncoder.encode(password);
        user.setUserPassword(encryptedPassword);
//        user.setEnabled(true);
        userService.save(user);
        Role role = roleService.findById(2L);
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleService.save(userRole);
        redirectAttributes.addFlashAttribute("message", "????ng k?? th??nh c??ng");
        return modelAndView;
    }

    @GetMapping("/admin")
    //Principal c?? th??? hi???u l?? m???t ng?????i, ho???c m???t thi???t b???,
    // ho???c m???t h??? th???ng n??o ???? c?? th??? th???c hi???n m???t h??nh ?????ng trong ???ng d???ng c???a b???n.
    public ModelAndView adminPage(Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(user);
        ModelAndView modelAndView = new ModelAndView("adminPage");
        modelAndView.addObject("userInfo", userInfo);
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView userInfo(Principal principal) {
        //Sau khi user login thanh cong se co principal
        if (principal != null) {
            String userName = principal.getName();
            System.out.println("User Name: " + userName);

            /*Principal spring tr??? cho server khi user ????ng nh???p th??nh c??ng*/
            User userPrincipal = (User) ((Authentication) principal).getPrincipal();

            /*T??m th??ng tin user t??? principal*/
            com.example.projectappchat.entity.User userInfo = userService.findUserByAccount(userPrincipal.getUsername());

            String userPrincipalToString = WebUtils.toString(userPrincipal);
            ModelAndView modelAndView = new ModelAndView("userInfoPage");
            modelAndView.addObject("userPrincipalToString", userPrincipalToString);
            modelAndView.addObject("userInfo", userInfo);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("loginPage");
            return modelAndView;
        }
    }

    @GetMapping({"/box-chat/{userFriendId}", "/box-chat"})
    public ModelAndView boxChatDetail(Principal principal,
                                      @PathVariable(value = "userFriendId", required = false) Long userFriendId) {
        ModelAndView modelAndView = new ModelAndView("boxChat");

        /*User spring tr??? cho server khi ????ng nh???p th??nh c??ng*/
        User userPrincipal = (User) ((Authentication) principal).getPrincipal();

        /*T??m th??ng tin user t??? principal*/
        com.example.projectappchat.entity.User userInfo = userService.findUserByAccount(userPrincipal.getUsername());

        /*T??m danh s??ch b???n b?? c???a user*/
        List<Friend> friendList = friendService
                .findFriendByUserIdAndFriendStatus(userInfo.getUserId(), (byte) 1);

        List<Message> messageListLast = new ArrayList<>();

        /*Duy???t danh s??ch b???n b?? c???a user ????? t??m message c???a t???t c??? c??c b???n b?? nh???n tin v???i user*/
        for (Friend friend : friendList) {
            System.out.println("friend: " + friend.toString());
            List<Message> messageListOfAllFriend = messageService.
                    findMessageByMessageSendId1AndMessageReceiverId1OrByMessageSendId2AndMessageReceiverId2
                            (userInfo.getUserId(), friend.getFriendReceiverId().getUserId());
            for (Message ms : messageListOfAllFriend) {
                System.out.println(ms.toString());
            }

            if (!messageListOfAllFriend.isEmpty()) {
                Message lastMessage = messageListOfAllFriend.get(messageListOfAllFriend.size() - 1);
                System.out.println("Last message: " + lastMessage);
                messageListLast.add(lastMessage);
                friend.setLastMessage(lastMessage.getMessageBody());
            }
        }

        /*T??m message gi???a user v???i 1 friend ???????c g???i*/
        List<Message> messageListOfOneFriend = messageService.
                findMessageByMessageSendId1AndMessageReceiverId1OrByMessageSendId2AndMessageReceiverId2(userInfo.getUserId(), userFriendId);

        for (Message ms : messageListOfOneFriend) {
            System.out.println("Message of one friend: " + ms.toString());
        }

        modelAndView.addObject("messageListOfOneFriend", messageListOfOneFriend);
        modelAndView.addObject("userInfo", userInfo);
        if (userFriendId != null) {
            modelAndView.addObject("friendInfo", userService.findUserById(userFriendId));
        }

        modelAndView.addObject("friendList", friendList);
        return modelAndView;
    }


    @GetMapping("/image/display/{userAccount}")
    @ResponseBody
    void showImage(@PathVariable("userAccount") String userAccount, HttpServletResponse response)
            throws ServletException, IOException {
        com.example.projectappchat.entity.User userInfo = userService.findUserByAccount(userAccount);
        response.getOutputStream().write(userInfo.getUserLogo());
        response.getOutputStream().close();
    }

    @GetMapping("/403")
    public ModelAndView accessDenied(Principal principal) {

        ModelAndView modelAndView = new ModelAndView("403Page");

        if (principal != null) {

            User loginUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginUser);

            modelAndView.addObject("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";

            modelAndView.addObject("message", message);

        }

        return modelAndView;
    }



}