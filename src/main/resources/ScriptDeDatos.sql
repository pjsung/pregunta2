#DELETE * FROM pregunta2.Questions;
#DELETE * FROM pregunta2.Categories;
INSERT INTO pregunta2.categories (id, name, created, updated_at) VALUES (1, 'Arte', '2017-05-21 18:23:52', '2017-05-21 18:23:57');
INSERT INTO pregunta2.categories (id, name, created, updated_at) VALUES (2, 'Ciencia', '2017-05-21 18:26:01', '2017-05-21 18:26:03');
INSERT INTO pregunta2.categories (id, name, created, updated_at) VALUES (3, 'Deporte', '2017-05-21 18:26:04', '2017-05-21 18:26:06');
INSERT INTO pregunta2.categories (id, name, created, updated_at) VALUES (4, 'Entretenimiento', '2017-05-21 18:26:09', '2017-05-21 18:26:14');
INSERT INTO pregunta2.categories (id, name, created, updated_at) VALUES (5, 'Geografía', '2017-05-21 18:26:28', '2017-05-21 18:26:32');
INSERT INTO pregunta2.categories (id, name, created, updated_at) VALUES (6, 'Historia', '2017-05-21 18:27:40', '2017-05-21 18:27:35');


INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id)
VALUES ('¿Quién pintó el cuadro "La Sonrisa de Mona Lisa"?', 'Leonardo Da Vinci', 'Pablo Picasso', 'Vicent Van Gogh', 'Claude Monet', '1', 1);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id)
VALUES ('¿Cuál era la nacionalidad de Claude Monet?', 'Alemana', 'Británica', 'Española', 'Francesa', '4', 1);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Qué ciudades describe Italo Calvino en “Las ciudades invisibles”?', 'Ciudades asiáticas', 'Las que Marco Polo visitó en sus viajes',
   'Ciudades europeas', 'Ciudades imaginadas por Marco Polo', '4', 1);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id)
VALUES ('¿Quién es el autor de la obra teatral “Casa de Muñecas”?', 'Henrik Ibsen', 'Isaac Asimov', 'Émile Zola', 'Sigrid Undset', '1', 1);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id)
VALUES ('¿Cuál fue el género más cultivado por los autores de la generación del 27?', 'Ensayo', 'Poesía', 'Teatro', 'Novela', '2', 1);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id)
VALUES ('¿A qué pintor pertenece la obra "Serpientes de agua"?', 'Gustave Klimt', 'Vicent Van Gogh', 'Pablo Picasso', 'Leonardo Da Vinci', '1', 1);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Quién fue Antonio Lucio Vivaldi?', 'Guitarrista Clásico', 'Tenor de Opera', 'Violinista y Compositor del Barroco', 'Clavesista Italiano', '3',
   1);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id)
