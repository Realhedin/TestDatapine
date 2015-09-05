package com.datapine;

import com.datapine.dao.UserDAO;
import com.datapine.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Dmitrii on 9/4/15.
 */
@ContextConfiguration( locations = {
        "classpath:META-INF/spring/applicationContext.xml",
        "classpath:META-INF/spring/applicationPersistence.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class Testing {

    @Autowired
    private UserDAO userDAO;
    private User user;

    @Before
    public void before() {
        user = new User();
        user.setEmail("p");
        user.setPassword("p");

        userDAO.save(user);
    }


    @Test
    public void test1(){

        Assert.assertNotNull(user);
        User user2 = userDAO.findById(user.getId());

        Assert.assertNotNull(user2);
        Assert.assertEquals("p", user2.getEmail());
    }
}
