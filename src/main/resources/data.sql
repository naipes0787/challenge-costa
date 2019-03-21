/* Se limpia la base de datos al reiniciar */
DELETE FROM album_user;

DELETE FROM album;

DELETE FROM user;

DELETE FROM photo;

DELETE FROM access_type WHERE id >= 1;

INSERT INTO access_type VALUES
  ('1', 'Read'),
  ('2', 'Write'),
  ('3', 'Read and Write');