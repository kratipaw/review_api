package com.udacity.course3.reviews.repositories;

import com.udacity.course3.reviews.documents.ReviewDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewDocRepository extends MongoRepository<ReviewDoc, String> {
}
