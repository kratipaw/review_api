package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.documents.ReviewDoc;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repositories.ProductRepository;
import com.udacity.course3.reviews.repositories.ReviewDocRepository;
import com.udacity.course3.reviews.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here
    @Autowired
    private
    ReviewRepository reviewRepository;

    @Autowired
    private
    ProductRepository productRepository;

    @Autowired
    private ReviewDocRepository reviewDocRepository;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId, @RequestBody Review review) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (!optionalProduct.isPresent())
            return ResponseEntity.notFound().build();

        //Save to MySQL via JPARepo
        review.setProduct(optionalProduct.get());
        reviewRepository.save(review);

        //Save to MongoDB via MngoRepo
        ReviewDoc reviewDoc = new ReviewDoc(review);
        reviewDocRepository.save(reviewDoc);

        return ResponseEntity.ok().build();
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (!optionalProduct.isPresent())
            return ResponseEntity.notFound().build();

        List<Integer> reviewIds = reviewRepository.findIdByProductId(productId);

        List<ReviewDoc> reviews = (List<ReviewDoc>) reviewDocRepository.findAllById(reviewIds);

        return ResponseEntity.ok(reviews);
    }
}