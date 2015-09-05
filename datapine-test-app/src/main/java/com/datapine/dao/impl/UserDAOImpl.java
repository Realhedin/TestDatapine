package com.datapine.dao.impl;

import com.datapine.dao.UserDAO;
import com.datapine.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dmitrii on 9/4/15.
 */
@Repository
@Transactional(readOnly = true)
public class UserDAOImpl implements UserDAO {


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(User user) {
        if (user.getId() == null) {
           em.persist(user);
        } else {
            em.merge(user);
        }
        System.out.println(user.getId());
    }

    @Override
    @Transactional
    public User update(User user) {
        em.merge(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        try {
            return em.createNamedQuery("User.getByEmail", User.class).setParameter(1, email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> findAllOrderById() {
        return em.createNamedQuery("User.findAllOrderById", User.class).getResultList();
    }


    @Override
    public User getReference(Long id) {
        return em.getReference(User.class,id);
    }
}
