INSERT IGNORE INTO ROASTING (id, name) VALUES (1, 'Espresso');
INSERT IGNORE INTO ROASTING (id, name) VALUES (2, 'Latte Macchiato');
INSERT IGNORE INTO ROASTING (id, name) VALUES (3, 'Cappuccino');
INSERT IGNORE INTO ROASTING (id, name) VALUES (4, 'Ristretto');
INSERT IGNORE INTO ROASTING (id, name) VALUES (5, 'Americano');
INSERT IGNORE INTO ROASTING (id, name) VALUES (6, 'Filter');

INSERT IGNORE INTO ROASTER (id, name, image_path, description, url) VALUES (1, 'Blaser', '/images/coffee/blasercafeag.jpg', 'We have a long tradition of love for coffee. ' ||
'Since it was founded in 1922, Blasercafé has been fully family-owned and is now in the 4th generation. Blasercafé is ' ||
'therefore the only coffee roastery in the Bern region that has retained its independence over the years. For us, Bern ' ||
'means home. Both the family''s center of life and the Blasercafé are located in Bern. The raw coffee imported from the ' ||
'tropical and subtropical regions is roasted exclusively in Bern.','https://www.blasertrading.ch');
INSERT IGNORE INTO ROASTER (id, name, image_path, description, url) VALUES (2, 'Vertical', '/images/coffee/vertical.jpg', 'The focus of our roastery is on working' ||
' only with Specialty Coffee: That means top cup quality and clear aroma profiles, partnerships at eye level as well as socially,' ||
' ecologically and economically sustainable practices and transparency along the value chain.In order to highlight the' ||
' great work done at origin, we roast these exceptional coffees in our signature clean and light roasting style.','https://www.vertical.coffee');
INSERT IGNORE INTO ROASTER (id, name, image_path, description, url) VALUES (3, 'Koppi', '/images/coffee/koppi.jfif', 'It was neither Stockholm nor Gothenburg that' ||
' became Sweden''s first coffee Mecca, but Helsingborg. It was here that Swedish brewista Anne Lunell and Charles Nystrand ' ||
'founded the roastery and café Koppe thirteen years ago. During trips to Honduras, Costa Rica, Colombia, Guatemala and Kenya, ' ||
'they chose their own selections and started at home - to make their origin-designated quality coffee. such a late-night ' ||
'experience that it attracted coffee lovers from all over the world. Over the years, more and more emphasis has been placed ' ||
'on the roasting itself, and anyone who wants to visit Koppi''s own premises can make an appointment. It''s totally worth it.' ||
'','https://www.koppi.se');
INSERT IGNORE INTO ROASTER (id, name, image_path, description, url) VALUES (999, 'other - please note the roaster in the description','no image','no description','https://www.noroaster.ch');

