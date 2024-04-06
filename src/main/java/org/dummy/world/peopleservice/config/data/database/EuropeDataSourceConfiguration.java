package org.dummy.world.peopleservice.config.data.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class EuropeDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.europe")
    public DataSourceProperties europeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource europeDataSource() {
        return europeDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public JdbcTemplate europeJdbcTemplate(@Qualifier("europeDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
