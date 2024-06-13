package org.dummy.world.peopleservice.controller;

import org.dummy.world.peopleservice.config.features.PeopleFeatures;
import org.dummy.world.peopleservice.model.City;
import org.dummy.world.peopleservice.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final List<CityService> cityServices;

    private final FeatureManager featureManager;

    public CityController(List<CityService> cityServices, FeatureManager featureManager) {
        this.cityServices = cityServices;
        this.featureManager = featureManager;
    }

    @GetMapping
    public List<City> findAll() {
        if (featureManager.isActive(PeopleFeatures.LOGGING)) {
            System.out.println("called findall");
        }

        return cityServices.stream()
                .flatMap(s -> s.findAll().stream())
                .toList();
    }

}
