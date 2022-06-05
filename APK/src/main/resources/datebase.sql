CREATE TABLE users (
                         user_id int NOT NULL AUTO_INCREMENT,
                         email varchar(45) NOT NULL,
                         password varchar(64) NOT NULL,
                         username varchar(45) NOT NULL,
                         surname varchar(45) NOT NULL,
                         patronymic varchar(45) NOT NULL,
                         company varchar(45) NOT NULL,
                         region varchar(45) NOT NULL,
                         enabled tinyint(4) DEFAULT NULL,
                         PRIMARY KEY (user_id),
                         UNIQUE KEY email_UNIQUE (email)
);

CREATE TABLE roles (
                         role_id int NOT NULL AUTO_INCREMENT,
                         name varchar(45) NOT NULL,
                         PRIMARY KEY (role_id)
);

CREATE TABLE users_roles (
                               user_id int NOT NULL,
                               role_id int NOT NULL,
                               KEY user_fk_idx (user_id),
                               KEY role_fk_idx (role_id),
                               CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES roles (role_id),
                               CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users (user_id)
);


INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('CREATOR/EDITOR');
INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO users (username, password, enabled, email) VALUES ('patrick', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', '1', 'none1@bk.ru');
INSERT INTO users (username, password, enabled, email) VALUES ('alex', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', '1', 'none2@bk.ru');
INSERT INTO users (username, password, enabled, email) VALUES ('john', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', '1', 'none3@bk.ru');
INSERT INTO users (username, password, enabled, email) VALUES ('namhm', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', '1', 'none4@bk.ru');
INSERT INTO users (username, password, enabled, email) VALUES ('admin', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', '1', 'none5@bk.ru');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1); -- user patrick has role USER
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2); -- user alex has role CREATOR
INSERT INTO users_roles (user_id, role_id) VALUES (3, 3); -- user john has role EDITOR
INSERT INTO users_roles (user_id, role_id) VALUES (4, 2); -- user namhm has role CREATOR
INSERT INTO users_roles (user_id, role_id) VALUES (4, 3); -- user namhm has role EDITOR
INSERT INTO users_roles (user_id, role_id) VALUES (5, 1); -- user admin has role ADMIN