package cs.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cs.weather.Core;
import cs.weather.model.User;
import cs.weather.repository.LocationRepository;
import cs.weather.repository.UserRepository;

@RestController
public class InitController {

	
	/*
	 Connecting to server: ssh jtloftus@coms-319-084.cs.iastate.edu
	 Connecting to SQL: mysql -u team30 -p ---> team30comsVM@319
	 Or... mysql -u root -p ---> comsVM@319
	 Database: weather
	 
	 
	 Request Naming Syntax: /'controller abrev'/'g or p'/'number'/...'vars'
	 CompanyController
	 1: (G) get all
	 2: (P) create (String name)
	 3: (G) get by (Integer id)
	 
	 CouponController
	 1: (G) get all
	 2: (P) create (String deal)
	 3: (G) get by (Integer id)
	 
	 LocationController
	 1: (G) get all
	 2: (P) create (String city, String state)
	 3: (G) get by (Integer id)
	 
	 UserController
	 1: (G) get all
	 2: (P) create user (String username, String password, String email)
	 3: (G) get by (Integer id)
	 4: (G) get by (String username, String password)
	 */
	
	@Autowired
	LocationRepository locationRepo;
	
	@Autowired
	UserRepository userRepo;
	
	
	
	@PostMapping(path = "/init/post/1")
	public @ResponseBody void ini1() {
		try {
			for (String name : Core.USERNAMES) {
				userRepo.save(new User(name, name, name + "@gmail.com"));
			}
			
		} catch (Exception ignore) {
			System.out.println("Error Thrown\n");
		}
	}
}
