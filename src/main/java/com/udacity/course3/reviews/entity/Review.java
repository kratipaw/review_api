package com.udacity.course3.reviews.entity;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int id;

    @Column(name = "title")
    private String reviewTitle;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public int getId() {
        return id;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "[ " + this.id + ", " + this.product.toString() + " ]";
    }
}
