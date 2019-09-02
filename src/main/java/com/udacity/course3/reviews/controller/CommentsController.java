package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.documents.CommentDoc;
import com.udacity.course3.reviews.documents.ReviewDoc;
import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repositories.CommentRepository;
import com.udacity.course3.reviews.repositories.ProductRepository;
import com.udacity.course3.reviews.repositories.ReviewDocRepository;
import com.udacity.course3.reviews.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here
    @Autowired
    private
    CommentRepository commentRepository;

    @Autowired
    private
    ReviewRepository reviewRepository;

    @Autowired
    private
    ProductRepository productRepository;

    @Autowired
    private ReviewDocRepository reviewDocRepository;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId, @RequestBody Comment comment) {

        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if(!optionalReview.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        comment.setReview(optionalReview.get());
        commentRepository.save(comment);

        Optional<ReviewDoc> optionalReviewDoc = reviewDocRepository.findById(reviewId);
        if(!optionalReviewDoc.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ReviewDoc reviewDoc = optionalReviewDoc.get();
        reviewDoc.addCommentDocs(new CommentDoc(comment));
        reviewDocRepository.save(reviewDoc);

        return ResponseEntity.ok().build();
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        return ResponseEntity.ok(commentRepository.findAllByReviewId(reviewId));
    }
}