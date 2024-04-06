package org.dummy.world.peopleservice.repository;

import org.dummy.world.peopleservice.model.City;

import java.util.List;

public class InMemoryEuropeRepository implements EuropeRepository {
    @Override
    public List<City> findAll() {
        return List.of(
                new City("Brussels", 1_209_000L),
                new City("Dendermonde", 45_800L));

    }
}
