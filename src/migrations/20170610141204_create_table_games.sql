CREATE TABLE games
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  question_id INT,
  user_id     INT,
  game_mode   INT
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

