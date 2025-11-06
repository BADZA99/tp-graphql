# Documentation : GraphQL et son intégration dans ce projet

### ENONCE DU TP
Concevoir une API GraphQL pour gérer une entité appelée « Book ». L’objectif est de créer un schéma GraphQL permettant de faire des requêtes et des mutations sur les livres, comme récupérer des informations, ajouter de nouveaux livres, ou mettre à jour des données existantes.


## 1. Qu'est-ce que GraphQL ?
GraphQL est un langage de requête pour les API, développé par Facebook. Il permet aux clients de demander exactement les données dont ils ont besoin, ni plus ni moins, via un schéma typé et une seule URL d'accès.

### Objectifs principaux :
- **Flexibilité** : Le client choisit les champs à récupérer.
- **Efficacité** : Une seule requête peut ramener des données issues de plusieurs entités.
- **Typage fort** : Le schéma décrit précisément les types et opérations disponibles.

## 2. Différences avec REST
| REST                                 | GraphQL                                 |
|--------------------------------------|-----------------------------------------|
| Plusieurs endpoints (URL)            | Un seul endpoint                        |
| Réponses fixes (structure imposée)   | Réponses dynamiques (structure choisie) |
| Sur-fetch/sous-fetch possible        | Juste ce qu'il faut (pas de sur/sous)   |
| Basé sur HTTP (GET, POST, etc.)      | Basé sur POST (en général)              |
| Pas de typage fort natif             | Schéma typé et validé                   |

## 3. Concepts clés de GraphQL
- **Query** : Pour lire/récupérer des données.
- **Mutation** : Pour modifier (créer, mettre à jour, supprimer) des données.
- **Input** : Objet d'entrée pour les mutations (équivalent à un DTO pour POST/PUT en REST).
- **Type** : Décrit la structure des objets retournés.
- **Schema** : Fichier qui décrit toutes les opérations et types disponibles.

### Exemple de Query
```graphql
query {
  books {
    id
    title
    author
  }
}
```

### Exemple de Mutation
```graphql
mutation {
  createBook(input: { title: "Titre", author: "Auteur", publishedDate: "2025-11-06", price: 15.99 }) {
    id
    title
  }
}
```

### Exemple d'Input dans le schéma
```graphql
input BookInput {
  title: String!
  author: String!
  publishedDate: String
  price: Float
}
```

## 4. Stack technique utilisée dans ce projet
- **Spring Boot** : Framework principal Java
- **Spring Data JPA** : Accès aux données relationnelles (MySQL)
- **MySQL** : Base de données relationnelle
- **Spring Boot Starter GraphQL** : Intégration GraphQL côté serveur
- **Maven** : Gestionnaire de dépendances/build

## 5. Mise en place de GraphQL dans ce projet

### a. Dépendances Maven (extrait du `pom.xml`)
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-graphql</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
</dependency>
```

### b. Configuration (`application.properties`)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tpgraphql?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### c. Schéma GraphQL (`src/main/resources/graphql/schema.graphqls`)
```graphql
type Book {
  id: ID!
  title: String!
  author: String!
  publishedDate: String
  price: Float
}

type Query {
  books: [Book!]!
  bookById(id: ID!): Book
}

input BookInput {
  title: String!
  author: String!
  publishedDate: String
  price: Float
}

type Mutation {
  createBook(input: BookInput!): Book!
  updateBook(id: ID!, input: BookInput!): Book!
  deleteBook(id: ID!): Boolean!
}
```

### d. Exemple de Resolver Java (extrait)
```java
@MutationMapping
public Book createBook(@Argument("input") BookInput input) {
    Book book = new Book();
    book.setTitle(input.getTitle());
    book.setAuthor(input.getAuthor());
    book.setPublishedDate(LocalDate.parse(input.getPublishedDate()));
    book.setPrice(input.getPrice());
    return bookService.createBook(book);
}
```

### e. Exemple de requête HTTP (dans `testApi.http`)
```http
POST http://localhost:8080/graphql
Content-Type: application/json

{
  "query": "mutation { createBook(input: { title: \"jojo bizarre adventure\", author: \"araki\", publishedDate: \"1980-11-06\", price: 275.99 }) { id title author publishedDate price } }"
}
```

## 6. Fonctionnement global dans ce projet
- Le client envoie une requête GraphQL (query ou mutation) à `/graphql`.
- Spring Boot/GraphQL lit le schéma, mappe la requête sur le resolver Java.
- Le resolver appelle le service, qui utilise JPA pour accéder à la base MySQL.
- Le résultat est renvoyé au client, exactement sous la forme demandée.

## 7. Avantages de GraphQL dans ce projet
- Un seul endpoint pour toutes les opérations.
- Les clients (front, mobile) choisissent les champs dont ils ont besoin.
- Évolution facile du schéma sans casser les clients existants.
- Documentation automatique via le schéma.



