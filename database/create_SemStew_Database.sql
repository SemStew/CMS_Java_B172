CREATE TABLE LANGUAGES (
  id_language integer PRIMARY KEY,
  name varchar(32)
);

CREATE TABLE FOOD(
  id_food serial PRIMARY KEY,
  price integer NOT NULL,
  image_name varchar(64)
);

CREATE TABLE FOOD_NAME(
  id_food integer NOT NULL REFERENCES FOOD(id_food),
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language),
  name varchar(64) NOT NULL,
  description varchar(256)
);

CREATE TABLE BEVERAGES(
  id_beverage serial PRIMARY KEY,
  price integer NOT NULL   
);

CREATE TABLE BEVERAGES_NAME(
  id_beverage integer REFERENCES BEVERAGES(id_beverage),
  id_language integer REFERENCES LANGUAGES(id_language),
  name varchar(64) NOT NULL,
  description varchar(256)
);

CREATE TABLE ALLERGENS(
  id_allergen integer PRIMARY KEY
);

CREATE TABLE FOOD_ALLERGEN(
  id_food integer REFERENCES FOOD(id_food) NOT NULL,
  id_allergen integer REFERENCES ALLERGENS(id_allergen) NOT NULL
);

CREATE TABLE BEVERAGE_ALLERGEN(
  id_beverage integer REFERENCES BEVERAGES(id_beverage) NOT NULL,
  id_allergen integer REFERENCES ALLERGENS(id_allergen) NOT NULL
);

CREATE TABLE ALLERGENS_NAME(
  id_allergen integer NOT NULL REFERENCES ALLERGENS(id_allergen),
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language),
  allergen varchar(64) NOT NULL 
);

CREATE TABLE REZERVATION(
  r_date date NOT NULL,
  time_from time NOT NULL,
  time_to time NOT NULL,
  n_table integer NOT NULL
);

CREATE TABLE PERFORMANCE(
  id_performance integer PRIMARY KEY,
  name varchar(64)
);

CREATE TABLE EMPLOYEE(
  id_employee integer PRIMARY KEY,
  perform integer REFERENCES PERFORMANCE(id_performance),
  name varchar(64) NOT NULL,
  surname varchar(64) NOT NULL,
  phone varchar(16),
  mail varchar(128)
);

CREATE TABLE ORDERS(
  o_date date NOT NULL,
  time_from time NOT NULL,
  time_to time NOT NULL,
  address text NOT NULL,
  id_food integer REFERENCES FOOD(id_food) NOT NULL
);