package com.udacity.course3.reviews.repositories;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testFindAllByReviewId() {

        Review review = this.getReview();

        Comment comment = new Comment();
        comment.setCommentString("Great series");
        comment.setReview(review);

        this.entityManager.persist(comment);

        List<Comment> comments = commentRepository.findAllByReviewId(review.getId());
        assertNotNull(comments);
        assertEquals(comment.getCommentString(), comments.get(0).getCommentString());
        assertEquals(comment.getReview().getId(), review.getId());
    }

    private Review getReview(){
        Product product = new Product();
        product.setProductName("Harry Potter series");

        this.entityManager.persist(product);
        this.entityManager.flush();

        Review review = new Review();
        review.setReviewTitle("Book Review");
        review.setProduct(product);

        this.entityManager.persist(review);
        this.entityManager.flush();

        return review;
    }

}