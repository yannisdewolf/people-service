package org.dummy.world.peopleservice.repository.database.america;

import org.dummy.world.peopleservice.model.City;
import org.dummy.world.peopleservice.repository.AmericaRepository;
import org.dummy.world.peopleservice.repository.EuropeRepository;
import org.springframework.data.repository.Repository;

public interface AmericaCityRepository extends Repository<City, Integer>, AmericaRepository {
}
