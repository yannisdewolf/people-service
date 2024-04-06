package org.dummy.world.peopleservice.config.data.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class AmericaDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.america")
    public DataSourceProperties americaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource americaDataSource() {
        return americaDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public JdbcTemplate americaJdbcTemplate(@Qualifier("americaDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
