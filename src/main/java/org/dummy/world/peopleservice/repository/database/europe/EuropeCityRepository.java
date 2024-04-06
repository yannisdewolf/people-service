package org.dummy.world.peopleservice.repository.database.europe;

import org.dummy.world.peopleservice.model.City;
import org.dummy.world.peopleservice.repository.EuropeRepository;
import org.springframework.data.repository.Repository;

public interface EuropeCityRepository extends Repository<City, Integer>, EuropeRepository {
}
