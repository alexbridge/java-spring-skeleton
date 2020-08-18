## Java skeleton application with spring framework

This id a demo skeleton for RESTful java applications

### Config

ENV variables needed:

- `APP_PORT` : a port, that application run on

### Run

You can run it with IDE, `com.example.Application.main` as a start point
 
You can start from cli:

- build project `gradle clean build`
- run artifact `APP_PORT=8090 java -jar java-spring-skeleton-0.1.0.jar`

Implemented endpoints:

- GET /messages - all message from repository
- POST /messages - add new message to repository
- GET /messages/{id} - get message by id, return 404 if not found
- PUT /messages/{id} - update message by id, return 404 if not found
- DELETE /messages/{id} - delete message by id, return 404 if not found

#### Performance

You can check it with apache benchmark 

```shell
    ab -n 10000 -c 100 http://localhost:8080/messages
```