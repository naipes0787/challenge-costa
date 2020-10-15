# Challenge

###### Developed by Leandro Costa


___
**Requirements**
- Maven
- PostgreSQL (Or Docker)

In case you use Docker, I recommend to use the postgres:9.6.6-alpine:
```sh
$ docker pull postgres:9.6.6-alpine
$ docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=challenge 
```

___
**Funcionalidades**

The API provides user, albums, photos, posts and comments information using [JSONPlaceholder](https://jsonplaceholder.typicode.com)
as the data source. Assuming that we are using localhost:8081 this are the endpoints:
- localhost:8081/challenge/users
- localhost:8081/challenge/albums
- localhost:8081/challenge/photos
- localhost:8081/challenge/posts
- localhost:8081/challenge/comments

You can get more information about each one looking at the Swagger documentation: localhost:8081/challenge/swagger-ui.html.
