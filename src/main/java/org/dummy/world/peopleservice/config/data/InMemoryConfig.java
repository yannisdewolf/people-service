package org.dummy.world.peopleservice.config.data;

import org.dummy.world.peopleservice.repository.AmericaRepository;
import org.dummy.world.peopleservice.repository.EuropeRepository;
import org.dummy.world.peopleservice.repository.inmemory.InMemoryAmericaRepository;
import org.dummy.world.peopleservice.repository.inmemory.InMemoryEuropeRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "peopleservice.data.source", havingValue = "INMEMORY", matchIfMissing = true)
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class
})
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
