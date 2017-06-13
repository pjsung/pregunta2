CREATE TABLE pregunta2.users
(
  id              INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nick            VARCHAR(80)     NOT NULL,
  pass            VARCHAR(80)     NOT NULL,
  recordClassic   INT DEFAULT 0   NOT NULL,
  recordChallenge INT DEFAULT 0   NOT NULL,
  userType        VARCHAR(10)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
CREATE UNIQUE INDEX users_id_uindex
  ON pregunta2.users (id);
CREATE UNIQUE INDEX users_nick_uindex
  ON pregunta2.users (nick);
