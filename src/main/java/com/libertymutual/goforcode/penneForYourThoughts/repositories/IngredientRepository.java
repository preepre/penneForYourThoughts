package com.libertymutual.goforcode.penneForYourThoughts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libertymutual.goforcode.penneForYourThoughts.models.Ingredient;


public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
