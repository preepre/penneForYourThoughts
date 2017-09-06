package com.libertymutual.goforcode.penneForYourThoughts.models;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
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

	// GETTERS AND SETTERS
	
	@ManyToOne
	private Recipe recipe;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
}
