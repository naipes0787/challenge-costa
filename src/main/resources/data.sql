DELETE FROM tipo_permiso WHERE id >= 1;

INSERT INTO tipo_permiso VALUES
  ('1', 'Lectura'),
  ('2', 'Escritura'),
  ('3', 'Lectura y escritura');