# People service

Stuff in this service:
- 2 datasources. One for america, one for europe.
- togglz library
- [Bruno](https://www.usebruno.com) rest client requests in _bruno-requests_ directory
- tests with testcontainers
- basic Spring Security

### Build
`./gradlew clean build`

## Functional features

### List of cities
Retrieve the cities using the url http://localhost:8081/cities

Use the credentials found in the class `org.dummy.world.peopleservice.config.security.SecurityConfig`

## Technical Features

### Multiple Datasources

Configure which datasource the service uses by adapting the property
`peopleservice.data.source`

When no value is set for this property, it defaults to `INMEMORY`

Possible values:

| Property | Description                              |
|----------|------------------------------------------|
| INMEMORY | uses the `InMemoryxxxRepository` classes |
| DATABASE | uses Spring Data repositories            |

### Feature toggles
Access feature toggles using the url http://localhost:8081/togglz-console/index

Feature toggles are kept in memory when using INMEMORY, or in the primary datasource

### Connect to databases

Use docker-compose.yml to start the databases and adminer



#### Adminer

Explore the data with adminer: http://localhost:8080

| Url                                       | username | password | database |
|-------------------------------------------|----------|----------|----------|
| jdbc:postgresql://localhost:15432/europe  | europe   | europe   | europe   |
| jdbc:postgresql://localhost:25432/america | america  | america  | america  |

Setup of postgresql: see https://hub.docker.com/_/postgres


