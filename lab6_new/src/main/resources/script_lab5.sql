CREATE SCHEMA IF NOT EXISTS `kasaraba_lab5`;
USE `kasaraba_lab5` ;

DROP TABLE IF EXISTS `feedback`;
DROP TABLE IF EXISTS `version_update`;
DROP TABLE IF EXISTS `user_application`;
DROP TABLE IF EXISTS `application`;
DROP TABLE IF EXISTS `single_developer`;
DROP TABLE IF EXISTS `organization`;
DROP TABLE IF EXISTS `author`;
DROP TABLE IF EXISTS `virtual_wallet`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `secured`;


CREATE TABLE `author` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
)ENGINE = InnoDB;

CREATE TABLE `application` (
    `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `author_id` INT NOT NULL,
  `price_in_dol` INT NOT NULL,
  `release_year` INT NOT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE = InnoDB;

CREATE TABLE `single_developer` (
	`id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `author_id` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;


CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `middle_name` VARCHAR(25) NULL,
  `nationality` VARCHAR(20) NULL,
  `email` VARCHAR(45) NULL,
  `year_of_registration` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `feedback` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `application_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `stars_given` INT NOT NULL,
  `text_feedback` MEDIUMTEXT NULL,
  `would_recommend` TINYINT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;


CREATE TABLE `organization` (
	`id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `author_id` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `secured` (
  `card_number` INT NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`card_number`)
) ENGINE = InnoDB;

