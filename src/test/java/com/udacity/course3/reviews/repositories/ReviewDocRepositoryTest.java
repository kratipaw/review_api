package com.udacity.course3.reviews.repositories;

import com.udacity.course3.reviews.documents.CommentDoc;
import com.udacity.course3.reviews.documents.ReviewDoc;
import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewDocRepositoryTest {

    @Autowired
    private ReviewDocRepository reviewDocRepository;

    @Test
    public void testMongoRepositoryFindAll(){
        Product product = new Product();
        product.setProductName("Harry Potter Series");

        Review review = new Review();
        review.setReviewTitle("Harry Ptter series review");
        review.setProduct(product);

        Comment comment1 = new Comment();
        comment1.setCommentString("Great series!!");
        comment1.setReview(review);

        ReviewDoc reviewDoc = new ReviewDoc(review);
        reviewDoc.addCommentDocs(new CommentDoc(comment1));

        ReviewDoc savedReviewInfo = reviewDocRepository.save(reviewDoc);
        assertEquals(reviewDoc.getReviewTitle(), savedReviewInfo.getReviewTitle());
        assertEquals(reviewDoc.getProduct(), savedReviewInfo.getProduct());
        assertEquals(reviewDoc.getCommentDocs(), savedReviewInfo.getCommentDocs());

        List<ReviewDoc> reviews = reviewDocRepository.findAll();
        assertNotNull(reviews);
        ReviewDoc retrievedReviewDoc = reviews.get(0);
        assertEquals(reviewDoc.getId(), retrievedReviewDoc.getId());
        assertEquals(reviewDoc.getReviewTitle(), retrievedReviewDoc.getReviewTitle());

    }
}