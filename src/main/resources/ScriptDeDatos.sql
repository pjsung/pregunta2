INSERT INTO pregunta2.Categories (id, name, created, updated_at) VALUES (1, 'Arte', '2017-05-21 18:23:52', '2017-05-21 18:23:57');
INSERT INTO pregunta2.Categories (id, name, created, updated_at) VALUES (2, 'Ciencia', '2017-05-21 18:26:01', '2017-05-21 18:26:03');
INSERT INTO pregunta2.Categories (id, name, created, updated_at) VALUES (3, 'Deporte', '2017-05-21 18:26:04', '2017-05-21 18:26:06');
INSERT INTO pregunta2.Categories (id, name, created, updated_at) VALUES (4, 'Entretenimiento', '2017-05-21 18:26:09', '2017-05-21 18:26:14');
INSERT INTO pregunta2.Categories (id, name, created, updated_at) VALUES (5, 'Geografía', '2017-05-21 18:26:28', '2017-05-21 18:26:32');
INSERT INTO pregunta2.Categories (id, name, created, updated_at) VALUES (6, 'Historia', '2017-05-21 18:27:40', '2017-05-21 18:27:35');

INSERT INTO pregunta2.Questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿A qué distancia promedio está el Sol de la Tierra?', '120 millones de kilómetros', '150 millones de kilómetros', '170 millones de kilómetros',
   '200 millones de kilómetros', 2, 2);


INSERT INTO pregunta2.Users (nick, pass, recordClassic, recordChallenge) VALUES ('celia', 'celia', 0, 0);
