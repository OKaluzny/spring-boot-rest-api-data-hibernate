CREATE TABLE IF NOT EXISTS `authors` (
  `author_id`         INT(11) NOT NULL AUTO_INCREMENT,
  `author_birth_date` DATETIME         DEFAULT NULL,
  `author_first_name` VARCHAR(255)     DEFAULT NULL,
  `author_last_name`  VARCHAR(255)     DEFAULT NULL,
  `author_sex`        VARCHAR(255)     DEFAULT NULL,
  PRIMARY KEY (`author_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `books` (
  `book_id`    INT(11) NOT NULL AUTO_INCREMENT,
  `book_genre` VARCHAR(255)     DEFAULT NULL,
  `book_isbn`  VARCHAR(255)     DEFAULT NULL,
  `book_title` VARCHAR(30)      DEFAULT NULL,
  PRIMARY KEY (`book_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `rewards` (
  `reward_id`    INT(11) NOT NULL AUTO_INCREMENT,
  `reward_title` VARCHAR(50)      DEFAULT NULL,
  `reward_year`  INT(11)          DEFAULT NULL,
  PRIMARY KEY (`reward_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `author_book` (
  `author_fk` INT(11) NOT NULL,
  `book_fk`   INT(11) NOT NULL,
  PRIMARY KEY (`author_fk`, `book_fk`),
  FOREIGN KEY (`author_fk`) REFERENCES `authors` (`author_id`),
  FOREIGN KEY (`book_fk`) REFERENCES `books` (`book_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `authors_rewards` (
  `author_author_id`  INT(11) NOT NULL,
  `rewards_reward_id` INT(11) NOT NULL,
  PRIMARY KEY (`author_author_id`, `rewards_reward_id`),
  FOREIGN KEY (`author_author_id`) REFERENCES `authors` (`author_id`),
  FOREIGN KEY (`rewards_reward_id`) REFERENCES `rewards` (`reward_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
