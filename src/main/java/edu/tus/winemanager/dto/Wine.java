package edu.tus.winemanager.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wine {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String name;
	private int year;
	private String grapes;
	private String country;

	public Wine(String name, int year, String grapes, String country) {
		this.country = country;
		this.year = year;
		this.grapes = grapes;
		this.name = name;
	}

	public Wine(){

	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrapes() {
		return grapes;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setGrapes(String grapes) {
		this.grapes = grapes;
	}

}
