package com.datapine.dao;

import com.datapine.domain.Item;

import java.util.List;

/**
 * Interface for DAO object for Item domain
 *
 * Created by Dmitrii on 9/8/15.
 */
public interface ItemDAO {

    void save(Item item);

//    boolean delete(long id);

    boolean delete(Item item);

    List<Item> findAllOrderById(long userId);

    Item get(long id);
}
