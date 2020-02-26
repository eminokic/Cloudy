package cs.weather.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cs.weather.model.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

	Optional<Location> findById(Integer id);
	Optional<Location> findByCity(String city);
	Optional<Location> findByCityAndState(String city, String state);
	Optional<List<Location>> findByState(String state);
}
