package org.dummy.world.peopleservice.service;

import org.dummy.world.peopleservice.model.City;
import org.dummy.world.peopleservice.repository.EuropeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EuropeService implements CityService {

    private final EuropeRepository europeRepository;

    public EuropeService(EuropeRepository europeRepository) {
        this.europeRepository = europeRepository;
    }

    @Override
    public List<City> findAll() {
        return europeRepository.findAll();
    }
}
