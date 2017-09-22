CREATE SCHEMA `totalizator`
  DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`user` (
  `id`                   INT         NOT NULL AUTO_INCREMENT,
  `first_name`           VARCHAR(45) NULL,
  `last_name`            VARCHAR(45) NULL,
  `email`                VARCHAR(45) NULL,
  `login`                VARCHAR(20) NOT NULL,
  `password`             VARCHAR(20) NOT NULL,
  `date_of_registration` DATE        NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);

CREATE TABLE IF NOT EXISTS `totalizator`.`employee` (
  `id`   INT         NOT NULL,
  `role` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_id`
  FOREIGN KEY (`id`)
  REFERENCES `totalizator`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `totalizator`.`client` (
  `id`          INT                     NOT NULL AUTO_INCREMENT,
  `max_bet`     DOUBLE ZEROFILL         NULL,
  `balance`     DECIMAL(10, 2) ZEROFILL NULL,
  `description` VARCHAR(200)            NULL,
  `id_user`     INT                     NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_id`
  FOREIGN KEY (`id`)
  REFERENCES `totalizator`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `totalizator`.`bet` (
  `id`          INT                     NOT NULL AUTO_INCREMENT,
  `id_client`   INT                     NOT NULL,
  `sum_in`      DECIMAL(10, 2)          NOT NULL,
  `sum_out`     DECIMAL(10, 2) ZEROFILL NULL,
  `type`        VARCHAR(8)              NOT NULL,
  `result`      VARCHAR(10)             NULL,
  `description` VARCHAR(100)            NULL,
  PRIMARY KEY (`id`),
  INDEX `id_client_idx` (`id_client` ASC),
  CONSTRAINT `id_client`
  FOREIGN KEY (`id_client`)
  REFERENCES `totalizator`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `totalizator`.`participant`
(
  `id`      INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name`    VARCHAR(50)     NOT NULL,
  `age`     INT,
  `weight`  INT,
  `trainer` VARCHAR(50),
  `jockey`  VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS `totalizator`.`event` (
  `id`                  INT PRIMARY KEY       NOT NULL AUTO_INCREMENT,
  `open_date_and_time`  DATETIME              NULL,
  `start_date_and_time` DATETIME              NULL,
  `country`             VARCHAR(45)           NULL,
  `tournament`          VARCHAR(100)          NULL,
  `status`              VARCHAR(11)           NULL,
  `score`               VARCHAR(45)           NULL,
  `margin`              DOUBLE ZEROFILL       NULL,
  `max_win`             INT ZEROFILL          NULL,
  `is_suspended`        TINYINT ZEROFILL      NULL,
  `id_bookmeker`        INT                   NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `id_bookmeker_idx` (`id_bookmeker` ASC),
  CONSTRAINT `id_bookmeker`
  FOREIGN KEY (`id_bookmeker`)
  REFERENCES `totalizator`.`employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `totalizator`.`outcome` (
  `id`          INT        NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(8) NOT NULL,
  `coefficient` DOUBLE     NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `totalizator`.`market_item` (
  `id`             INT          NOT NULL AUTO_INCREMENT,
  `id_patricipant` INT          NOT NULL,
  `id_outcome1`    INT          NOT NULL,
  `id_outcome2`    INT ZEROFILL NULL,
  PRIMARY KEY (`id`),
  INDEX `id_patricipant_idx` (`id_patricipant` ASC),
  INDEX `id_outcome1_idx` (`id_outcome1` ASC),
  CONSTRAINT `id_patricipant`
  FOREIGN KEY (`id_patricipant`)
  REFERENCES `totalizator`.`participant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_outcome1`
  FOREIGN KEY (`id_outcome1`)
  REFERENCES `totalizator`.`outcome` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
ALTER TABLE `totalizator`.`market_item`
  CHANGE COLUMN `id_outcome2` `id_outcome2` INT(10) NULL,
  ADD INDEX `id_outcome2_idx` (`id_outcome2` ASC);
ALTER TABLE `totalizator`.`market_item`
  ADD CONSTRAINT `id_outcome2`
FOREIGN KEY (`id_outcome2`)
REFERENCES `totalizator`.`outcome` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
ALTER TABLE `totalizator`.`market_item`
  DROP FOREIGN KEY `id_outcome2`;
ALTER TABLE `totalizator`.`market_item`
  CHANGE COLUMN `id_outcome2` `id_outcome2` INT NULL,
  ADD COLUMN `id_market` INT NOT NULL
  AFTER `id_outcome2`,
  ADD INDEX `id_market_idx` (`id_market` ASC);
ALTER TABLE `totalizator`.`market_item`
  ADD CONSTRAINT `id_outcome2`
FOREIGN KEY (`id_outcome2`)
REFERENCES `totalizator`.`outcome` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_market`
FOREIGN KEY (`id_market`)
REFERENCES `totalizator`.`market` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE IF NOT EXISTS `totalizator`.`market` (
  `id`            INT         NOT NULL AUTO_INCREMENT,
  `name`          VARCHAR(10) NOT NULL,
  `is_changeable` TINYINT     NULL,
  `id_event`      INT         NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_event_idx` (`id_event` ASC),
  CONSTRAINT `id_event`
  FOREIGN KEY (`id_event`)
  REFERENCES `totalizator`.`event` (`id_bookmeker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `totalizator`.`bet_outcome_link` (
  `id`         INT NOT NULL AUTO_INCREMENT,
  `id_bet`     INT NULL,
  `id_outcome` INT NULL,
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
    ON UPDATE NO ACTION
);