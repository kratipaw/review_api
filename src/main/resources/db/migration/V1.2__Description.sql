CREATE TABLE products(
    product_id INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (product_id)
);

CREATE TABLE reviews(
    review_id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    PRIMARY KEY (review_id),
    product_id INT NOT NULL,
    FOREIGN KEY(product_id)
    REFERENCES products(product_id)
);

CREATE TABLE comments(
    comment_id INT NOT NULL AUTO_INCREMENT,
    comment_text VARCHAR(512),
    PRIMARY KEY (comment_id),
    review_id INT NOT NULL,
    FOREIGN KEY (review_id)
    REFERENCES reviews (review_id)
);
