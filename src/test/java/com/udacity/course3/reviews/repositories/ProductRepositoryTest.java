package com.udacity.course3.reviews.repositories;

import com.udacity.course3.reviews.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testFindByProductId() {
        Product product = new Product();

        product.setProductName("Pride and Prejudice");

        entityManager.persist(product);
        entityManager.flush();

        Optional<Product> optional = repository.findById(product.getId());
        assertNotNull(optional);
        assertEquals(optional.get().getId(), product.getId());
    }

}