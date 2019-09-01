package com.udacity.course3.reviews.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "product_name")
    @NotNull
    private String productName;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[ " + this.id + ", " + this.productName + " ]";
    }
}
