package org.dummy.world.peopleservice;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.web.client.LocalHostUriTemplateHandler;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public abstract class AbstractIntegrationTest {

    @Autowired
    @Qualifier("authenticatedRestTemplateBuilder")
    private RestTemplateBuilder readAllowedRestTemplateBuilder;

    @Autowired
    private Environment environment;

    protected TestRestTemplate testRestTemplate;

    @BeforeEach
    void setupRestTemplate() {
        testRestTemplate = new TestRestTemplate(readAllowedRestTemplateBuilder);
        testRestTemplate.setUriTemplateHandler(new LocalHostUriTemplateHandler(environment));
    }

    protected ResponseEntity<String> doGet(String url) {
        return testRestTemplate
                .withBasicAuth("pepe", "ronny")
                .exchange(url, HttpMethod.GET, null, String.class);
    }

}
