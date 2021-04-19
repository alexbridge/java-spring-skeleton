## Java spring boot skeleton

This id a demo spring boot skeleton for RESTful java applications

### Config

ENV variables needed:

- `APP_PORT`: a port, that application run on

### Run

You can run it with IDE, `spring.skeleton.Application.main` as a start point
 
You can start from cli (see [Makefile](./Makefile) recipes):

- build project `make`
- run `make run`

Implemented endpoints:

- GET /v*/messages - all message from repository
- POST /v*/messages - add new message to repository
- GET /v*/messages/{id} - get message by id, return 404 if not found
- PUT /v*/messages/{id} - update message by id, return 404 if not found
- DELETE /v*/messages/{id} - delete message by id, return 404 if not found

#### Benchmarking

You can check it with apache benchmark, see [Makefile](./Makefile) recipe `bench`

```shell
    make bench
```