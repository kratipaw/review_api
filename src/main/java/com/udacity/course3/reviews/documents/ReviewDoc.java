package com.udacity.course3.reviews.documents;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Document("reviews")
public class ReviewDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String reviewTitle;

    private Product product;

    private List<CommentDoc> commentDocs;

    public ReviewDoc(){
        commentDocs = new ArrayList<>();
    }

    public ReviewDoc(Review review){

        this.id = review.getId();
        this.reviewTitle = review.getReviewTitle();
        this.product = review.getProduct();
        this.commentDocs = new ArrayList<>();
    }

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

    public List<CommentDoc> getCommentDocs() {
        return commentDocs;
    }

    public void addCommentDocs(CommentDoc commentDoc) {
        commentDocs.add(commentDoc);
    }
}
