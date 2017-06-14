CREATE TABLE pregunta2.categories
(
  id         INT PRIMARY KEY                      NOT NULL AUTO_INCREMENT,
  name       VARCHAR(25),
  created    DATETIME DEFAULT CURRENT_TIMESTAMP   NOT NULL,
  updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
CREATE UNIQUE INDEX categories_id_uindex
  ON pregunta2.categories (id);
