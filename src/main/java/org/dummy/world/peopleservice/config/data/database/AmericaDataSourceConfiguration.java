package org.dummy.world.peopleservice.config.data.database;

import org.dummy.world.peopleservice.model.City;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "org.dummy.world.peopleservice.repository.database.america",
        entityManagerFactoryRef = "americaEntityManagerFactory",
        transactionManagerRef = "americaTransactionManager"
)
public class AmericaDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.america")
    public DataSourceProperties americaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource americaDataSource(DataSourceProperties americaDataSourceProperties) {
        return americaDataSourceProperties
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public JdbcTemplate americaJdbcTemplate(@Qualifier("americaDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean americaEntityManagerFactory(
            @Qualifier("americaDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(City.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager americaTransactionManager(
            @Qualifier("americaEntityManagerFactory") LocalContainerEntityManagerFactoryBean americaEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(americaEntityManagerFactory.getObject()));
    }

}
