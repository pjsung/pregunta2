CREATE TABLE pregunta2.questions
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  question    VARCHAR(128),
  option1     VARCHAR(64),
  option2     VARCHAR(64),
  option3     VARCHAR(64),
  option4     VARCHAR(64),
  answer      VARCHAR(64),
  category_id INT             #,
  #CONSTRAINT Questions_Categories_id_fk FOREIGN KEY (id) REFERENCES categories (id)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
CREATE UNIQUE INDEX questions_id_uindex
  ON pregunta2.questions (id);
