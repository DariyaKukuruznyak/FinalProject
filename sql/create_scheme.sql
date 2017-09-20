CREATE SCHEMA `totalizator` DEFAULT CHARACTER SET utf8 ;

CREATE  TABLE IF NOT EXISTS `totalizator`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT
--   `number` INT NOT NULL ,
--   `department` VARCHAR(45) NULL ,
--   PRIMARY KEY (`id`)
);

CREATE  TABLE IF NOT EXISTS `totalizator`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT
--   `name` VARCHAR(45) NULL ,
--   `surname` VARCHAR(45) NULL ,
--   `enrolment_date` DATE NULL ,
--   `group_id` INT ,
--   PRIMARY KEY (`id`)
);

