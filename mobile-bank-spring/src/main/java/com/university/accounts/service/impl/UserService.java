package com.university.accounts.service.impl;

import com.university.accounts.dao.UserRepository;
import com.university.accounts.dto.UserDto;
import com.university.accounts.entity.Credentials;
import com.university.accounts.entity.User;
import com.university.accounts.service.security.AuthenticationService;
import com.university.accounts.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;

    public String loginIn(Credentials authenticationRequest) throws ValidationException {
       return authenticationService.authenticate(authenticationRequest);
    }

    public void createUser(UserDto userDto) throws ValidationException {
        if (userRepository.existsByLogin(userDto.getLogin())) {
            throw new ForbiddenException("User with such userName is already exists");
        }
        if (userRepository.existsByPhone(userDto.getPhone())) {
            throw new ForbiddenException("User with such phone is already exists");
        }
        userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));

        User.UserBuilder builder = User.builder()
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .phone(userDto.getPhone())
                .address(userDto.getAddress());
        userRepository.save(builder.build());
    }
}
