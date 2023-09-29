package com.example.obspringdatajpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Coche {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String manufacturer;
	private String model;
	private Integer year;
	
	public Coche() {
		
	}
	
	public Coche(Long id, String manufacturer, String model, Integer year) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Coche [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + ", year=" + year + "]";
	}
	
	
	
	
	
	

}
