CREATE SCHEMA IF NOT EXISTS `totalizator` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `totalizator`.`bet` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `sum_in` DECIMAL(10,2) NOT NULL,
  `sum_out` DECIMAL(10,2) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `type` VARCHAR(8) NOT NULL,
  `result` VARCHAR(10) NULL DEFAULT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `client_id`
  FOREIGN KEY (`id`)
  REFERENCES `totalizator`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`bet_outcome` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_bet` INT(11) NULL DEFAULT NULL,
  `id_outcome` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_bet_idx` (`id_bet` ASC),
  INDEX `id_outcome_idx` (`id_outcome` ASC),
  CONSTRAINT `id_bet`
  FOREIGN KEY (`id_bet`)
  REFERENCES `totalizator`.`bet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_outcome`
  FOREIGN KEY (`id_outcome`)
  REFERENCES `totalizator`.`outcome` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`client` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `max_bet` DECIMAL(10,2) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `balance` DECIMAL(10,2) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_frk`
  FOREIGN KEY (`id`)
  REFERENCES `totalizator`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`event` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `open_date_and_time` DATETIME NULL DEFAULT NULL,
  `start_date_and_time` DATETIME NULL DEFAULT NULL,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `tournament` VARCHAR(100) NULL DEFAULT NULL,
  `status` VARCHAR(11) NULL DEFAULT NULL,
  `score` VARCHAR(45) NULL DEFAULT NULL,
  `margin` DOUBLE UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `max_win` INT(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `is_suspended` TINYINT(3) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `id_bookmeker` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `author_frk_idx` (`id_bookmeker` ASC),
  CONSTRAINT `author_frk`
  FOREIGN KEY (`id_bookmeker`)
  REFERENCES `totalizator`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`market` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `event_frk`
  FOREIGN KEY (`id`)
  REFERENCES `totalizator`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`outcome` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(8) NOT NULL,
  `coefficient` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `market_frk_idx` (`id` ASC),
  CONSTRAINT `market_frk`
  FOREIGN KEY (`id`)
  REFERENCES `totalizator`.`market` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`participant` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `age` INT(11) NULL DEFAULT NULL,
  `weight` INT(11) NULL DEFAULT NULL,
  `trainer` VARCHAR(50) NULL DEFAULT NULL,
  `jockey` VARCHAR(50) NULL DEFAULT NULL,
  `id_event` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `participant_id_uindex` (`id` ASC),
  UNIQUE INDEX `participant_name_uindex` (`name` ASC),
  INDEX `event_idx` (`id_event` ASC),
  CONSTRAINT `event`
  FOREIGN KEY (`id_event`)
  REFERENCES `totalizator`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `date_of_registration` DATE NULL DEFAULT NULL,
  `user_role` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARACTER SET = utf8;