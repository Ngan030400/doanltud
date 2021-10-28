package com.example.projectappchat.service.userDetails;


import com.example.projectappchat.entity.User;
import com.example.projectappchat.repository.RoleRepository;
import com.example.projectappchat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {

        User user = this.userRepository.findUserAccount(userAccount);

        if (user == null) {
            System.out.println("User not found! " + userAccount);
            throw new UsernameNotFoundException("User " + userAccount
                    + " was not found in the database");
        }

        System.out.println("Found user: " + user);

        List<String> roleNames = this.roleRepository.getRoleNames(user.getUserId());

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantedAuthorityList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new
                org.springframework.security.core.userdetails.User(user.getUserAccount(),
                user.getUserPassword(), grantedAuthorityList);

        return userDetails;
    }
}
