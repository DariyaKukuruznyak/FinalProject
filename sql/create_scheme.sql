CREATE SCHEMA IF NOT EXISTS `totalizator`
  DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`bet` (
  `id`                INT(11)                          NOT NULL AUTO_INCREMENT,
  `sum_in`            DECIMAL(10, 2)                   NOT NULL,
  `sum_out`           DECIMAL(10, 2) UNSIGNED ZEROFILL NULL     DEFAULT NULL,
  `type`              VARCHAR(8)                       NOT NULL,
  `result`            VARCHAR(10)                      NULL     DEFAULT NULL,
  `description`       VARCHAR(100)                     NULL     DEFAULT NULL,
  `date`              DATETIME                         NOT NULL,
  `client_id`         INT(11)                          NOT NULL,
  `total_coefficient` DOUBLE                           NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `client_id_idx` (`client_id` ASC),
  CONSTRAINT `client_id`
  FOREIGN KEY (`client_id`)
  REFERENCES `totalizator`.`client` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`client` (
  `id`          INT(11)                          NOT NULL,
  `max_bet`     INT(10) UNSIGNED ZEROFILL        NOT NULL,
  `balance`     DECIMAL(10, 2) UNSIGNED ZEROFILL NOT NULL,
  `description` VARCHAR(200)                     NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_frk`
  FOREIGN KEY (`id`)
  REFERENCES `totalizator`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`event` (
  `id`            INT(11)                      NOT NULL AUTO_INCREMENT,
  `open_date`     DATE                         NOT NULL,
  `tournament_id` INT(11)                      NOT NULL,
  `status`        VARCHAR(20)                  NOT NULL,
  `is_suspended`  TINYINT(3) UNSIGNED ZEROFILL NOT NULL,
  `bookmaker_id`  INT(11)                      NOT NULL,
  `turnover`      DECIMAL(10, 2)               NULL     DEFAULT NULL,
  `profit`        DECIMAL(10, 2)               NULL     DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `author_frk_idx` (`bookmaker_id` ASC),
  INDEX `tournament_frk_idx` (`tournament_id` ASC),
  CONSTRAINT `author_frk`
  FOREIGN KEY (`bookmaker_id`)
  REFERENCES `totalizator`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tournament_frk`
  FOREIGN KEY (`tournament_id`)
  REFERENCES `totalizator`.`tournament` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`market` (
  `id`       INT(11)     NOT NULL AUTO_INCREMENT,
  `name`     VARCHAR(10) NOT NULL,
  `event_id` INT(11)     NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `event_frk_idx` (`event_id` ASC),
  CONSTRAINT `event_frk`
  FOREIGN KEY (`event_id`)
  REFERENCES `totalizator`.`event` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`outcome` (
  `id`          INT(11)      NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(100) NOT NULL,
  `coefficient` DOUBLE       NULL     DEFAULT NULL,
  `market_id`   INT(11)      NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `market_fkt_idx` (`market_id` ASC),
  CONSTRAINT `market_fkt`
  FOREIGN KEY (`market_id`)
  REFERENCES `totalizator`.`market` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`participant` (
  `id`      INT(11)     NOT NULL AUTO_INCREMENT,
  `name`    VARCHAR(50) NOT NULL,
  `age`     INT(11)     NOT NULL,
  `weight`  INT(11)     NOT NULL,
  `trainer` VARCHAR(50) NOT NULL,
  `jockey`  VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `participant_id_uindex` (`id` ASC),
  UNIQUE INDEX `participant_name_uindex` (`name` ASC)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`user` (
  `id`                   INT(11)     NOT NULL AUTO_INCREMENT,
  `first_name`           VARCHAR(45) NOT NULL,
  `last_name`            VARCHAR(45) NOT NULL,
  `email`                VARCHAR(45) NOT NULL,
  `login`                VARCHAR(20) NOT NULL,
  `password`             VARCHAR(20) NOT NULL,
  `date_of_registration` DATE        NOT NULL,
  `user_role`            VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`tournament` (
  `id`         INT(11)      NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(100) NOT NULL,
  `start_date` DATE         NOT NULL,
  `winner`     VARCHAR(45)  NULL     DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`participant_link_tournament` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT,
  `participant_id` INT(11) NULL     DEFAULT NULL,
  `tournament_id`  INT(11) NULL     DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `participant_frk_idx` (`participant_id` ASC),
  INDEX `tournament_frk_idx` (`tournament_id` ASC),
  CONSTRAINT `participant_to_tournament_frk`
  FOREIGN KEY (`participant_id`)
  REFERENCES `totalizator`.`participant` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `tournament_to_particioant_frk`
  FOREIGN KEY (`tournament_id`)
  REFERENCES `totalizator`.`tournament` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `totalizator`.`bet_item` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45) NOT NULL,
  `coefficient` DOUBLE      NULL     DEFAULT NULL,
  `result`      VARCHAR(10) NULL     DEFAULT NULL,
  `outcome_id`  INT(11)     NOT NULL,
  `bet_id`      INT(11)     NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `outcome_frk_idx` (`outcome_id` ASC),
  INDEX `bet_id_idx` (`bet_id` ASC),
  CONSTRAINT `outcome_frk`
  FOREIGN KEY (`outcome_id`)
  REFERENCES `totalizator`.`outcome` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `bet_id`
  FOREIGN KEY (`bet_id`)
  REFERENCES `totalizator`.`bet` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
