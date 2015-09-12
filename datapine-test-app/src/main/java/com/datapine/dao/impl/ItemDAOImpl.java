package com.datapine.dao.impl;

import com.datapine.dao.ItemDAO;
import com.datapine.domain.Item;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
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
    @Secured("ACL_ADD_ITEM") // Protection from inMemory realization from /acl
    public void save(Item item) {

        if (item.getId() == null) {
            em.persist(item);
        } else {
           em.merge(item);
        }
    }


//    @Override
//    @PreAuthorize("hasPermission(#item, 'WRITE')")
//    public boolean delete(long id) {
//        return em.createNamedQuery(Item.DELETE)
//                .setParameter("id",id)
//                .executeUpdate() != 0;
//    }

    @Override
    @PostAuthorize("hasPermission(#item, 'WRITE')")    //protection from acl tables
    public boolean delete(Item item) {
        return em.createNamedQuery(Item.DELETE)
                .setParameter("id",item.getId())
                .executeUpdate() != 0;
    }

    @Override
    //works but doesn't update list of Items after add (see ACL_ENTRY table)
//    @PostFilter("hasPermission(filterObject,'READ')")     //protection from acl tables
    public List<Item> findAllOrderById(long userId) {
        return em.createNamedQuery(Item.ALL_SORTED, Item.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    @PostAuthorize("hasPermission(returnObject,'READ')")    //protection from acl tables
    public Item get(long id) {
        return em.find(Item.class,id);
    }
}
