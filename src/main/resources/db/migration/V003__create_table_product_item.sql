CREATE TABLE product_item(
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     quantity INT,
     product_id BIGINT NOT NULL,
     user_id BIGINT NOT NULL,
     FOREIGN KEY (product_id) REFERENCES product(id),
     FOREIGN KEY (user_id) REFERENCES app_user(id)
);