package org.dummy.world.peopleservice;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
        , properties = "peopleservice.data.source=DATABASE"
)
public class CitiesDatabaseIntegrationTest {

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
