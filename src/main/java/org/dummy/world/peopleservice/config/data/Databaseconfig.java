package org.dummy.world.peopleservice.config.data;

import org.dummy.world.peopleservice.config.data.database.AmericaDataSourceConfiguration;
import org.dummy.world.peopleservice.config.data.database.EuropeDataSourceConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(value = "peopleservice.data.source", havingValue = "DATABASE")
@Import({
        EuropeDataSourceConfiguration.class,
        AmericaDataSourceConfiguration.class})
public class Databaseconfig {
}
