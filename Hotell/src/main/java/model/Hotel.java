package model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

	private String name;
	private String city;
	private int stars;
	private String description;
	private String image;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Hotel() {}
	
	public Hotel(String name, String city, int stars, String description, String image, int id) {
		super();
		this.name = name;
		this.city = city;
		this.stars = stars;
		this.description = description;
		this.image = image;
		this.id = id;
	}

	public Hotel(String name, String city, int stars, String description, String image) {
		super();
		this.name = name;
		this.city = city;
		this.stars = stars;
		this.description = description;
		this.image = image;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	private List<Roomtype> roomTypes = new ArrayList<>();

	public void addRoomType(Roomtype roomType) {
	    this.roomTypes.add(roomType);
	}

	public List<Roomtype> getRoomTypes() {
	    return roomTypes;
	}

	
	
	
 
	
	

}
