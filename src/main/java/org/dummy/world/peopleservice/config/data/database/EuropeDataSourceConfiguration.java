package org.dummy.world.peopleservice.config.data.database;

import org.dummy.world.peopleservice.model.City;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "org.dummy.world.peopleservice.repository.database.europe",
        entityManagerFactoryRef = "europeEntityManagerFactory",
        transactionManagerRef = "europeTransactionManager"
)
public class EuropeDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("peopleservice.datasource.europe")
    public DataSourceProperties europeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource europeDataSource(DataSourceProperties europeDataSourceProperties) {
        return europeDataSourceProperties
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public JdbcTemplate europeJdbcTemplate(@Qualifier("europeDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean europeEntityManagerFactory(
            @Qualifier("europeDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .properties(Map.of("hibernate.default_schema", "europe"))
                .packages(City.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager europeTransactionManager(
            @Qualifier("europeEntityManagerFactory") LocalContainerEntityManagerFactoryBean europeEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(europeEntityManagerFactory.getObject()));
    }

}
