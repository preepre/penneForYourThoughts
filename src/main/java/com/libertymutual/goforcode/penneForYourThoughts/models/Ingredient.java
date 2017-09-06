package com.libertymutual.goforcode.penneForYourThoughts.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		generator=ObjectIdGenerators.PropertyGenerator.class,
		property="id"
	)

@Entity
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