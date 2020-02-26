package cs.weather.controller.structure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cs.weather.model.User;
import cs.weather.repository.UserRepository;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	UserRepository userRepo;

	
	
	// Get all users
	@GetMapping(path = "/use/get/1")
	public @ResponseBody List<User> use1() {
		try {
			return (List<User>) userRepo.findAll();
		} catch (Exception ignore) {
			System.out.println("Error Thrown: " + ignore.getSuppressed() + "\n");
			return null;
		}
	}

	// Create new user
	@PostMapping(path = "/use/post/2/{username}/{password}/{email}")
	public @ResponseBody User use2(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("email") String email) {
		try {
			if (userRepo.findByUsername(username).isPresent() || userRepo.findByEmail(email).isPresent()) {
				System.out.println("username or email already in use...");
				return null;
			}
			System.out.println("username: " + username + " password: " + password + " email: " + email);
			return userRepo.save(new User(username, password, email));
		} catch (Exception ignore) {
			System.out.println("Error Thrown: " + ignore.getSuppressed() + "\n");
			return null;
		}
	}
	
	// Get user by id
	@GetMapping(path = "/use/get/3/{id}")
	public @ResponseBody User use3(@PathVariable("id") Integer id) {
		try {
			return userRepo.findById(id).get();
		} catch (Exception ignore) {
			System.out.println("Error Thrown\n");
			return null;
		}
	}
	
	// Sign in service
	@GetMapping(path = "/use/get/4/{username}/{password}")
	public @ResponseBody User use4(@PathVariable("username") String username, @PathVariable("password") String password) {
		try {
			return userRepo.findByUsernameAndPassword(username, password).get();
		} catch (Exception ignore) {
			System.out.println("\"username\" and \"password\" combination not found in database...\n");
			return null;
		}
	}
}
