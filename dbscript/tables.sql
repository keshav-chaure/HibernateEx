CREATE  TABLE `keshav_db`.`Department` (
  `dept_id` INT NOT NULL AUTO_INCREMENT ,
  `dept_name` VARCHAR(45) NOT NULL ,
  `dept_code` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`dept_id`) ,
  UNIQUE INDEX `dept_code_UNIQUE` (`dept_code` ASC) );


  CREATE  TABLE `keshav_db`.`Employee` (
    `emp_id` INT NOT NULL AUTO_INCREMENT ,
    `emp_name` VARCHAR(45) NULL ,
    `emp_city` VARCHAR(45) NULL ,
    `emp_dept` INT NOT NULL ,
    PRIMARY KEY (`emp_id`) ,
    UNIQUE INDEX `emp_dept_UNIQUE` (`emp_dept` ASC) ,
    CONSTRAINT `dept_id`
      FOREIGN KEY (`emp_dept` )
      REFERENCES `keshav_db`.`Department` (`dept_id` )
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);

