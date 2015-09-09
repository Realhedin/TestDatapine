package com.datapine;

import com.datapine.dao.ItemDAO;
import com.datapine.dao.UserDAO;
import com.datapine.domain.Item;
import com.datapine.domain.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitrii on 9/6/15.
 */
@ContextConfiguration( locations = {
        "classpath:META-INF/spring/applicationContext.xml",
        "classpath:META-INF/spring/applicationPersistence.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSecurity {

    static ApplicationContext applicationContext = null;
//    static InMemoryUserDetailsManager userDetailsService = null;
     //static UserDetailsService userDetailsService = null;

   @Autowired
    private UserDAO userDAO;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private UserDetailsService userDetailsService;

    @BeforeClass
    public static void setup()
    {
        //Create application context instance
        //applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/applicationSecurity.xml");
        //Get user details service configured in configuration
        //userDetailsService = applicationContext.getBean(InMemoryUserDetailsManager.class);
        //userDetailsService = (UserDetailsService) applicationContext.getBean("userDetailsService");
    }

    /**
     * Test the valid user with valid role
     * */
    @Test
    public void testValidRole()
    {
        //Get the user by username from configured user details service
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin@admin.com");
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        User u = new User("user@user.com","user");
        u.setId(1L);
        userDAO.update(u);
    }


    /**
     * Test the valid user with INVALID role
     * */
    @Test (expected = AccessDeniedException.class)
    public void testInvalidRole()
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername ("user@user.com");
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_INVALID"));
        Authentication authToken = new UsernamePasswordAuthenticationToken (userDetails.getUsername(), userDetails.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        User u = new User("user@user.com","user");
        u.setId(1L);
        userDAO.update(u);
    }


    /**
     * ACL part that will protect adding item without permission
     */
    @Test
    public void testItemAdd()
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername("user@user.com");
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        Item item = new Item("keyboard");
        item.setUser(userDAO.findByEmail(userDetails.getUsername()));
        itemDAO.save(item);
    }


    @Test (expected = AccessDeniedException.class)
    public void testItemAddInvalid()
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername("acl@acl.com");
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        Item item = new Item("keyboard");
        item.setUser(userDAO.findByEmail(userDetails.getUsername()));
        itemDAO.save(item);
    }



}
