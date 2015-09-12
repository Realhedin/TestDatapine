package com.datapine.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by Dmitrii on 9/8/15.
 */
@Entity
@Table(name = "items")
@NamedQueries({
        @NamedQuery(name = Item.DELETE, query = "DELETE FROM Item m WHERE m.id=:id"),
        @NamedQuery(name = Item.ALL_SORTED, query = "SELECT m FROM Item m WHERE m.user.id=:userId ORDER BY m.id DESC")
})
public class Item {

    public static final String DELETE = "Item.delete";
    public static final String ALL_SORTED = "Item.getAll";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "product", nullable = false)
    private String product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //constuctors
    public Item() {
    }

    public Item(String product) {
        this.product = product;
    }

    //setters & getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
