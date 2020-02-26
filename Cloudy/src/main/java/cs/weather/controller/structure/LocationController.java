package cs.weather.controller.structure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cs.weather.model.Location;
import cs.weather.repository.LocationRepository;

@RestController
public class LocationController {

	@Autowired
	LocationRepository locationRepo;

	
	
	@GetMapping(path = "/loc/get/1")
	public @ResponseBody List<Location> loc1() {
		try {
			return (List<Location>) locationRepo.findAll();
		} catch (Exception ignore) {
			System.out.println("Error Thrown\n");
			return null;
		}
	}

	@PostMapping(path = "/loc/post/2/{city}/{state}")
	public @ResponseBody Location loc2(@PathVariable("city") String city, @PathVariable("state") String state) {
		try {
			if (locationRepo.findByCityAndState(city, state).isPresent()) return null;
			return locationRepo.save(new Location(city, state));
		} catch (Exception ignore) {
			System.out.println("Error Thrown\n");
			return null;
		}
	}
	
	@GetMapping(path = "/loc/get/3/{id}")
	public @ResponseBody Location loc3(@PathVariable("id") Integer id) {
		try {
			return locationRepo.findById(id).get();
		} catch (Exception ignore) {
			System.out.println("Error Thrown\n");
			return null;
		}
	}
}
