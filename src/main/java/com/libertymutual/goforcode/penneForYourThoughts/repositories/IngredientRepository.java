package com.libertymutual.goforcode.penneForYourThoughts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.penneForYourThoughts.models.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
