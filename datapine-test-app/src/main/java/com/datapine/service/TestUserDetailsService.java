package com.datapine.service;

import com.datapine.dao.UserDAO;
import com.datapine.domain.User;
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
 * Created by Dmitrii on 9/7/15.
 */
public class TestUserDetailsService implements UserDetailsService {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TestUserDetailsService.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("User: "+username + " is trying to login...");

        User user = userDAO.findByEmail(username);
        if (user == null) {
            logger.info("User: " +username + " was not found!");
            logger.info("Redirected to login page");
            return null;
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword()
            , getAuthorities(user));
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        if (user.getEmail().equals("admin")) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            logger.info("User: "+user.getEmail()+" logged as ADMINISTRATOR");
            return authorityList;
        }
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        logger.info("User: "+user.getEmail()+ " successfully logged");
        return authorityList;
    }
}
