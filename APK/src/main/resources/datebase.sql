CREATE TABLE roles
(
    role_id int         NOT NULL AUTO_INCREMENT,
    name    varchar(45) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE company
(
    company_id     int          NOT NULL AUTO_INCREMENT,
    company_code   int          NOT NULL,
    company_name   varchar(255) NOT NULL,
    INN            varchar(100) NOT NULL,
    active         boolean      NOT NULL,
    type_property  varchar(100) NOT NULL,
    region         varchar(45)  NOT NULL,
    area           varchar(45)  NOT NULL,
    address        varchar(45)  NOT NULL,
    phone          varchar(20)  NOT NULL,
    email          varchar(45)  NOT NULL,
    industry       varchar(45)  NOT NULL,
    main_product   varchar(45)  NOT NULL,
    amount_workers int(11)      NOT NULL,
    PRIMARY KEY (company_id),
    UNIQUE KEY company_code_UNIQUE (company_code)
);

CREATE TABLE users
(
    user_id      int         NOT NULL AUTO_INCREMENT,
    company_id int,
    email        varchar(45) NOT NULL,
    password     varchar(64) NOT NULL,
    username     varchar(45) NOT NULL,
    surname      varchar(45) NOT NULL,
    patronymic   varchar(45) NOT NULL,
    phone        varchar(20) NOT NULL,
    region       varchar(45) NOT NULL,
    company_code int,
    enabled      tinyint(4) DEFAULT NULL,
    role_id      int,
    KEY company_fk_company (company_id),
    CONSTRAINT company_fk_company FOREIGN KEY (company_id) REFERENCES company (company_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    KEY users_fk_role (role_id),
    CONSTRAINT users_fk_role FOREIGN KEY (role_id) REFERENCES roles (role_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (user_id),
    UNIQUE KEY email_UNIQUE (email)
);

CREATE TABLE land
(
    land_id            int          NOT NULL AUTO_INCREMENT,
    location           varchar(100) NOT NULL,
    type_permitted_use varchar(100) NOT NULL,
    actual         varchar(100) NOT NULL,
    conditions         varchar(100) NOT NULL,
    crop_rotation_type varchar(100) NOT NULL,
    square             varchar(45)  NOT NULL,
    company_id int,
    KEY land_fk_company (company_id),
    CONSTRAINT land_fk_company FOREIGN KEY (company_id) REFERENCES company (company_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (land_id)
);

CREATE TABLE employee
(
    employee_id             int          NOT NULL AUTO_INCREMENT,
    username                varchar(45)  NOT NULL,
    surname                 varchar(45)  NOT NULL,
    patronymic              varchar(45)  NOT NULL,
    gender                  varchar(10)  NOT NULL,
    age                     int(11)      NOT NULL,
    speciality              varchar(100) NOT NULL,
    total_work_experience   varchar(100) NOT NULL,
    company_work_experience varchar(100) NOT NULL,
    job_title               varchar(100) NOT NULL,
    phone                   varchar(20)  NOT NULL,
    email                   varchar(45)  NOT NULL,
    salary                  varchar(45)  NOT NULL,
    company_id int,
    KEY employee_fk_company (company_id),
    CONSTRAINT employee_fk_company FOREIGN KEY (company_id) REFERENCES company (company_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (employee_id),
    UNIQUE KEY email_UNIQUE (email)
);

CREATE TABLE plannedProduction
(
    planned_production_id int         NOT NULL AUTO_INCREMENT,
    product_type          varchar(45) NOT NULL,
    product_name          varchar(45) NOT NULL,
    planned_property      int(11)     NOT NULL,
    planned_revenue       int(11)     NOT NULL,
    planned_profit        int(11),
    company_id int,
    KEY plannedProduction_fk_company (company_id),
    CONSTRAINT plannedProduction_fk_company FOREIGN KEY (company_id) REFERENCES company (company_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (planned_production_id)
);

CREATE TABLE productionCosts
(
    production_costs_id int         NOT NULL AUTO_INCREMENT,
    product_type        varchar(45) NOT NULL,
    salary              int(11)     NOT NULL,
    material_costs      int(11)     NOT NULL,
    depreciation_costs  int(11)     NOT NULL,
    other_costs         int(11)     NOT NULL,
    company_id int,
    planned_production_id int,
    KEY productionCosts_fk_plannedProduction (planned_production_id),
    CONSTRAINT productionCosts_fk_plannedProduction FOREIGN KEY (planned_production_id) REFERENCES plannedProduction (planned_production_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    KEY productionCosts_fk_company (company_id),
    CONSTRAINT productionCosts_fk_company FOREIGN KEY (company_id) REFERENCES company (company_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (production_costs_id)
);

CREATE TABLE expenses
(
    expenses_id   int         NOT NULL AUTO_INCREMENT,
    expenses_date varchar(45),
    name_purchase varchar(45) NOT NULL,
    price         int(11)     NOT NULL,
    product_type  varchar(45) NOT NULL,
    company_id int,
    production_costs_id int,
    KEY expenses_fk_productionCosts (production_costs_id),
    CONSTRAINT expenses_fk_productionCosts FOREIGN KEY (production_costs_id) REFERENCES productionCosts (production_costs_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    KEY expenses_fk_company (company_id),
    CONSTRAINT expenses_fk_company FOREIGN KEY (company_id) REFERENCES company (company_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (expenses_id)
);

CREATE TABLE procurement
(
    procurement_id int         NOT NULL AUTO_INCREMENT,
    purchase_date  varchar(45),
    name_purchase  varchar(45) NOT NULL,
    price          int(11)     NOT NULL,
    product_type   varchar(45) NOT NULL,
    unit           varchar(45) NOT NULL,
    amount         int(11)     NOT NULL,
    company_id int,
    expenses_id int,
    KEY procurement_id_fk_expenses (expenses_id),
    CONSTRAINT procurement_id_fk_expenses FOREIGN KEY (expenses_id) REFERENCES expenses (expenses_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    KEY procurement_fk_company (company_id),
    CONSTRAINT procurement_fk_company FOREIGN KEY (company_id) REFERENCES company (company_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (procurement_id)
);

CREATE TABLE product
(
    product_id       int         NOT NULL AUTO_INCREMENT,
    product_type     varchar(45) NOT NULL,
    date_manufacture varchar(45) NOT NULL,
    unit             varchar(45) NOT NULL,
    amount           int(11)     NOT NULL,
    production_cost  int(11),
    cost_price       int(11),
    company_id int,
    planned_production_id int,
    KEY product_fk_plannedProduction (planned_production_id),
    CONSTRAINT product_fk_plannedProduction FOREIGN KEY (planned_production_id) REFERENCES plannedProduction (planned_production_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    KEY product_fk_company (company_id),
    CONSTRAINT product_fk_company FOREIGN KEY (company_id) REFERENCES company (company_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (product_id)
);

CREATE TABLE sale
(
    sale_id      int         NOT NULL AUTO_INCREMENT,
    product_type varchar(45) NOT NULL,
    date_sale    varchar(45) NOT NULL,
    unit         varchar(45) NOT NULL,
    amount       int(11)     NOT NULL,
    revenue      int(11),
    price          int(11),
    profit       int(11),
    company_id int,
    product_id int,
    KEY sale_fk_product (product_id),
    CONSTRAINT sale_fk_product FOREIGN KEY (product_id) REFERENCES product (product_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    KEY sale_fk_company (company_id),
    CONSTRAINT sale_fk_company FOREIGN KEY (company_id) REFERENCES company (company_id)  ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (sale_id)
);

INSERT INTO roles (name)
VALUES ('VIEWER');
INSERT INTO roles (name)
VALUES ('CREATEREDITOR');
INSERT INTO roles (name)
VALUES ('MANAGER');
INSERT INTO roles (name)
VALUES ('ADMIN');

INSERT INTO users (email, role_id, password, username, surname, patronymic, phone, region, enabled)
VALUES ('none1@bk.ru', 1, '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'patrick', 'none',
        'none', 'none', 'none', '1');
INSERT INTO users (email, role_id, password, username, surname, patronymic, phone, region, enabled)
VALUES ('none2@bk.ru', 2, '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'alex', 'none', 'none',
        'none', 'none', '1');
INSERT INTO users (email, role_id, password, username, surname, patronymic, phone, region, enabled)
VALUES ('none3@bk.ru', 3, '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'manager', 'none',
        'none', 'none', 'none', '1');
INSERT INTO users (email, role_id, password, username, surname, patronymic, phone, region, enabled)
VALUES ('none4@bk.ru', 4, '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'admin', 'none', 'none',
        'none', 'none', '1');
INSERT INTO users (email, role_id, password, username, surname, patronymic, phone, region, enabled)
VALUES ('none5@bk.ru', 4, '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'admin', 'none', 'none',
        'none', 'none', '1');
INSERT INTO users (email, role_id, password, username, surname, patronymic, phone, region, enabled)
VALUES ('none6@bk.ru', 4, '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'admin', 'none', 'none',
        'none', 'none', '1');
