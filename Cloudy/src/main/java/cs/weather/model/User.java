package cs.weather.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer id;
	public String username;
	public String password;
	public String name;
	public String email;
	public String birthday;
	@ManyToOne
	public Location starred;
	@OneToMany
	public List<Location> current;
	@OneToMany
	public List<Location> locations;
	
	User() {}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		init();
	}
	
	private void init() {
		this.locations = new ArrayList<Location>();
		// this.current = new ArrayList<Location>();
	}
	
	public String toString() {
		return "Username: " + username +
				"\nPassword: " + password +
				"\nEmail: " + email
				;
	}
}
