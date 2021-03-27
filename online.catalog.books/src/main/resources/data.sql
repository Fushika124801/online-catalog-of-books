INSERT INTO book (name,year_publication,publishing_house) VALUES
('Big suck','19190812','Inda house'),
('Tusa djusa','20121106','Inda house'),
('Shop of pleasure','20030126','Arot');

INSERT INTO author (first_name,last_name,birthday,sex) VALUES
('Artur','Morgan','18510211','MALE'),
('Datch','Van der linde','18460324','MALE'),
('Elena','Hlopushkina','19861223','FEMALE');

INSERT INTO book_author (book_id,author_id) VALUES
(1,2),
(1,1),
(2,3),
(3,3);