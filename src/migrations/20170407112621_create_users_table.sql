CREATE TABLE pregunta2.Users
(
  id              INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nick            VARCHAR(80)     NOT NULL,
  pass            VARCHAR(80)     NOT NULL,
  recordClassic   INT DEFAULT 0   NOT NULL,
  recordChallenge INT DEFAULT 0   NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
CREATE UNIQUE INDEX Users_id_uindex
  ON pregunta2.Users (id);
CREATE UNIQUE INDEX Users_nick_uindex
  ON pregunta2.Users (nick);
