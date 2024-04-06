package org.dummy.world.peopleservice;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
        , properties = "peopleservice.data.source=DATABASE"
)
@ContextConfiguration(initializers = CitiesDatabaseIntegrationTest.Initializer.class)
public class CitiesDatabaseIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> europeContainer = new PostgreSQLContainer<>("postgres:16.2-alpine")
            .withDatabaseName("europe")
            .withUsername("sa")
            .withPassword("sa")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource(
                            "europe.sql"), "/docker-entrypoint-initdb.d/"
            );
            ;

    @Container
    public static PostgreSQLContainer<?> americaContainer = new PostgreSQLContainer<>("postgres:16.2-alpine")
            .withDatabaseName("america")
            .withUsername("sa")
            .withPassword("sa")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource(
                            "america.sql"), "/docker-entrypoint-initdb.d/"
            );

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.europe.url=" + europeContainer.getJdbcUrl(),
                    "spring.datasource.europe.username=" + europeContainer.getUsername(),
                    "spring.datasource.europe.password=" + europeContainer.getPassword(),
                    "spring.datasource.america.url=" + americaContainer.getJdbcUrl(),
                    "spring.datasource.america.username=" + americaContainer.getUsername(),
                    "spring.datasource.america.password=" + americaContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getCities() throws Exception {
        // Given

        // When
        String actual = restTemplate.getForObject("/cities", String.class);

        System.out.println(actual);
        // Then
        JSONAssert.assertEquals("""
                                                [
                                                {"name":"Brussels","amount":1209000},
                                                {"name":"Dendermonde","amount":45800},
                                                {"name":"Austin","amount":974000},
                                                {"name":"New York","amount":8260000},
                                                {"name":"Washington","amount":671000}]
                        """
                , actual, false);
    }

}
