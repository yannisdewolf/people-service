package org.dummy.world.peopleservice.repository.inmemory;

import org.dummy.world.peopleservice.model.City;
import org.dummy.world.peopleservice.repository.AmericaRepository;

import java.util.List;

public class InMemoryAmericaRepository implements AmericaRepository {
    @Override
    public List<City> findAll() {
        return List.of(
                new City("Austin", 974_000L),
                new City("New York", 8_260_000L),
                new City("Washington", 671_000L)
        );
    }
}
