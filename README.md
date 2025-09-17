# Documentation
This is a Java project built on springboot framework for POC showcasing ORM mapping using Spring JPA. Other frameworks include lombok, redis, openAPI and postgres.  

Use docker for `redis` and `postgresql`. Here are few commands:
- redis:   
`docker run --name redis -p 6379:6379 redis`  (start redis container, if the image is not available docker will download it for you)
    - start redis-cli in docker:  
    `docker exec -it redis redis-cli`

- postgres:   
`docker run --name postgres-local -e POSTGRES_USER=user -e POSTGRES_PASSWORD=<see_application.yml> -e POSTGRES_DB=orm-poc -p 5432:5432 -d postgres:15`
    - start psql in docker:  
    `docker exec -it postgres-local psql -U user -d orm-poc`
    - write queries in the cli to see the table:
    `select * from customer;`

- [swagger link](http://localhost:8080/poc/swagger-ui/index.html)


