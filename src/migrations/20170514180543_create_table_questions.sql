CREATE TABLE pregunta2.questions
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  question    VARCHAR(128)    NOT NULL,
  option1     VARCHAR(64)     NOT NULL,
  option2     VARCHAR(64)     NOT NULL,
  option3     VARCHAR(64)     NOT NULL,
  option4     VARCHAR(64)     NOT NULL,
  answer      VARCHAR(64)     NOT NULL,
  category_id INT             NOT NULL #,
  #CONSTRAINT Questions_Categories_id_fk FOREIGN KEY (id) REFERENCES categories (id)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
CREATE UNIQUE INDEX questions_id_uindex
  ON pregunta2.questions (id);
CREATE UNIQUE INDEX questions_question_uindex
  ON pregunta2.questions (question);
