package com.university.accounts.service.security;

import com.university.accounts.dao.UserRepository;
import com.university.accounts.entity.Credentials;
import com.university.accounts.entity.User;
import com.university.accounts.security.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService jwtUserDetailsService;
    private JwtToken jwtToken;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager,
                             JwtUserDetailsService jwtUserDetailsService,
                             JwtToken jwtToken) {
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtToken = jwtToken;
    }

    public String authenticate(Credentials authenticationRequest) throws ValidationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));

            final UserDetails userDetails = jwtUserDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtToken.generateToken(userDetails);
            return String.format("Bearer %s", token);
        } catch (DisabledException e) {
            throw new ValidationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new ValidationException("INVALID_CREDENTIALS", e);
        }
    }

    public User getAuthenticatedUser() throws ValidationException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        return userRepository.findByLogin(username).get();
    }
}
