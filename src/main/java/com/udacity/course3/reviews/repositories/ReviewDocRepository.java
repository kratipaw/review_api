package com.udacity.course3.reviews.repositories;

import com.udacity.course3.reviews.documents.ReviewDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDocRepository extends MongoRepository<ReviewDoc, Integer> {
}
