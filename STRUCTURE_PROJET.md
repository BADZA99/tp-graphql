# Structure et rôle des dossiers/fichiers du projet

## Racine du projet
- **pom.xml** : Fichier de configuration Maven (dépendances, plugins, build).
- **mvnw / mvnw.cmd / .mvn/** : Wrapper Maven pour garantir la version de Maven utilisée.
- **HELP.md** : Documentation d’aide générée par Spring Initializr.
- **GRAPHQL_DOCUMENTATION.md** : Documentation sur GraphQL et son intégration dans ce projet.
- **testApi.http** : Fichier de requêtes HTTP pour tester l’API GraphQL (exemples de query/mutation).

## src/main/java/com/example/tpgraphql/
- **TpgraphqlApplication.java** : Classe principale Spring Boot (point d’entrée de l’application).
- **domain/Book.java** : Entité JPA représentant la table `book` (modèle de données).
- **repository/BookRepository.java** : Interface Spring Data JPA pour accéder à la table `book` (CRUD).
- **service/BookService.java** : Logique métier pour manipuler les livres (utilise le repository).
- **resolver/BookResolver.java** : Resolvers GraphQL (Query/Mutation) qui exposent les opérations à l’API.

## src/main/resources/
- **application.properties** : Configuration de l’application (base de données, JPA, etc.).
- **graphql/schema.graphqls** : Schéma GraphQL (types, queries, mutations, inputs).
- **data.sql** : (optionnel) Script SQL pour insérer des données au démarrage (non utilisé ici avec MySQL/JPA).
- **schema.sql** : (optionnel) Script SQL pour créer le schéma (non utilisé ici avec JPA).
- **static/** et **templates/** : Dossiers réservés pour les fichiers statiques (HTML, CSS, JS) et templates (Thymeleaf, etc.) si besoin.

## src/test/java/com/example/tpgraphql/
- **TpgraphqlApplicationTests.java** : Test d’intégration principal pour vérifier le chargement du contexte Spring Boot.

## target/
- **/** : Dossier généré lors du build Maven (classes compilées, jar, rapports de test, etc.).

---

## Récapitulatif du flux
1. **Le client** envoie une requête GraphQL à `/graphql` (exemple dans `testApi.http`).
2. **BookResolver** reçoit la requête, appelle **BookService**.
3. **BookService** utilise **BookRepository** pour accéder à la base MySQL via l’entité **Book**.
4. **Le résultat** est renvoyé au client, formaté selon le schéma GraphQL.

Chaque dossier/fichier a un rôle précis pour séparer la configuration, la logique métier, l’accès aux données, l’exposition de l’API et la documentation/test.
