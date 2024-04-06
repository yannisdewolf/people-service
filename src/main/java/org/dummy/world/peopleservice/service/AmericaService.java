package org.dummy.world.peopleservice.service;


import org.dummy.world.peopleservice.model.City;
import org.dummy.world.peopleservice.repository.AmericaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmericaService implements CityService {

    private final AmericaRepository americaRepository;

    public AmericaService(AmericaRepository americaRepository) {
        this.americaRepository = americaRepository;
    }

    @Override
    public List<City> findAll() {
        return americaRepository.findAll();
    }
}
