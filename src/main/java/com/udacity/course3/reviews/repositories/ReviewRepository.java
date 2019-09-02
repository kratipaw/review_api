package com.udacity.course3.reviews.repositories;

import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findAllByProductId(Integer productId);

    @Query("SELECT r.id FROM Review r JOIN r.product p WHERE p.id = :productId")
    List<Integer> findIdByProductId(Integer productId);
}
