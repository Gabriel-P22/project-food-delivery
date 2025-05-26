# Food Delivery

## Dependencies

- Kotlin
- Spring
- Gradlew
- OracleDB

### Architecture

 - DDD
 - SOLID

### Recommended apps

- Intellij
- Dbeaver

### About Project

- It's a simple application to delivery food for everyone!

## Struct

| Camada                     | Descrição                                        |
|----------------------------|--------------------------------------------------|
| **Application (Service)**  | Controllers, clients, UseCases and orchestrators. |
| **Domain (Core)**          | Entities, rules, aggregators, ValueObjects       |
| **Infrastructure (Infra)** | database, configurations, repositories  |


## Entities

### User

| Campo        | Tipo       | Descrição                                   |
| ------------ | ---------- |---------------------------------------------|
| `id`         | `UUID?`    | Identificador único do usuário              |
| `name`       | `String`   | Nome do usuário                             |
| `secondName` | `String`   | Sobrenome do usuário                        |
| `email`      | `String`   | Email do usuário                            |
| `password`   | `String`   | Senha do usuário                            |
| `activate`   | `Boolean`  | Indica se o usuário está ativo              |
| `type`       | `UserType` | Tipo de usuário (ADMIN, CUSTOMER, BUSINESS) |

### Address

| Campo     | Tipo       | Descrição                      |
|-----------| ---------- |--------------------------------|
| `id`      | `UUID?`    | Identificador único do address |
| `street`  | `String`   | Nome da rua                    |
| `number`  | `String`   | Numero da rua                  |
| `zipcode` | `String`   | codigo de area do endereco     |
| `userId`  | `String`   | id do usuario                  |