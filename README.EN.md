[Versão em Português](README.md)

# Training Kotlin and Spring Boot

This repository contains the files developed during the [Training of Kotlin and Spring Boot](https://cursos.alura.com.br/formacao-kotlin-spring-boot) held on the [Alura Cursos Online](https://alura.com.br) platform.

## Alura's Official Repository

The official files for the course, developed by the instructor, are available in the GitHub repository of this Alura course:

[<img src="https://www.alura.com.br/assets/api/cursos/api-rest-kotlin-spring-boot-camada-web.svg" width="16px" height="16px"> REST API with Kotlin and Spring Boot: Web Layer (API REST com Kotlin e Spring Boot: Camada Web)](https://github.com/alura-cursos/2208-kotlin-spring).

[<img src="https://www.alura.com.br/assets/api/cursos/api-rest-kotlin-spring-boot-camada-persistencia.svg" width="16px" height="16px"> REST API with Kotlin and Spring Boot: Persistence Layer (API REST com Kotlin e Spring Boot: Camada de persistência)](https://github.com/alura-cursos/2210-kotlin-spring).

[<img src="https://www.alura.com.br/assets/api/cursos/kotlin-spring-seguranca-infraestrutura.svg" width="16px" height="16px"> Kotlin and Spring: Security and Infrastructure (Kotlin e Spring: segurança e infraestrutura)](https://github.com/alura-cursos/2217-kotlin-spring/).

[<img src="https://www.alura.com.br/assets/api/cursos/kotlin-spring-testes-automatizados-documentacao-api.svg" width="16px" height="16px"> Kotlin and Spring: Automated Testing and API Documentation (Kotlin e Spring: testes automatizados e documentação de API)](https://github.com/alura-cursos/2373-kotlin-spring-testes-automatizados).

## Courses

The courses in which these files were developed:

[<img src="https://www.alura.com.br/assets/api/cursos/api-rest-kotlin-spring-boot-camada-web.svg" width="16px" height="16px"> REST API with Kotlin and Spring Boot: Web Layer (API REST com Kotlin e Spring Boot: Camada Web)](https://cursos.alura.com.br/course/api-rest-kotlin-spring-boot-camada-web).

[<img src="https://www.alura.com.br/assets/api/cursos/api-rest-kotlin-spring-boot-camada-persistencia.svg" width="16px" height="16px"> REST API with Kotlin and Spring Boot: Persistence Layer (API REST com Kotlin e Spring Boot: Camada de persistência)](https://cursos.alura.com.br/course/api-rest-kotlin-spring-boot-camada-persistencia).

[<img src="https://www.alura.com.br/assets/api/cursos/kotlin-spring-seguranca-infraestrutura.svg" width="16px" height="16px"> Kotlin and Spring: Security and Infrastructure (Kotlin e Spring: segurança e infraestrutura)](https://cursos.alura.com.br/course/kotlin-spring-seguranca-infraestrutura).

[<img src="https://www.alura.com.br/assets/api/cursos/kotlin-spring-testes-automatizados-documentacao-api.svg" width="16px" height="16px"> Kotlin and Spring: Automated Testing and API Documentation (Kotlin e Spring: testes automatizados e documentação de API)](https://cursos.alura.com.br/course/kotlin-spring-testes-automatizados-documentacao-api).

## External websites

The external websites accessed during these course were:

https://start.spring.io

https://mvnrepository.com

https://bcrypt-generator.com

https://jwt.io

https://java.testcontainers.org/

## Requirements

To compile and run this program you must have the following programs installed:

- [Java JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)

## How to Execute

To execute this program, in the project folder open a terminal (or Command Prompt) and type the following command:

In Linux and Mac terminal, or on Windows PowerShell:

```
./mvnw spring-boot:run
```

In Windows Command Prompt:

```
mvnw spring-boot:run
```

## How to Test

To test the operation of this program, install [Postman](https://www.postman.com/) and import the [Postman Collection](postman_collection.json), and perform the desired requests.

## Docker Container

### Building the Container

To build the docker container, in the project folder open a terminal (or Command Prompt) and type the following command:

In Linux or Mac Terminal:

```
./build-container.bash
```

In Windows Command Prompt:

```
build-container.bat
```

In PowerShell:

```
.\build-container.ps1
```

### Execute the Container

To execute the docker container, after have built the container, open a terminal (or command prompt) and type the following command:

```
docker run -p 3080:8080 forum
```

And the program will execute in the port 3080.

## API Documentation

To access the API documentation, [execute the program](#how-to-execute), and in a web browser access: http://www.localhost:8080/swagger-ui.html