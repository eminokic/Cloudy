package cs.weather.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer id;
	public String city;
	public String state;
	
	public Location() {}
	
	public Location(String city, String state) {
		this.city = city;
		this.state = state;
		init();
	}
	
	private void init() {
	}
	
	public String toString() {
		return "City: " + city + 
				"\nState: " + state;
	}
}
