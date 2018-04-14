CREATE TABLE LANGUAGES (
  id_language serial PRIMARY KEY,
  name varchar(32)
);

CREATE TABLE CATEGORIES(
  id_category serial PRIMARY KEY
);

CREATE TABLE CATEGORIES_NAME(
  id_category integer NOT NULL REFERENCES CATEGORIES(id_category),
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language),
  description text NOT NULL
);

CREATE TABLE FOOD(
  id_food serial PRIMARY KEY,
  price integer NOT NULL,
  image_name varchar(64),
  id_category integer REFERENCES CATEGORIES(id_category) NOT NULL
);

CREATE TABLE FOOD_NAME(
  id_food integer NOT NULL REFERENCES FOOD(id_food),
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language),
  name varchar(64) NOT NULL,
  description varchar(256)
);

CREATE TABLE BEVERAGES(
  id_beverage serial PRIMARY KEY,
  price integer NOT NULL,
  id_category integer REFERENCES CATEGORIES(id_category) NOT NULL   
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
  n_table integer NOT NULL
);

CREATE TABLE REZERVATION_CONFIG(
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language) NOT NULL,
  header varchar(128) NOT NULL,
  table_number integer NOT NULL,
  time_from_desc varchar(64) NOT NULL
);

CREATE TABLE PERFORMANCE(
  id_performance serial PRIMARY KEY,
  name varchar(64)
);

CREATE TABLE EMPLOYEE(
  id_employee serial PRIMARY KEY,
  perform integer REFERENCES PERFORMANCE(id_performance),
  name varchar(64) NOT NULL,
  surname varchar(64) NOT NULL,
  phone varchar(16),
  mail varchar(128)
);

CREATE TABLE ORDERS(
  o_date date NOT NULL,
  address text NOT NULL,
  id_food integer REFERENCES FOOD(id_food) NOT NULL,
  id_beverage integer REFERENCES BEVERAGES(id_beverage)
);

CREATE TABLE NEWS(
  id_news serial PRIMARY KEY,
  n_date date NOT NULL  
);

CREATE TABLE NEWS_NAME(
  id_news integer REFERENCES NEWS(id_news) NOT NULL,
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language) NOT NULL,
  description text NOT NULL
);

CREATE TABLE GENERAL_CONFIG(
  url_main_image text NOT NULL
);

CREATE TABLE INTRO_CONFIG(
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language) NOT NULL,
  header varchar(128) NOT NULL,
  short_description text NOT NULL,
  news_header varchar(64) NOT NULL
); 

CREATE TABLE MENUS_CONFIG(
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language) NOT NULL,
  header varchar(128) NOT NULL
);

CREATE TABLE MENUS(
  id_menu serial PRIMARY KEY,
  url_image text NOT NULL
);

CREATE TABLE MENUS_NAME(
  id_menu integer NOT NULL REFERENCES MENUS(id_menu) NOT NULL,
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language) NOT NULL,
  description varchar(128) NOT NULL
);

CREATE TABLE ABOUT_US_CONFIG(
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language) NOT NULL,
  header varchar(128) NOT NULL,
  description text NOT NULL,
  fotogallery_header varchar(64) NOT NULL
);

CREATE TABLE IMAGES(
  id_image serial PRIMARY KEY,
  url text NOT NULL
);

CREATE TABLE CONTACT_CONFIG(
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language) NOT NULL,
  header varchar(128) NOT NULL,
  description text NOT NULL,
  url_image1 text,
  url_image2 text
);