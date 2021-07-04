package com.web.travelagency.service;

import com.web.travelagency.model.User;

public interface UserServices {
    void createUser(User user);
    User findUserByUsername(String username);
}
