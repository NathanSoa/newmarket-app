CREATE TABLE role (
  id BIGINT PRIMARY KEY auto_increment,
  role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_role (
   user_id BiGINT,
   role_id BIGINT,
   FOREIGN KEY (user_id) REFERENCES app_user(id),
   FOREIGN KEY (role_id) REFERENCES role(id)
);