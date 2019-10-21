INSERT INTO role (role_id, role) VALUES (1,'ADMIN');
INSERT INTO role (role_id, role) VALUES (2,'SELLER');
INSERT INTO role (role_id, role) VALUES (3,'BUYER');

INSERT INTO CATEGORY(NAME) VALUES('Phones');
INSERT INTO CATEGORY(NAME) VALUES('Laptops');
INSERT INTO CATEGORY(NAME) VALUES('TV & video');
INSERT INTO CATEGORY(NAME) VALUES('Audio');

INSERT INTO PRODUCT(TITLE, DESCRIPTION, PRICE, DISCOUNT, IMAGE_URL, AVAILABLE_COUNT, CATEGORY_ID)
VALUES(
'Apple iPhone 11 Pro Max (64GB) - Space Gray',
'Carrier - This phone is locked to simple Mobile from Tracfone, which means this Device can only be used on the Simple Mobile wireless network.',
1099,
10,
'https://images-na.ssl-images-amazon.com/images/I/81bsgUsPM-L._SL1500_.jpg',
3,
1
);
INSERT INTO PRODUCT(TITLE, DESCRIPTION, PRICE, DISCOUNT, IMAGE_URL, AVAILABLE_COUNT, CATEGORY_ID)
VALUES(
'Microsoft Surface Laptop (1st Gen) DAJ-00001 Laptop (Windows 10 S, Intel Core i7, 13.5" LED-Lit Screen, Storage: 256 GB, RAM: 8 GB) Platinum',
'Microsoft Surface Laptop features a 7th generation Intel Core i7 processor, 256 GB of storage, 8 GB RAM, and up to 14.5 hours of video playback',
780,
0,
'https://images-na.ssl-images-amazon.com/images/I/61EJz6KKOKL._SL1200_.jpg',
3,
1
);
INSERT INTO PRODUCT(TITLE, DESCRIPTION, PRICE, DISCOUNT, IMAGE_URL, AVAILABLE_COUNT, CATEGORY_ID)
VALUES(
'Apple MacBook Air MJVE2LL/A 13-inch Laptop 1.6GHz Core i5,4GB RAM,128GB SSD (Renewed)',
'1.6 GHz dual-core Intel Core i5 (Turbo Boost up to 2.7 GHz) with 3 MB shared L3 cache
13.3-Inch (diagonal) LED-backlit Glossy Widescreen Display, 1440 x 900 resolution
Intel HD Graphics 6000
OS X Yosemite, Up to 12 Hours of Battery Life',
489,
5,
'https://images-na.ssl-images-amazon.com/images/I/218lVbfUbxL.jpg',
8,
2
);
