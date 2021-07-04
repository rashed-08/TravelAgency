package com.web.travelagency.service.impl;

import com.web.travelagency.model.Role;
import com.web.travelagency.model.User;
import com.web.travelagency.repository.RoleRepository;
import com.web.travelagency.repository.UserRepository;
import com.web.travelagency.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserServices {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(User user) {
        try {
            User checkUser = findUserByUsername(user.getUsername());
            if (checkUser == null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setIsActive(true);
                Role userRole = roleRepository.findByRole("ROLE_USER");
                user.setRole(new HashSet<>(Arrays.asList(userRole)));
                userRepository.save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public User findUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
