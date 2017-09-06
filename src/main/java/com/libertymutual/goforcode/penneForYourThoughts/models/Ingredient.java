package com.libertymutual.goforcode.penneForYourThoughts.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Ingredient {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	@Column(length=200, nullable=false)
	private String foodItem;
	
	@Column (length=75, nullable=false)
	private String unitOfMeasure;
	
	//@JsonIgnore
	//private Set<Movie> movies;
	
	@Column (length=10)
	private double quantity;
	
}