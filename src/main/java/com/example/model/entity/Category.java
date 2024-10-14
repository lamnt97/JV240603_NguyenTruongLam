package com.example.model.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name",length = 100,unique = true,nullable = false)
    private String name;
    @Column(name = "status")
    @ColumnDefault("1")
    private boolean status;
    @OneToMany(mappedBy = "category")
    private Set<Product> products;
    public Category() {
    }

    public Category(int id, String name, boolean status, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}