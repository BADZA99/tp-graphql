# TPGraphQL

API de gestion de livres avec Spring Boot, GraphQL et MySQL.

## Fonctionnalités
- CRUD complet sur les livres (Book)
- API GraphQL (requêtes et mutations)
- Persistance MySQL via Spring Data JPA

## Structure du projet
- `src/main/java/com/example/tpgraphql/` : code source principal
  - `domain/Book.java` : entité JPA
  - `repository/BookRepository.java` : accès base
  - `service/BookService.java` : logique métier
  - `resolver/BookResolver.java` : endpoints GraphQL
  - `TpgraphqlApplication.java` : point d’entrée
- `src/main/resources/`
  - `application.properties` : configuration
  - `graphql/schema.graphqls` : schéma GraphQL
- `src/test/java/com/example/tpgraphql/` : tests

## Démarrage rapide
1. Cloner le repo
2. Configurer la base MySQL dans `application.properties`
3. Lancer :
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Accéder à l’interface GraphQL :
   - http://localhost:8080/graphiql

## Exemples de requêtes
```graphql
# Récupérer tous les livres
query {
  books {
    id
    title
    author
    publishedDate
    price
  }
}

# Ajouter un livre
mutation {
  createBook(input: {
    title: "Nouveau livre"
    author: "Auteur"
    publishedDate: "2023-01-01"
    price: 19.99
  }) {
    id
    title
  }
}
```

## Documentation
- Voir `GRAPHQL_DOCUMENTATION.md` pour plus de détails sur GraphQL et l’implémentation.
- Voir `STRUCTURE_PROJET.md` pour la structure détaillée du projet.

---
© 2025 - TPGraphQL- PAPA BN 