VALUES ('¿Quién pintó La Capilla Sixtina?', 'Leonardo Da Vinci', 'Pablo Piccaso', 'Miguel Ángel', 'Claude Monet', '3', 1);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuál es el género teatral intermedio entre la comedia y la tragedia?', 'Drama', 'Zarzuela', 'Entremés',
   'Farsa', '1', 1);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Qué obra de mármol de Miguel Ángel se encuentra en la Basílica de San Pedro?', 'Moisés', 'La Piedad', 'Capilla Sixtina',
   'David', '2', 1);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿A qué distancia promedio está el Sol de la Tierra?', '120 millones de kilómetros', '150 millones de kilómetros', '170 millones de kilómetros',
   '200 millones de kilómetros', '2', 2);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuál de las sisguientes enfermedades ataca al higado?', 'Hepatitis', 'Diabetes', 'Artrósis ',
   'Cifoescoliosis', '1', 2);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Qué significa ARN?', 'Ácido Rico en Nucleo', 'Ácido Desoxirribonucleico', 'Ácido Ribonucleico',
   'Antena Rosa y Numerada', '3', 2);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuántos centímetros cúbicos tiene un litro?', '100', '1000', '10',
   '1', '2', 2);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cómo se llama la rama de las matemáticas en que los números son representados por letras y símbolos?', 'Adición ', 'Álgebra', 'Geometría',
   'Topología', '2', 2);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuántos hidrógenos tiene la molécula de metano?', '10', '4', '6',
   '8', '2', 2);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cómo se llama el movimiento que realiza la Tierra alrededor del Sol?', 'Traslación', 'Rotación', 'La Tierra no se mueve',
   'Órbita', '1', 2);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Qué sustancia permite realizar la fotosíntesis?', 'Savia', 'Cloroformo', 'Clorofila',
   'Agua', '3', 2);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuánto es la raíz cuadrada de 625?', '25', '11', '26',
   '12', '1', 2);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Quién postuló la ley del principio de inercia?', 'Albert Einstein', 'Víctor Alvarado', 'Isaac Newton',
   'John Kennedy', '3', 2);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Qué jugadora de hockey sobre césped ha ganado 7 veces el premio a la mejor jugadora del mundo de 2013?', 'Luciana Aymar', 'Natascha Keller',
   'Alyson Annan',
   'Maartje Paumen', '1', 3);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuál es el estilo de natación más rápido?', 'Crol', 'Espalda', 'Mariposa',
   'Pecho', '1', 3);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuántos jugadores componen un equipo de rugby?', '11', '12', '15',
   '18', '3', 3);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿En qué país se inventó el voleibol?', 'Gran Bretaña', 'Francia', 'Rusia',
   'Estados Unidos', '4', 3);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuánto dura un partido de fútbol?', '90 minutos', '45 minutos', '75 minutos',
   '80 minutos', '1', 3);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿En cuál de estos deportes no hay árbitro?', 'Ultimate', 'Football', 'Basketball',
   'Volleyball', '1', 3);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuál es el país de origen del futbolista Lionel Messi?', 'España', 'Argentina', 'Brasil',
   'Chile', '2', 3);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Dónde se inventó el tenis de mesa?', 'China', 'Japón', 'Corea del Sur',
   'Inglaterra', '4', 3);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Qué arte marcial utiliza mayor procentaje de extremidades inferiores?', 'Taekwondo ', 'Judo', 'Karate',
   'Boxeo', '1', 3);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuál es la última cinta en las artes marciales?', 'Roja ', 'Blanca ', 'Verde',
   'Negra', '4', 3);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿En qué año se estrenó la película de Disney “Pinocho”?', '1940', '1950', '1952',
   '1946', '1', 4);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Quién es la mascota de SEGA?', 'Sonic', 'Mario', 'Pac Man',
   'Ryu', '1', 4);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Quién es Bella en la saga “Crepúsculo”?', 'Cristina Ricci', 'Kristen Stewart', 'Emma Watson',
   'Dakota Fanning', '2', 4);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿A qué película de Disney pertenece la canción “Un mundo ideal”?', 'Aladdín', 'Pocahontas', 'Mulán',
   'Hércules', '1', 4);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿A quién se considera el Rey del Pop?', 'Justin Bieber', 'Michael Jackson', 'Zayn Malik',
   'Zac Efron', '2', 4);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Qué día es San Valentín?', '14 de Marzo', '15 de Febrero ', '14 de Febrero',
   '15 de Marzo', '3', 4);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuántos colores tiene un cubo de Rubik clásico?', '4', '8', '6',
   '9', '3', 4);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cómo se llama la serie de dibujos animados en la que sale un gato cósmico del siglo XXI?', 'Doraemon', 'Bob Esponja', 'Las tortugas ninja',
   'Las Supernenas ', '1', 4);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cómo se llama el pájaro símbolo de los Juegos del Hambre?', 'Lechuza', 'Gale', 'Sinsajo',
   'Llamas', '3', 4);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Quién es el profesor del colegio Hogwarts que se convierte en Hombre lobo?', 'Remus Lupin', 'Romulus Lupin', 'James Potter',
   'Subill Trelawney', '1', 4);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿En cuál de los siguientes países NO hay ningún desierto?', 'España', 'Chile', 'Mongolia',
   'Alemania', '4', 5);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuál de estas características no pertenece al clima Mediterráneo?', 'Veranos secos y calurosos', 'Es un tipo de clima templado',
   'Lluvias muy abundantes',
   'Variables temperaturas en primavera', '3', 5);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Con cuántos países limita Argentina?', '3', '4', '5',
   '6', '3', 5);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuál es la capital de Suiza?', 'Berna', 'Zurich', 'Ginebra',
   'Basilea', '1', 5);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿En qué país está Ushuaia, la ciudad más al sur del mundo?', 'Chile', 'Argentina', 'Sudáfrica',
   'Nueva Zelanda', '2', 5);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuál de estos países africanos no tiene costa?', 'Mauritania', 'Senegal', 'Gambia',
   'Todas son correctas', '4', 5);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿En qué cordillera están la mayoría de las grandes montañas?', 'En el Karakórum', 'En el Himalaya', 'En los Andes',
   'En las Montañas Rocosas', '2', 5);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Qué ciudad europea es famosa por la belleza de su parlamento?', 'París', 'Madrid', 'Praga',
   'Budapest', '4', 5);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿En qué continente/s se encuentra Turquía?', 'América', 'Asia y Europa', 'África y Europa',
   'Oceania', '2', 5);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuál de estos países está en una isla?', 'Japón', 'Jamaica', 'Madagascar',
   'Todas son correctas', '4', 5);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿En qué año tuvo lugar el ataque a Pearl Harbor?', '1939', '1940', '1941',
   '1942', '3', 6);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuál es la ciudad más antigua de América Latina?', 'Caral', 'Valparaíso', 'Arequipa',
   'La Paz', '1', 6);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Con qué emperador estuvo casada Cleopatra?', 'Ptolomeo XIV', 'Julio César', 'Marco Antonio',
   'Todas son correctas', '4', 6);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿El Renacimiento marcó el inicio de la Edad...?', 'Moderna', 'Antigüedad clásica', 'Contemporánea',
   'Media', '1', 6);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Qué se celebra el 8 de Marzo?', 'El día del trabajo', 'El día del medio ambiente', 'El día de la mujer',
   'El día del niño', '3', 6);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Cuántos siglos duró el Siglo de Oro?', 'Dos', 'Uno', 'Tres',
   'Medio', '1', 6);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Quién liberó a Argentina, Chile y Perú?', 'Ernesto Che Guevara', 'Manuel Belgrano', 'José de San Martín',
   'Simón Bolívar', '3', 6);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Quién gobernó Francia desde 1799 a 1815?', 'Adam Smith', 'François Quesnay', 'Napoleón Bonaparte',
   'Louis Bonaldgug', '3', 6);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿Qué motivó la rebelión que dió lugar a la Guerra de la Independencia de Estados Unidos?', 'Esclavitud', 'Impuesto', 'Corrupción',
   'Represión', '2', 6);
INSERT INTO pregunta2.questions (question, option1, option2, option3, option4, answer, category_id) VALUES
  ('¿En qué año viajó el primer hombre a la luna?', '1969', '1968', '1979',
   '1957', '1', 6);
#Bibliografía: http://www.respuestaspreguntados.org/


INSERT INTO pregunta2.users (nick, pass, recordClassic, recordChallenge, record1Vs1, userType)
VALUES ('celia', 'celia', 0, 0, 0, 'admin');
INSERT INTO pregunta2.users (nick, pass, recordClassic, recordChallenge, record1Vs1, userType)
VALUES ('franco', 'franco', 0, 0, 0, 'common');
INSERT INTO pregunta2.users (nick, pass, recordClassic, recordChallenge, record1Vs1, userType)
VALUES ('marcelo', 'marcelo', 0, 0, 0, 'common');
