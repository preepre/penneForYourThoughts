package com.libertymutual.goforcode.penneForYourThoughts.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.libertymutual.goforcode.penneForYourThoughts.models.Ingredient;
import com.libertymutual.goforcode.penneForYourThoughts.models.Recipe;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.IngredientRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.RecipeRepository;

@RestController
@RequestMapping("/api/recipes/{id}/ingredients")
public class IngredientApiController {

	private RecipeRepository recipeRepo;
	private IngredientRepository ingredientRepo;
	
	public IngredientApiController(RecipeRepository recipeRepo, IngredientRepository ingredientRepo) {
		this.recipeRepo = recipeRepo;
		this.ingredientRepo = ingredientRepo;
	}

	@PostMapping("")
	public Recipe createIngredientForARecipe(@PathVariable long id, @RequestBody Ingredient ingredient) {
		Recipe recipe = recipeRepo.findOne(id);
		ingredient.setRecipe(recipe);

		ingredient = ingredientRepo.save(ingredient);
		recipe.addIngredient(ingredient);
		return recipe;
	}

	// Get all ingredients for a recipe
	@GetMapping("")
	public List<Ingredient> getAll() {
		return ingredientRepo.findAll();
	}

	@DeleteMapping("{ing_id}")
	public Ingredient deleteIngredientForARecipe(@PathVariable long ing_id) {
		try {
			Ingredient ingredient = ingredientRepo.findOne(ing_id);
			ingredientRepo.delete(ing_id);
			return ingredient;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
}

