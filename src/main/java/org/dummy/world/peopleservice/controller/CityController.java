package org.dummy.world.peopleservice.controller;

import org.dummy.world.peopleservice.model.City;
import org.dummy.world.peopleservice.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final List<CityService> cityServices;

    public CityController(List<CityService> cityServices) {
        this.cityServices = cityServices;
    }

    @GetMapping
    public List<City> findAll() {
        return cityServices.stream()
                .flatMap(s -> s.findAll().stream())
                .toList();
    }

}
