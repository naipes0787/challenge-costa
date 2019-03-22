# Wolox Challenge

###### Desarrollado por Leandro Costa


___
**Consideraciones**
- Se utilizó inglés para la nomenclatura de tablas, clases, etcétera
- Se utilizó la annotation @Entity para cada parte del modelo, es decir, habrá una tabla por cada objeto del modelo
- Se agregó un script inicial (_data.sql_) para volver a un estado limpio la base de datos al reiniciar la aplicación (En caso de no precisarse, puede eliminarse)
- La tabla access_type posee los permisos. 1 = Read, 2 = Write, 3 = Read and Write


___
**Requisitos**
- Poseer Maven
- Es necesario tener MySQL
- Se precisa tener creada la base de datos _wolox___challenge_
- Se deberá configurar el acceso a la base de datos en el archivo _application.properties_ del directorio resources


___
**Iniciar aplicación**
Para iniciar ejecutar en el directorio raíz: _mvn spring-boot:run_


___
La API provee información de usuarios, álbumes, fotos, posts y comentarios con las siguientes urls (Suponiendo que se utiliza desde localhost en el puerto 8080):

localhost:8080/api/users

localhost:8080/api/albums

localhost:8080/api/photos

localhost:8080/api/posts

localhost:8080/api/comments


Para cada una de ellas, si desea obtenerse información de sólo un registro podrá utilizarse el id como se muestra en el siguiente ejemplo para un usuario de id = 3:

localhost:8080/api/users/3

**Otras funcionalidades:**
- Obtener las fotos del determinado usuario 3: GET _localhost:8080/api/photosByUserId?userId=3_
- Obtener los comentarios con el name 'id labore ex et quam laborum':
GET _localhost:8080/api/filterComments?name=id%20labore%20ex%20et%20quam%20laborum_
- Obtener los comentarios del usuario 3: GET _localhost:8080/api/filterComments?userId=3_
- Compartir el álbum 2 con el usuario 4 en modo lectura: POST _localhost:8080/api/shareAlbum?albumId=2&userId=4&accessTypeId=1_
- Modificar a modo escritura el álbum 2 con el usuario 4: PUT _localhost:8080/api/shareAlbum?albumId=2&userId=4&accessTypeId=2_
- Obtener usuarios asociados al album 2 en modo escritura: GET _localhost:8080/api/usersByAlbumAndAccessType?albumId=2&accessTypeId=2_
