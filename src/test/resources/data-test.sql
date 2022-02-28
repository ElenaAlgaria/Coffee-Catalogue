INSERT INTO ROASTING (id, name)
VALUES (1, 'Test roasting 1');
INSERT INTO ROASTING (id, name)
VALUES (2, 'Test roasting 2');
INSERT INTO ROASTING (id, name)
VALUES (3, 'Test roasting 3');

INSERT INTO ROASTER (id, name, image_path, description, url)
VALUES (1, 'Test roaster 1', '/images/coffee/blasercafeag.jpg',
        'Test description', 'https://www.blasertrading.ch');
INSERT INTO ROASTER (id, name, image_path, description, url)
VALUES (2, 'Test roaster 2', '/images/coffee/blasercafeag.jpg',
        'Test description', 'https://www.blasertrading.ch');
INSERT INTO ROASTER (id, name, image_path, description, url)
VALUES (3, 'Test roaster 3', '/images/coffee/blasercafeag.jpg',
        'Test description', 'https://www.blasertrading.ch');

INSERT INTO BEAN (id, name, image_path, description, favorite, roaster_id)
VALUES (1, 'Test bean 1',
        'https://www.blasercafe.ch/Blasercafe_lilla_e_rose_250g.png?itok=uXhX-Z4E',
        'Test description 1', false, 1);
INSERT INTO BEAN (id, name, image_path, description, favorite, roaster_id)
VALUES (2, 'Test bean 2',
        'https://www.blasercafe.ch/sites/default/files/styles/product_slideshow/public/product_images/Blasercafe_lilla_e_rose_250g.png?itok=uXhX-Z4E',
        'Test description 2', true, 2);
INSERT INTO BEAN (id, name, image_path, description, favorite, roaster_id)
VALUES (3, 'Test bean',
        'https://www.blasercafe.ch/sites/default/files/styles/product_slideshow/public/product_images/Blasercafe_lilla_e_rose_250g.png?itok=uXhX-Z4E',
        'Test description 3', true, 3);

INSERT INTO BEAN_ROASTINGS (bean_id, roastings_id)
VALUES (1, 1);
INSERT INTO BEAN_ROASTINGS (bean_id, roastings_id)
VALUES (1, 2);
INSERT INTO BEAN_ROASTINGS (bean_id, roastings_id)
VALUES (2, 3);
INSERT INTO BEAN_ROASTINGS (bean_id, roastings_id)
VALUES (3, 2);