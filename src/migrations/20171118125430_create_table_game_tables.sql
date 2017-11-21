CREATE TABLE game_tables
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user1_id    INT,
  user2_id    INT,
  user1_score INT,
  user2_score INT
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;