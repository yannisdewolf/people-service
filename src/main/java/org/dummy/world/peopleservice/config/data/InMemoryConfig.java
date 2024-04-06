package org.dummy.world.peopleservice.config.data;

import org.dummy.world.peopleservice.repository.AmericaRepository;
import org.dummy.world.peopleservice.repository.EuropeRepository;
import org.dummy.world.peopleservice.repository.InMemoryAmericaRepository;
import org.dummy.world.peopleservice.repository.InMemoryEuropeRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "peopleservice.data.source", havingValue = "INMEMORY", matchIfMissing = true)
public class InMemoryConfig {

    @Bean
    public AmericaRepository americaRepository() {
        return new InMemoryAmericaRepository();
    }

    @Bean
    public EuropeRepository europeRepository() {
        return new InMemoryEuropeRepository();
    }

}
