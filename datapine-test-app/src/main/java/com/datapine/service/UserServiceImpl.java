package com.datapine.service;

import com.datapine.dao.UserDAO;
import com.datapine.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation for UserService interface
 *
 * Created by Dmitrii on 9/4/15.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User register(String email, String password) {
        User user = new User(email,password);
        userDAO.save(user);
        return user;
    }

    @Override
    @Transactional
    public User updatePassword(Long userId, String oldPassword, String newPassword) {
        User user = userDAO.findById(userId);
        if (user != null) {
            user.setPassword(newPassword);
        }
        userDAO.save(user);
        return user;
    }
}
