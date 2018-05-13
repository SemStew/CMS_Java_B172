CREATE TABLE LANGUAGES (
  id_language serial PRIMARY KEY,
  name varchar(32)
);

CREATE TABLE ADMINS(
  id_admin serial PRIMARY KEY,
  name varchar(128) NOT NULL,
  password varchar(256)
);

CREATE TABLE RESTAURANT(
  id_restaurant serial PRIMARY KEY,
  id_admin integer NOT NULL REFERENCES ADMINS(id_admin),
  name varchar(128) NOT NULL,
  ico decimal,
  image text,
  email text,
  email_password text
);

CREATE TABLE BRANCH(
  id_branch serial PRIMARY KEY,
  id_restaurant integer NOT NULL REFERENCES RESTAURANT(id_restaurant),
  address varchar(256),
  phone varchar(16),
  description text,
  opening_hours text  
);

CREATE TABLE IMAGES(
  id_image serial PRIMARY KEY,
  id_restaurant integer NOT NULL REFERENCES RESTAURANT(id_restaurant),
  image text
);

CREATE TABLE MAIN_CATEGORIES(
  id_main_category serial PRIMARY KEY
);

CREATE TABLE MAIN_CATEGORIES_NAME(
  id_main_category integer NOT NULL REFERENCES MAIN_CATEGORIES(id_main_category),
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language),
  name text NOT NULL
);

CREATE TABLE CATEGORIES(
  id_category serial PRIMARY KEY,
  id_main_category integer REFERENCES MAIN_CATEGORIES(id_main_category)
);

CREATE TABLE CATEGORIES_NAME(
  id_category integer NOT NULL REFERENCES CATEGORIES(id_category),
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language),
  name text NOT NULL
);

CREATE TABLE UNITS(
  id_unit serial PRIMARY KEY,
  name varchar(16) NOT NULL
);

CREATE TABLE MENUS(
  id_menu serial PRIMARY KEY,
  id_branch integer NOT NULL REFERENCES BRANCH(id_branch),
  url_image text NOT NULL
);

CREATE TABLE MENU_ITEM(
  id_menu_item serial PRIMARY KEY,
  price integer NOT NULL,
  image_name text,
  amount decimal NOT NULL,
  id_unit integer REFERENCES UNITS(id_unit) NOT NULL,     
  id_category integer REFERENCES CATEGORIES(id_category) NOT NULL,
  id_menu integer REFERENCES MENUS(id_menu)
);

CREATE TABLE MENU_ITEM_NAME(
  id_menu_item integer NOT NULL REFERENCES MENU_ITEM(id_menu_item),
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language),
  name varchar(64) NOT NULL,
  description varchar(256)
);

CREATE TABLE ALLERGENS(
  id_allergen serial PRIMARY KEY
);

CREATE TABLE ALLERGENS_NAME(
  id_allergen integer NOT NULL REFERENCES ALLERGENS(id_allergen),
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language),
  allergen varchar(64) NOT NULL 
);

CREATE TABLE MENU_ITEM_ALLERGEN(
  id_menu_item integer REFERENCES MENU_ITEM(id_menu_item) NOT NULL,
  id_allergen integer REFERENCES ALLERGENS(id_allergen) NOT NULL
);

CREATE TABLE RESERVATION(
  id_branch integer NOT NULL REFERENCES BRANCH(id_branch),
  r_date date NOT NULL,
  time_from time NOT NULL,
  person varchar(128) NOT NULL,
  n_table integer NOT NULL,
  email text,
  status text
);

CREATE TABLE RESERVATION_CONFIG(
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language) NOT NULL,
  header varchar(128) NOT NULL,
  table_number varchar(64) NOT NULL,
  time_from_desc varchar(64) NOT NULL
);

CREATE TABLE ROLE(
  id_role serial PRIMARY KEY,
  name varchar(64)
);

CREATE TABLE EMPLOYEE(
  id_employee serial PRIMARY KEY,
  role integer REFERENCES ROLE(id_role) NOT NULL,
  id_branch integer REFERENCES BRANCH(id_branch) NOT NULL,
  name varchar(64) NOT NULL,
  surname varchar(64) NOT NULL,
  phone varchar(16),
  mail varchar(128)
);

CREATE TABLE ORDERS(
  id_order serial PRIMARY KEY,
  id_branch integer NOT NULL REFERENCES BRANCH(id_branch),
  o_date date NOT NULL,
  person varchar(128),
  address text NOT NULL,
  email text NOT NULL,
  status text
);

CREATE TABLE ORDER_ITEM(
  id_menu_item integer REFERENCES MENU_ITEM(id_menu_item) NOT NULL,
  id_order integer REFERENCES ORDERS(id_order) NOT NULL
);

CREATE TABLE NEWS(
  id_news serial PRIMARY KEY,
  id_restaurant integer NOT NULL REFERENCES RESTAURANT(id_restaurant),
  n_date date NOT NULL  
);

CREATE TABLE NEWS_NAME(
  id_news integer REFERENCES NEWS(id_news) NOT NULL,
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language) NOT NULL,
  header varchar(128) NOT NULL,
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

CREATE TABLE CONTACT_CONFIG(
  id_language integer NOT NULL REFERENCES LANGUAGES(id_language) NOT NULL,
  header varchar(128) NOT NULL,
  description text NOT NULL,
  url_image_map text,
  url_image_restaurant text
);