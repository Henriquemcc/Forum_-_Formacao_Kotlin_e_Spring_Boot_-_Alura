[English Version](README.EN.md)

# Formação Kotlin e Spring Boot

Este repositório contém os arquivos desenvolvidos durante a [Formação de Kotlin e Spring Boot](https://cursos.alura.com.br/formacao-kotlin-spring-boot) realizado na plataforma [Alura Cursos Online](https://alura.com.br).

## Repositório Oficial da Alura

Os arquivos oficiais do curso, desenvolvidos pelo instrutor, estão disponíveis no repositório do Github deste curso da Alura:

[<img src="https://www.alura.com.br/assets/api/cursos/api-rest-kotlin-spring-boot-camada-web.svg" width="16px" height="16px"> API REST com Kotlin e Spring Boot: Camada Web](https://github.com/alura-cursos/2208-kotlin-spring).

[<img src="https://www.alura.com.br/assets/api/cursos/api-rest-kotlin-spring-boot-camada-persistencia.svg" width="16px" height="16px"> API REST com Kotlin e Spring Boot: Camada de persistência](https://github.com/alura-cursos/2210-kotlin-spring).

[<img src="https://www.alura.com.br/assets/api/cursos/kotlin-spring-seguranca-infraestrutura.svg" width="16px" height="16px"> Kotlin e Spring: segurança e infraestrutura](https://github.com/alura-cursos/2217-kotlin-spring/).

[<img src="https://www.alura.com.br/assets/api/cursos/kotlin-spring-testes-automatizados-documentacao-api.svg" width="16px" height="16px"> Kotlin e Spring: testes automatizados e documentação de API](https://github.com/alura-cursos/2373-kotlin-spring-testes-automatizados).

[<img src="https://www.alura.com.br/assets/api/cursos/kotlin-spring-recursos-avancados.svg" width="16px" height="16px"> Kotlin e Spring: recursos avançados](https://github.com/alura-cursos/2519-kotlin-spring-outros-recursos).

## Cursos

Os cursos em que estes arquivos foram desenvolvidos:

[<img src="https://www.alura.com.br/assets/api/cursos/api-rest-kotlin-spring-boot-camada-web.svg" width="16px" height="16px"> API REST com Kotlin e Spring Boot: Camada Web](https://cursos.alura.com.br/course/api-rest-kotlin-spring-boot-camada-web).

[<img src="https://www.alura.com.br/assets/api/cursos/api-rest-kotlin-spring-boot-camada-persistencia.svg" width="16px" height="16px"> API REST com Kotlin e Spring Boot: Camada de persistência](https://cursos.alura.com.br/course/api-rest-kotlin-spring-boot-camada-persistencia).

[<img src="https://www.alura.com.br/assets/api/cursos/kotlin-spring-seguranca-infraestrutura.svg" width="16px" height="16px"> Kotlin e Spring: segurança e infraestrutura](https://cursos.alura.com.br/course/kotlin-spring-seguranca-infraestrutura).

[<img src="https://www.alura.com.br/assets/api/cursos/kotlin-spring-testes-automatizados-documentacao-api.svg" width="16px" height="16px"> Kotlin e Spring: testes automatizados e documentação de API](https://cursos.alura.com.br/course/kotlin-spring-testes-automatizados-documentacao-api).

[<img src="https://www.alura.com.br/assets/api/cursos/kotlin-spring-recursos-avancados.svg" width="16px" height="16px"> Kotlin e Spring: recursos avançados](https://cursos.alura.com.br/course/kotlin-spring-recursos-avancados).

## Sites Externos

Os sites externos acessados durante esses cursos foram:

https://start.spring.io

https://mvnrepository.com

https://bcrypt-generator.com

https://jwt.io

https://java.testcontainers.org/

## Requisitos

Para compilar e executar este programa é necessário ter instalado os seguintes programas:

- [Java JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)

## Como Executar

Para executar este programa, na pasta do projeto abra um terminal (ou prompt de comando) e digite o seguinte comando:

No Terminal do Linux e Mac, ou no PowerShell do Windows:

```
./mvnw spring-boot:run
```

No Prompt de Comando do Windows:

```
mvnw spring-boot:run
```

## Como Testar

Para testar o funcionamento deste programa, instale o [Postman](https://www.postman.com/) e importe a [Postman Collection](postman_collection.json), e realize as requisições desejadas.

## Container Docker

### Construir Container

Para construir o container docker, na pasta do projeto abra um terminal (ou prompt de comando) e digite o seguinte comando:

No Terminal do Linux e Mac:

```
./build-container.bash
```

No Prompt de Comando do Windows:

```
build-container.bat
```

No PowerShell:

```
.\build-container.ps1
```

### Executar Container

Para executar o container docker, após ter construído o container, abra um terminal (ou prompt de comando) e digite o seguinte comando:

```
docker run -p 3080:8080 forum
```

E o programa executará na porta 3080.

## Documentação da API

Para acessar a documentação da API, [execute o programa](#como-executar), e em um navegador web acesse: http://www.localhost:8080/swagger-ui.html

## Redis

### Testando o cache

Para testar o cache do Redis, com o [programa em execução](#como-executar), abra um terminal (ou prompt de comando) e digite o seguinte comando:

```
docker exec -it redis-local bash
```

E depois:

```
redis-cli monitor
```

Dessa forma será mostrada a interação da aplicação com o Redis. 