CREATE TABLE questions
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  question    VARCHAR(128),
  option1     VARCHAR(64),
  option2     VARCHAR(64),
  option3     VARCHAR(64),
  option4     VARCHAR(64),
  answer      VARCHAR(64),
  category_id INT
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

