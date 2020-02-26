package cs.weather.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cs.weather.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	Optional<User> findById(Integer id);
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Optional<User> findByUsernameAndPassword(String username, String password);
	// List<User> findByStarredOrLocationsContains(Location search);
	List<User> findByName(String name);
}
