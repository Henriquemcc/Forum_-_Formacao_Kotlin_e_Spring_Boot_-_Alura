[English Version](README.EN.md)

# Formação Kotlin e Spring Boot

Este repositório contém os arquivos desenvolvidos durante a [Formação de Kotlin e Spring Boot](https://cursos.alura.com.br/formacao-kotlin-spring-boot) realizado na plataforma [Alura Cursos Online](https://alura.com.br).

## Repositório Oficial da Alura

Os arquivos oficiais do curso, desenvolvidos pelo instrutor, estão disponíveis no repositório do Github deste curso da Alura:

[<img src="https://www.alura.com.br/assets/api/cursos/api-rest-kotlin-spring-boot-camada-web.svg" width="16px" height="16px"> API REST com Kotlin e Spring Boot: Camada Web](https://github.com/alura-cursos/2208-kotlin-spring).

[<img src="https://www.alura.com.br/assets/api/cursos/api-rest-kotlin-spring-boot-camada-persistencia.svg" width="16px" height="16px"> API REST com Kotlin e Spring Boot: Camada de persistência](https://github.com/alura-cursos/2210-kotlin-spring).

[<img src="https://www.alura.com.br/assets/api/cursos/kotlin-spring-seguranca-infraestrutura.svg" width="16px" height="16px"> Kotlin e Spring: segurança e infraestrutura](https://github.com/alura-cursos/2217-kotlin-spring/).

## Cursos

Os cursos em que estes arquivos foram desenvolvidos:

[<img src="https://www.alura.com.br/assets/api/cursos/api-rest-kotlin-spring-boot-camada-web.svg" width="16px" height="16px"> API REST com Kotlin e Spring Boot: Camada Web](https://cursos.alura.com.br/course/api-rest-kotlin-spring-boot-camada-web).

[<img src="https://www.alura.com.br/assets/api/cursos/api-rest-kotlin-spring-boot-camada-persistencia.svg" width="16px" height="16px"> API REST com Kotlin e Spring Boot: Camada de persistência](https://cursos.alura.com.br/course/api-rest-kotlin-spring-boot-camada-persistencia).

[<img src="https://www.alura.com.br/assets/api/cursos/kotlin-spring-seguranca-infraestrutura.svg" width="16px" height="16px"> Kotlin e Spring: segurança e infraestrutura](https://cursos.alura.com.br/course/kotlin-spring-seguranca-infraestrutura).

## Sites Externos

Os sites externos acessados durante esses cursos foram:

https://start.spring.io

https://mvnrepository.com

https://bcrypt-generator.com

https://jwt.io

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