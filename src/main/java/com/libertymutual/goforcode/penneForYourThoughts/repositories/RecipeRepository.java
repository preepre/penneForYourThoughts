package com.libertymutual.goforcode.penneForYourThoughts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.penneForYourThoughts.models.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	List<Recipe> findByTitleContaining(String partialTitle);
}
