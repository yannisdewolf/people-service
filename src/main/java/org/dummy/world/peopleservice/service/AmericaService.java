package org.dummy.world.peopleservice.service;


import org.dummy.world.peopleservice.model.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmericaService implements CityService {

    @Override
    public List<City> findAll() {
        return List.of(
                new City("Austin", 974_000L),
                new City("New York", 8_260_000L),
                new City("Washington", 671_000L)
        );

    }
}
