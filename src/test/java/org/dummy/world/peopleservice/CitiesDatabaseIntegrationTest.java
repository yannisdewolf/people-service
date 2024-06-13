package org.dummy.world.peopleservice;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
        , properties = "peopleservice.data.source=DATABASE"
)
@ContextConfiguration(initializers =
        CitiesDatabaseIntegrationTest.Initializer.class,
        classes = IntegrationTestConfiguration.class)
public class CitiesDatabaseIntegrationTest extends AbstractIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> europeContainer = new PostgreSQLContainer<>("postgres:16.2-alpine")
            .withCopyFileToContainer(MountableFile.forClasspathResource("create_schema_europe.sql"),
                    "/docker-entrypoint-initdb.d/00_init-schema.sql")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource(
                            "create_city_europe.sql"), "/docker-entrypoint-initdb.d/01_create_table.sql"
            )
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource(
                            "europe.sql"), "/docker-entrypoint-initdb.d/02_europe.sql"
            );
            ;

    @Container
    public static PostgreSQLContainer<?> americaContainer = new PostgreSQLContainer<>("postgres:16.2-alpine")
            .withDatabaseName("america")
            .withUsername("sa")
            .withPassword("sa")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource(
                            "create_city.sql"), "/docker-entrypoint-initdb.d/01_create_table.sql"
            )
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource(
                            "america.sql"), "/docker-entrypoint-initdb.d/02_america.sql"
            );

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            String password = "pw1";
            String username = "europe_user";
            System.out.println(username);
            System.out.println(password);
            TestPropertyValues.of(
                    "peopleservice.datasource.europe.url=" + europeContainer.getJdbcUrl(),
                    "peopleservice.datasource.europe.username=" + username,
                    "peopleservice.datasource.europe.password=" + password,
                    "peopleservice.datasource.america.url=" + americaContainer.getJdbcUrl(),
                    "peopleservice.datasource.america.username=" + americaContainer.getUsername(),
                    "peopleservice.datasource.america.password=" + americaContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    void getCities() throws Exception {
        // Given

        // When
        ResponseEntity<String> response = doGet("/cities");

        // Then
        JSONAssert.assertEquals("""
                                                [
                                                {"name":"Brussels","amount":1209000},
                                                {"name":"Dendermonde","amount":45800},
                                                {"name":"Austin","amount":974000},
                                                {"name":"New York","amount":8260000},
                                                {"name":"Washington","amount":671000}]
                        """
                , response.getBody(), false);
    }

}
