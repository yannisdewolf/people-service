# People service

Service with 2 datasources. One for america, one for europe.

### Build
`./gradlew clean build`

### Datasources

Configure which datasource the service uses by adapting the property
`peopleservice.data.source`

When no value is set for this property, it defaults to `INMEMORY`

Possible values:

| Property | Description                              |
|----------|------------------------------------------|
| INMEMORY | uses the `InMemoryxxxRepository` classes |
| DATABASE |                                          |

### Connect to databases

Use docker-compose.yml to start the databases and adminer

#### Adminer

Explore the data with adminer: http://localhost:8080

| Url                                       | username | password | database |
|-------------------------------------------|----------|----------|----------|
| jdbc:postgresql://localhost:15432/europe  | europe   | europe   | europe   |
| jdbc:postgresql://localhost:25432/america | america  | america  | america  |

Setup of postgresql: see https://hub.docker.com/_/postgres

