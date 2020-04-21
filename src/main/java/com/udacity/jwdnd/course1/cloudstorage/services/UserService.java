package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return org.springframework.security.core.userdetails.User.withUsername(user.getUserid()).password(user.getPassword()).authorities("role").build();
            }
            throw new UsernameNotFoundException("User not found");
        } catch (UsernameNotFoundException e) {
            throw e;
        }
    }

    public User insertUser(User user) throws Exception {
        try {
            String encodedPW = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPW);
            userRepository.insert(user);
            return user;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean isUserExists(String  username) throws Exception {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }  catch (Exception e) {
                throw e;
        }
    }
}