package com.cinefy.Cinefy.service;

import com.cinefy.Cinefy.dao.UserRepository;
import com.cinefy.Cinefy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("YOU ARE ABLE TO REACH HERE _____________>>>>>>>>>");
        Optional<User>optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println(optionalUser.get().toString());
        System.out.println("*****************");
        return new CustomUserDetails(optionalUser.get());
    }
}
