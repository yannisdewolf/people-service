package org.dummy.world.peopleservice.service;

import org.dummy.world.peopleservice.model.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EuropeService implements CityService {

    @Override
    public List<City> findAll() {
        return List.of(new City("Brussels", 1_209_000L),
                new City("Dendermonde", 45_800L));
    }
}
