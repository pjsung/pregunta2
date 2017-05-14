CREATE TABLE pregunta2.Questions
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  question    VARCHAR(128)    NOT NULL,
  answer1     VARCHAR(64)     NOT NULL,
  answer2     VARCHAR(64)     NOT NULL,
  answer3     VARCHAR(64)     NOT NULL,
  answer4     VARCHAR(64)     NOT NULL,
  category_id INT             NOT NULL,
  CONSTRAINT Questions_Categories_id_fk FOREIGN KEY (id) REFERENCES Categories (id)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
CREATE UNIQUE INDEX Questions_id_uindex
  ON pregunta2.Questions (id);
CREATE UNIQUE INDEX Questions_question_uindex
  ON pregunta2.Questions (question);
