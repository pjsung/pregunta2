CREATE TABLE pregunta2.Questions
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  question    VARCHAR(128)    NOT NULL,
  option1     VARCHAR(64)     NOT NULL,
  option2     VARCHAR(64)     NOT NULL,
  option3     VARCHAR(64)     NOT NULL,
  option4     VARCHAR(64)     NOT NULL,
  answer      INT             NOT NULL,
  category_id INT             NOT NULL,
  CONSTRAINT Questions_Categories_id_fk FOREIGN KEY (id) REFERENCES Categories (id)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
CREATE UNIQUE INDEX Questions_id_uindex
  ON pregunta2.Questions (id);
CREATE UNIQUE INDEX Questions_question_uindex
  ON pregunta2.Questions (question);
