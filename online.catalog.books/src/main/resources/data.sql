INSERT INTO book(name,year_publication,publishing_house) VALUES
('Big suck','1919','Inda house'),
('Tusa-djusa','2012','Inda house'),
('Shop of pleasure','2003','Arot');

INSERT INTO author(first_name,last_name,birthday,sex) VALUES
('Artur','Morgan','18510211','FEMALE'),
('Datch','Van der linde','18460412','MALE'),
('Elena','Hlopushkina','19861223','FEMALE');

INSERT INTO users(username, password, role) VALUES
('super','$2y$04$IZTuX3DvwWuq9T6QUuKcY.nfMnrjAPPIp7EM08CDfki7msT1Biciu','USER'),
('puper','$2y$04$IZTuX3DvwWuq9T6QUuKcY.nfMnrjAPPIp7EM08CDfki7msT1Biciu','USER');

INSERT INTO book_author(book_id,author_id) VALUES
(1,2),
(1,1),
(1,3),
(2,3),
(3,3);

