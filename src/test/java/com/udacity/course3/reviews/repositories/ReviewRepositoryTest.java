package com.udacity.course3.reviews.repositories;

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
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testFindReviewsByProductId() {
        Product product = new Product();
        product.setProductName("Product_title");

        entityManager.persist(product);
        entityManager.flush();

        Review review = new Review();
        review.setReviewTitle("Review_title");
        review.setProduct(product);

        entityManager.persist(review);
        entityManager.flush();

        List<Review> reviews = reviewRepository.findAllByProductId(product.getId());
        assertNotNull(reviews);
        assertEquals(review.getReviewTitle(), reviews.get(0).getReviewTitle());
    }

}