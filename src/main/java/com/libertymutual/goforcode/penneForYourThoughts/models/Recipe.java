package com.libertymutual.goforcode.penneForYourThoughts.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@JsonIdentityInfo(
		generator=ObjectIdGenerators.PropertyGenerator.class,
		property="id"
	)

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@Column(length=100, nullable=false)
	private String title;
	
	@Column(length=250, nullable=false)
	private String description;
	
	@Column(length=50, nullable=false)
	private int minutes;
	
	//need mappings
	@OneToMany(mappedBy="recipe")
	private Set<Ingredient> ingredients;
	
	@OneToMany(mappedBy="recipe")
	private Set<Instruction> instructions;
	
	public Recipe() {}
	
	public Recipe(String title, String description, int minutes) {
		this.title = title;
		this.description = description;
		this.minutes = minutes;
	}
	
	public void addInstruction(Instruction instruction) {
		if(instructions == null) {
			instructions = new HashSet<Instruction>();
		}
		instructions.add(instruction);
	}

	public void addIngredient(Ingredient ingredient) {
		if(ingredients == null) {
			ingredients = new HashSet<Ingredient>();
		}
		ingredients.add(ingredient);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(Set<Instruction> instructions) {
		this.instructions = instructions;
	}
	

}
