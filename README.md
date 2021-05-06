## Java spring boot boilerplate

This id a demo spring boot boilerplate for RESTful java applications

### Config

ENV variables needed:

- `APP_PORT`: a port, that application run on

### Run

You can run it with IDE, `spring.boilerplate.Application.main` as a start point
 
You can start from cli (see [Makefile](./Makefile) recipes):

- build project `make`
- run `make run`

Implemented endpoints:

- GET /customers - all customers from repository
- POST /customers - add new customer to repository
- GET /customers/{id} - get customer by id, return 404 if not found
- PUT /customers/{id} - update customer by id, return 404 if not found
- DELETE /customers/{id} - delete customer by id, return 404 if not found
- GET /doc - swagger api documentation

#### Benchmarking

You can check it with apache benchmark, see [Makefile](./Makefile) recipe `bench`

```shell
    make bench
```