CREATE TABLE `virtual_wallet` (
    `id` INT NOT NULL AUTO_INCREMENT,
  `secured_card_number` INT NOT NULL,
  `balance_in_dol` INT NOT NULL,
  `subscription_plan` VARCHAR(45) NULL,
  `pay_automaticly` TINYINT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `version_update` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `update_day` DATE,
  `things_updated` VARCHAR(300) NOT NULL,
  `application_id` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `user_application` (
  `user_id` INT NOT NULL,
  `application_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `application_id`)
) ENGINE = InnoDB;

CREATE INDEX User_Name_Surname
	ON user (first_name, last_name);

ALTER TABLE `virtual_wallet`
	ADD CONSTRAINT `fk_virtual_wallet_secutiry1`
		FOREIGN KEY (`secured_card_number`)
			REFERENCES `kasaraba_lab5`.`secured` (`card_number`),

	ADD CONSTRAINT `fk_virtual_wallet_user1`
		FOREIGN KEY (`user_id`)
			REFERENCES `kasaraba_lab5`.`user` (`id`);


ALTER TABLE `application`
    ADD CONSTRAINT `fk_application_author1`
        FOREIGN KEY (`author_id`)
            REFERENCES `kasaraba_lab5`.`author` (`id`);

ALTER TABLE `single_developer`
    ADD CONSTRAINT `fk_person_author1`
        FOREIGN KEY (`author_id`)
            REFERENCES `kasaraba_lab5`.`author` (`id`);

ALTER TABLE `feedback`
    ADD CONSTRAINT `fk_feedback_application1`
        FOREIGN KEY (`application_id`)
            REFERENCES `kasaraba_lab5`.`application` (`id`),

    ADD CONSTRAINT `fk_feedback_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `kasaraba_lab5`.`user` (`id`);

ALTER TABLE `organization`
    ADD CONSTRAINT `fk_organization_author1`
        FOREIGN KEY (`author_id`)
            REFERENCES `kasaraba_lab5`.`author` (`id`);

ALTER TABLE `version_update`
    ADD CONSTRAINT `fk_version_update_application1`
        FOREIGN KEY (`application_id`)
            REFERENCES `kasaraba_lab5`.`application` (`id`);

ALTER TABLE `user_application`
    ADD CONSTRAINT `fk_user_application_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `kasaraba_lab5`.`user` (`id`),

    ADD CONSTRAINT `fk_user_application_application1`
        FOREIGN KEY (`application_id`)
            REFERENCES `kasaraba_lab5`.`application` (`id`);

INSERT INTO `secured`(card_number, password) VALUES
	(123456789, 'qwerty'),
    (987654321, 'password'),
    (345678912, 'volodya'),
    (765432198, 'private'),
    (456789123, 'iot3475'),
    (654321987, 'omygod'),
    (918273645, 'lolkeklol'),
    (564738291, 'kakakaka'),
    (234567891, 'kasaraba'),
    (291038475, 'donaldtrump');

INSERT INTO `user`(first_name, last_name, middle_name, nationality, email, year_of_registration) VALUES
	('Volodymyr', 'Kasaraba', 'Volodymyrovych', 'Ukrainian', 'kasaraba@lpnu.ua', 2010),
    ('Andriy', 'Peleno', 'Olegivych', 'Indian', NULL, 2012),
    ('Taras', 'Leshchy', NULL, 'Ukrainian', 'leshchyshyn@lpnu.ua', 2018),
    ('Sofiia', 'Didula', NULL, 'Ukrainian', 'didula@lpnu.ua', 2008),
    ('Max', 'Zvarych', 'Fedorovych', 'Polish', NULL, 2016),
    ('Olena', 'Vovk', 'Volodymyrivna', 'Ukrainian', 'vovk@gmail.com', 1999),
    ('Andriy', 'Krech', NULL, 'French', NULL, 2011),
    ('Levy', 'Baker', NULL, 'American', 'levy2003@gmail.com', 2008),
    ('Mariia', 'Didula', NULL, 'Ukrainian', 'didula_mariia@lpnu.ua', 2009),
    ('Mike', 'Zend', 'Jr', 'British', NULL, 2016);


INSERT INTO `virtual_wallet`(secured_card_number, balance_in_dol, subscription_plan, pay_automaticly, user_id) VALUES
	(123456789, 10, 'monthly', 1, 1),
    (987654321, 0, NULL, 0, 2),
    (345678912, 96, 'monthly', 1, 3),
    (765432198, 34, 'yearly', 1, 4),
    (456789123, 0, NULL, 0, 5),
    (654321987, 46, 'yearly', 1, 6),
    (918273645, 15, 'monthly', 1, 7),
    (564738291, 35, 'yearly', 1, 8),
    (234567891, 29, 'monthly', 0, 9),
    (291038475, 1, NULL, 0, 10);

INSERT INTO `author`(id) VALUES
	(1), (2), (3), (4), (5), (6), (7), (8), (9), (10),
    (11), (12), (13), (14), (15), (16), (17), (18), (19), (20);

INSERT INTO `organization`(name, author_id) VALUES
	('Hyperlink InfoSystem' ,1),
    ('Cubix', 2),
    ('Yudiz Solutions', 3),
    ('Fgfactory', 4),
    ('Juego Studios', 5),
    ('Algoworks', 6),
    ('Tintash', 7),
    ('Indus Net', 8),
    ('ChopDawg.com', 9),
    ('CitrusBits', 10);

INSERT INTO `single_developer`(first_name, last_name, author_id) VALUES
	('Keon',  'Joseph' , 11),
    ('Ayrton',  ' William', 12),
    ('Samira',  ' Albert', 13),
    ('Ela',  ' Dawson', 14),
    ('Ria',  ' Neale', 15),
    ('Margaret',  ' Kirkland', 16),
    ('Suleman',  ' Bravo', 17),
    ('Nathanial',  ' Keenan', 18),
    ('Pixie',  ' Monroe', 19),
    ('Anabelle',  ' Byers', 20);

INSERT INTO `application`(`id`, `name`, `author_id`, `price_in_dol`, `release_year`)
	VALUES
	(1, 'Camera ZOOM FX Premium', 1, 2, 2003),
    (2, 'Beautiful Widgets Pro', 3, 2, 2009),
    (3, 'Cut the Rope GOLD', 5, 1, 2010),
    (4, 'Where is My Water?', 7, 1, 2011),
    (5, 'Fruit Ninja Classic', 9, 4, 2003),
    (6, 'Draw Something', 11, 0, 2019),
    (7, 'Grand Theft Auto III', 13, 1, 2018),
    (8, 'Smart Tools', 15, 0, 2009),
    (9, 'TuneIn Radio Pro', 17, 1, 2007),
    (10, 'HD Widgets', 19, 0, 2003);

INSERT INTO `user_application`(user_id, application_id) VALUES
	(1, 1),
    (2, 1),
    (3, 3),
    (3, 4),
	(5, 7),
    (6, 9),
    (7, 5),
    (8, 8),
    (9, 10),
    (10, 6);

INSERT INTO `version_update`(update_day, things_updated, application_id) VALUES
	('2003-02-13 00:00:00.000', 'Fixed bugs and other minor improvements', 1),
    ('2004-12-18 00:00:00.000', 'Added abbility to create a profile', 2),
    ('2005-04-19 00:00:00.000', 'Fixed bugs and other minor improvements', 3),
    ('2006-05-23 00:00:00.000', 'Added abbility to create a profile', 4),
    ('2010-02-20 00:00:00.000', 'Fixed bugs and other minor improvements', 5),
    ('2011-09-03 00:00:00.000', 'Added abbility to create a profile', 6),
    ('2013-10-01 00:00:00.000', 'Fixed bugs and other minor improvements', 7),
    ('2014-11-07 00:00:00.000', 'Added abbility to create a profile', 8),
    ('2016-10-15 00:00:00.000', 'Added new radio stations', 9),
    ('2017-12-30 00:00:00.000', 'Fixed bugs and other minor improvements', 10);

INSERT INTO `feedback`(user_id, application_id, stars_given, text_feedback, would_recommend) VALUES
	(3, 1, 4,  'Good enough', 1),
    (3,  3, 0, 'Waste of time', 0),
    (1,  9, 5, 'Nice game!', 1),
    (4,  9, 4, NULL, 1),
	(10, 5, 5, 'Playing with my grandma all day!', 1),
    (2,  7, 2, 'average game', 0),
    (5,  9, 4, NULL, 1),
    (6,  10, 4, 'Deserves 5, but my gf broke up with me, so 4', 1),
    (7,  10, 3, NULL, 1),
    (8,  10, 5, 'convenient', 1);
