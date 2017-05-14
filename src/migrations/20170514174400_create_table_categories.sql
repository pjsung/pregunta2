CREATE TABLE pregunta2.Categories
(
  id         INT PRIMARY KEY                      NOT NULL AUTO_INCREMENT,
  name       VARCHAR(25)                          NOT NULL,
  created    DATETIME DEFAULT CURRENT_TIMESTAMP   NOT NULL,
  updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
CREATE UNIQUE INDEX Categories_id_uindex
  ON pregunta2.Categories (id);
CREATE UNIQUE INDEX Categories_name_uindex
  ON pregunta2.Categories (name);
