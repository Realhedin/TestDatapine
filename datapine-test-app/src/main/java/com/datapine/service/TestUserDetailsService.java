package com.datapine.service;

import com.datapine.dao.UserDAO;
import com.datapine.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Helper class to manually add roles to the logged users.
 *
 * Created by Dmitrii on 9/7/15.
 */
public class TestUserDetailsService implements UserDetailsService {

    private final static Logger logger = Logger.getLogger(TestUserDetailsService.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Log4j: User: "+username + " is trying to login...");

        User user = userDAO.findByEmail(username);
        if (user == null) {
            logger.info("Log4j: User: " +username + " was not found!");
            logger.info("Log4j: Redirected to login page");
            return null;
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword()
            , getAuthorities(user));
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        if (user.getEmail().equals("admin@admin.com")) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return authorityList;
        }
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorityList;
    }
}
