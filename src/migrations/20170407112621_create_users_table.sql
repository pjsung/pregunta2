CREATE TABLE pregunta2.users
(
  id              INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nick            VARCHAR(80),
  pass            VARCHAR(80),
  recordClassic   INT                      DEFAULT 0,
  recordChallenge INT                      DEFAULT 0,
  userType        VARCHAR(10)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
CREATE UNIQUE INDEX users_id_uindex
  ON pregunta2.users (id);
