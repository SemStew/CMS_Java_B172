--languages
INSERT INTO LANGUAGES(name) VALUES ('English');
INSERT INTO LANGUAGES(name) VALUES ('Czech');

--categories
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;
INSERT INTO CATEGORIES DEFAULT VALUES;

--categories in english
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (1, 1, 'Starters');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (2, 1, 'Soups');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (3, 1, 'Salads');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (4, 1, 'Traditional cousine');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (5, 1, 'Pasta');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (6, 1, 'Specialities');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (7, 1, 'Vegetarian food');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (8, 1, 'For kids');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (9, 1, 'Desserts');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (10, 1, 'Side dishes');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (11, 1, 'Sauces');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (12, 1, 'Alcohol free');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (13, 1, 'Warm beverages');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (14, 1, 'Apperitives');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (15, 1, 'Wine');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (16, 1, 'Liquers');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (17, 1, 'Destialtes');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (18, 1, 'Drinks');

--categories in czech
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (1, 2, 'Pøedkrmy');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (2, 2, 'Polévky');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (3, 2, 'Saláty');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (4, 2, 'Tradièní kuchynì');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (5, 2, 'Tìstoviny');
--INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (6, 2, 'Speciality');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (7, 2, 'Bezmasá jídla');
--INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (8, 2, 'Pro dìti');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (9, 2, 'Dezerty');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (10, 2, 'Pøílohy');
--INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (11, 2, 'Omáèky');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (12, 2, 'Nealkoholické nápoje');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (13, 2, 'Teplé nápoje');
INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (14, 2, 'Aperitivy');
--INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (15, 2, 'Víno');
--INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (16, 2, 'Likéry');
--INSERT INTO CATEGORIES_NAME(id_category, id_language, description) VALUES (17, 2, 'Destiláty');

--units
INSERT INTO UNITS(description) VALUES ('g');
INSERT INTO UNITS(description) VALUES ('pcs');
INSERT INTO UNITS(description) VALUES ('ks');
INSERT INTO UNITS(description) VALUES ('l');
INSERT INTO UNITS(description) VALUES ('cl');

--food
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (60, 'http://pivarenbudik.sk/wp-content/uploads/2013/04/utopenec.jpg', 2, 2, 1);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (75, 'https://www.receptyonline.cz/wp-content/uploads/2016/11/185503topinky-pikantni-kureci-smes-indruchova.jpg', 2, 2, 1);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (25, 'http://cdn.milujivareni.cz/image/eyJ0eXBlIjoicmVjaXBlIiwiaW1hZ2VJZCI6IjEwNjciLCJ0aHVtYiI6MH0=/kureci-vyvar-s-masem-a-nudlemi-.jpg?ver=11', 0.25, 4, 2);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (25, 'https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/beef_goulash_soup_gulyas_16159_16x9.jpg', 0.25, 4, 2);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (120, 'http://bitesofbri.com/wp-content/uploads/2014/04/Chicken-Bruschetta-Caesar-Salad-2-Bites-of-Bri.jpg', 125, 1, 3);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (115, 'http://balkansut.com.tr/wp-content/uploads/2016/09/salata.jpg', 125, 1, 3);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (130, 'https://thumbs.dreamstime.com/z/homemade-hot-czech-goulash-knodel-close-up-horizontal-top-plate-view-above-75573944.jpg', 200, 1, 4);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (150, 'https://realfood.tesco.com/media/images/Sorted-roast-beef-LGH-6d1b1286-a7d3-4096-bff7-20d840716df3-0-1400x919.jpg', 200, 1, 4);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (120, 'https://www.warrennash.co.uk/recipes/carbonara/spaghetticarbonara_xl.jpg', 200, 1, 5);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (135, 'http://media.igurmet.cz/yummy/dc/e9/dce94f79b5912872d1f769debba02180.jpg', 250, 1, 5);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (115, 'http://www.praguemorning.cz/img/2017/10/schermata-2017-10-20-alle-152713_960x540.jpg', 200, 1, 7);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (100, 'https://fthmb.tqn.com/w5CXk2yMylJwBHxvrv13n1Y7Tqw=/960x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/scrambled-eggs-58a701ac5f9b58a3c91cbebd.jpg', 200, 1, 7);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (90, 'https://assets.epicurious.com/photos/5761d0268accf290434553aa/6:4/w_620%2Ch_413/panna-cotta.jpg', 100, 1, 9);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (110, 'http://www.rawforyou.cz/wp-content/uploads/2014/09/61a0305_upr_1.jpg', 100, 1, 9);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (30, 'https://airfryer.cooking/wp-content/uploads/2017/12/Shoestring_fries_large.jpg', 50, 1, 10);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (30, 'https://previews.123rf.com/images/weyo/weyo1611/weyo161100125/65592367-potato-roasted-potatoes-american-potatoes-with-salt-pepper-and-cumin-roasted-potato-wedges-delicious.jpg', 50, 1, 10);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (45, 'http://www.howtomakeorangejuice.com/files/photos/Orange_Juice_Recipes_Copyright_2012.jpg', 0.33, 4, 12);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (45, 'https://www.organicfacts.net/wp-content/uploads/applejuice.jpg', 0.33, 4, 12);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (30, 'http://www.healthywomen.org/sites/default/files/green-tea.jpg', 0.5, 4, 13);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (75, 'https://img.mediacentrum.sk/gallery/nwo/maxwidth/650/775529.jpg', 2, 5, 14);
INSERT INTO FOOD(price, image_name, amount, id_unit, id_category) VALUES (85, 'https://cdn.shopify.com/s/files/1/0343/7389/products/20170303_JBH_0033_2048x.jpg?v=1519243463', 2, 5, 14);

--food in english