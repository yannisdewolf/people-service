package org.dummy.world.peopleservice.config.features;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;

import javax.sql.DataSource;

@Configuration
public class FeatureConfiguration {

    @Bean
    @ConditionalOnProperty(value = "peopleservice.data.source", havingValue = "DATABASE")
    public StateRepository stateRepository(DataSource dataSource) {
        return new JDBCStateRepository(dataSource, "europe.togglz");
    }

//    @Bean
//    public FeatureProvider featureProvider() {
//        return new EnumBasedFeatureProvider(PeopleFeatures.class);
//    }

//    @Bean
//    public UserProvider userProvider() {
//        return new NoOpUserProvider();
//    }

}
