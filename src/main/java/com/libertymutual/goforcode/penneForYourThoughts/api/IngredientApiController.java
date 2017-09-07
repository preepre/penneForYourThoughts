package com.libertymutual.goforcode.penneForYourThoughts.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.penneForYourThoughts.models.Ingredient;
import com.libertymutual.goforcode.penneForYourThoughts.models.Recipe;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.IngredientRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.InstructionRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.RecipeRepository;


@RestController
@RequestMapping("/api/recipes/{id}/ingredients")
public class IngredientApiController {

	private RecipeRepository recipeRepo;
	private IngredientRepository ingredientRepo;
	private InstructionRepository instructionRepo;

	public IngredientApiController(RecipeRepository recipeRepo, IngredientRepository ingredientRepo,
			InstructionRepository instructionRepo) {

		this.recipeRepo = recipeRepo;
		this.ingredientRepo = ingredientRepo;
		this.instructionRepo = instructionRepo;
		
	}
	
	@PostMapping("")
	public Recipe createIngredientForARecipe(@PathVariable long id, @RequestBody Ingredient ingredient) {
		Recipe recipe = recipeRepo.findOne(id);
		ingredient.setRecipe(recipe);
		ingredient = ingredientRepo.save(ingredient);
		recipe.addIngredient(ingredient);
		return recipe;
	}
	
	
}
