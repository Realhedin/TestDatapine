package com.datapine.dao.impl;

import com.datapine.dao.ItemDAO;
import com.datapine.domain.Item;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Dmitrii on 9/8/15.
 */
@Repository
@Transactional(readOnly = true)
public class ItemDAOImpl implements ItemDAO {


    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    @Secured("ACL_ADD_ITEM")
    public void save(Item item) {

        if (item.getId() == null) {
            em.persist(item);
        } else {
           em.merge(item);
        }
    }


    @Override
    public boolean delete(long id) {
        return em.createNamedQuery(Item.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Item> findAllOrderById(long userId) {
        return em.createNamedQuery(Item.ALL_SORTED, Item.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Item get(long id) {
        return em.find(Item.class,id);
    }
}
