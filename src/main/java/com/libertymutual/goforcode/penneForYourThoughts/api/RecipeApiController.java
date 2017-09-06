package com.libertymutual.goforcode.penneForYourThoughts.api;

import java.util.HashSet;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.penneForYourThoughts.models.Recipe;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.RecipeRepository;

@RestController
@RequestMapping("/api/recipes")
// @Api(description="Use this to get and create recipes")
public class RecipeApiController {

	private RecipeRepository recipeRepo;
	// private IngredientRepository ingredientRepo;
	// private InstructionRepository instructionRepo;

	public RecipeApiController(RecipeRepository recipeRepo) {

		this.recipeRepo = recipeRepo;
		// this.

	}

	@GetMapping("")
	public List<Recipe> getAll() {
		return recipeRepo.findAll();

	}
	
	

}
