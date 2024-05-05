package org.dummy.world.peopleservice;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
        , properties = "peopleservice.data.source=INMEMORY"
)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class CitiesInMemoryIntegrationTest extends AbstractIntegrationTest {

    @Test
    void getCities() throws Exception {
        // Given

        // When
        ResponseEntity<String> response = doGet("/cities");

        System.out.println(response.getBody());
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        });

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

//    protected ResponseEntity<String> doGet(String url) {
//        return testRestTemplate
//                .exchange(url, HttpMethod.GET, null, String.class);
//    }

}