INSERT IGNORE INTO BEAN (id, name, image_path, description, favorite, roaster_id) VALUES (1, 'LILLA E ROSE',
'https://www.blasercafe.ch/sites/default/files/styles/product_slideshow/public/product_images/Blasercafe_lilla_e_rose_250g.png?itok=uXhX-Z4E',
'The name “lilac and roses” perfectly describes the well-balanced aroma in the cup. Intense flowery, fruity and spicy ' ||
'aromas give this mixture, together with the medium roast level, an extremely varied flavor.
The Lilla e Rose is particularly popular as a café crème.', true, 1);
INSERT IGNORE INTO BEAN (id, name, image_path, description, favorite, roaster_id) VALUES (2, 'RED CLAY',
'https://images.squarespace-cdn.com/content/v1/58975fd26a4963593a5ee7eb/1625508355532-ER9LGCVN62U5H9597XD1/redclay.png?format=2500w',
'Red Clay is an espresso with rich mouth feel, notes of chocolate, nougat and caramel with a soft acidity. Matches perfectly with milk.', false, 3);
INSERT IGNORE INTO BEAN (id, name, image_path, description, favorite, roaster_id) VALUES (3, 'BOJI',
'https://images.squarespace-cdn.com/content/v1/583af2a1725e257a97d87ad6/1626433826479-QHQNWKRG9G880ODQKHFH/DSC_4338.jpg?format=750w',
'We''re sticking with the strawberry flavor profile with this fresh natural Ethiopian. It''s rounded out by some raspberry,' ||
' vanilla and caramel notes. Fantastic with milk (and milk alternatives) but also very tasty as a neat espresso or in the moka pot.', false, 2);
INSERT IGNORE INTO BEAN (id, name, image_path, description, favorite, roaster_id) VALUES (4, 'DHILGEE',
'https://images.squarespace-cdn.com/content/v1/58975fd26a4963593a5ee7eb/1633098543407-HMIUYLHA7GIXMKLJ5KG4/dhilgeechelbesaORGANIC.png?format=2500w',
'Flavour notes: Lychee, pink grapefruit, perfume-like', false, 3);
INSERT IGNORE INTO BEAN (id, name, image_path, description, favorite, roaster_id) VALUES (5, 'DIAMOND',
'https://images.squarespace-cdn.com/content/v1/583af2a1725e257a97d87ad6/1588231031526-6H0SBWMAWD63AB7U2VVE/DSC_1536.jpg?format=750w',
'Our Brazil Diamond is available year-round and a great standard coffee with the same cup profile season upon season.' ||
' Grown at an altitude of 1100 masl in the Minas Gerais region, it shows mild and classic flavours of milk chocolate and' ||
' hazelnut - what you might expect from a Brazilian coffee. ', false, 2);
INSERT IGNORE INTO BEAN (id, name, image_path, description, favorite, roaster_id) VALUES (6, 'DIIMA',
'https://images.squarespace-cdn.com/content/v1/58975fd26a4963593a5ee7eb/1634920373529-0EB66A7SPIL35Z8N1RL0/bombediimaNATURAL.png?format=2500w',
' Natural - cherries dried whole in the sun of Ethiopia.' ||
'Flavour notes: Berry Jam, Pineapple, Candy', false, 3);
INSERT IGNORE INTO BEAN (id, name, image_path, description, favorite, roaster_id) VALUES (7, 'FINCA EL PORVENIR',
'https://images.squarespace-cdn.com/content/v1/58975fd26a4963593a5ee7eb/1629055275078-16055MKE91Y8J9S1VKLQ/fincaelporvenirCATURRA.png?format=2500w',
'The coffee is organically grown with no chemical fertilisers or pesticides.' ||
'The coffee is picked and sorted by hand and it takes about a month to pick the whole farm and then they start over again.' ||
' Flavour notes: Dark Chocolate, Caramel, Berries', false, 3);
INSERT IGNORE INTO BEAN (id, name, image_path, description, favorite, roaster_id) VALUES (8, 'FINCA LA FUENTE',
'https://images.squarespace-cdn.com/content/v1/58975fd26a4963593a5ee7eb/1629055698200-B913K8YKFYISC0WH2XUY/fincalafuenteVC.png?format=2500w',
'This is a truly special coffee showcasing the very best of Colombia.' ||
'Due to the high elevation, the cooler average temperature allows a slow ripening that builds up lots of sugars and' ||
' complex flavors in the coffee cherries. The cherries are bursting with flavor of papaya, lychee and melon. ' ||
' Flavour notes: red berries, fruity, elegant ', false, 3);
INSERT IGNORE INTO BEAN (id, name, image_path, description, favorite, roaster_id) VALUES (9, 'LA UNION',
'https://images.squarespace-cdn.com/content/v1/583af2a1725e257a97d87ad6/1631365652154-HTESWGK1J0GMCPL4KPGQ/DSC_4398.jpg?format=750w',
'Colombia is back as our washed Central American espresso which bridges the gap from our more basic  to the complex' ||
' espresso roasts. Expect a well balanced coffee with dominant notes of caramel and hints of fruitiness through its prune' ||
' flavors. Great as an everyday espresso staple as well as a special treat!', false, 2);
INSERT IGNORE INTO BEAN (id, name, image_path, description, favorite, roaster_id) VALUES (10, 'ADADO',
'https://images.squarespace-cdn.com/content/v1/583af2a1725e257a97d87ad6/1631366340033-ZIRYXRUB2HH9XS41689C/DSC_4362.jpg?format=750w',
'As always, we are sticking to the classic and elegant Yirgacheffe flavor profile: black tea, apricot and bergamot are ' ||
'the typical aromas to look for in this floral filter coffee.
Lovely choice for a special occasion as well as your everyday morning coffee!', false, 2);
INSERT IGNORE INTO BEAN (id, name, image_path, description, favorite, roaster_id) VALUES (11, 'TERROIR BRA',
'https://www.blasercafe.ch/sites/default/files/styles/product_teaser/public/product_images/Blasercafe_terroir_brasil_2.png?itok=SZereTz-',
'Around 60 percent of the property of the Capoeirinha Farm is used for growing coffee. The remaining 40 percent of the' ||
' land has been declared a nature reserve by the owners and includes rainforest areas and reserves. The farm workers' ||
' benefit from good employment conditions such as health insurance, free transportation and accommodation.', false, 1);
INSERT IGNORE INTO BEAN (id, name, image_path, description, favorite, roaster_id) VALUES (12, 'TERROIR COL',
'https://www.blasercafe.ch/sites/default/files/styles/product_teaser/public/product_images/Blasercafe_terroir_maytama_washed_neu.png?itok=4kt9nC2m',
'Die Familie von Patricia Builes führt die Maytama Farm seit mehr als 45 Jahren. Mit leidenschaftlicher Hingabe sowie' ||
' innovativen Methoden werden die Kaffeepflanzen schonend und nachhaltig bewirtschaftet. Der Name Maytama steht in der ' ||
'Kultur der Quimbaya, den Ureinwohnern von Kolumbien, für die Herrin des Landes der Göttinnen. So wird auch das Thema' ||
' Gleichstellung auf der Farm grossgeschrieben. Neben der Besitzerin Patricia haben mehrere Frauen führende Funktionen.' ||
' Bis in den hintersten Winkel der Farm erlebt man die familiäre Atmosphäre, die von der positiven Ausstrahlung und ' ||
'Herzlichkeit der Besitzerin geprägt ist.', false, 1);

INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (1, 1);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (1, 3);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (1, 6);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (11, 3);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (11, 2);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (11, 1);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (12, 4);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (12, 6);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (2, 1);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (4, 6);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (6, 6);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (7, 1);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (8, 6);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (5, 1);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (5, 2);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (5, 3);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (3, 1);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (3, 2);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (3, 3);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (9, 1);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (9, 2);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (9, 3);
INSERT IGNORE INTO BEAN_ROASTINGS (bean_id, roastings_id) VALUES (10, 6);