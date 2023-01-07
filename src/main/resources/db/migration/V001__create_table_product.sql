CREATE TABLE product (
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(50) NOT NULL,
     photo VARCHAR(100),
     description VARCHAR(50) NOT NULL,
     active BOOLEAN NOT NULL,
     price DOUBLE NOT NULL
);