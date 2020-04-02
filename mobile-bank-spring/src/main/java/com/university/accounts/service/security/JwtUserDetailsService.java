package com.university.accounts.service.security;

import com.university.accounts.dao.UserRepository;
import com.university.accounts.entity.User;
import com.university.accounts.exceptions.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(name).get();
        if (user == null) {
            user = userRepository.findByPhone(name)
                    .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage()));
        }
        return new org.springframework.security.core.userdetails.User(name, user.getPassword(),
                new ArrayList<>());
    }
}
