package ua.greencampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.entity.User;

import java.util.Collections;
import java.util.List;

/**
 * Created by Arsenii on 25.03.2016.
 */
@Service("userDetailsService")
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.readByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                getGrantedAuthority(user));
    }

    private List<GrantedAuthority> getGrantedAuthority(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
    }
}