package com.libertymutual.goforcode.penneForYourThoughts.api;

import java.util.HashSet;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.penneForYourThoughts.models.Recipe;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.IngredientRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.InstructionRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.RecipeRepository;

@RestController
@RequestMapping("/api/recipes")
// @Api(description="Use this to get and create recipes")
public class RecipeApiController {

	private RecipeRepository recipeRepo;
	private IngredientRepository ingredientRepo;
	private InstructionRepository instructionRepo;

	public RecipeApiController(RecipeRepository recipeRepo, IngredientRepository ingredientRepo,
			InstructionRepository instructionRepo) {

		this.recipeRepo = recipeRepo;
		this.ingredientRepo = ingredientRepo;
		this.instructionRepo = instructionRepo;
		
		recipeRepo.save(new Recipe("lasagna", "A layered pasta with tomato sauce", 74));
		recipeRepo.save(new Recipe("frozen pesto pizza", "A pizza with pesto sauce", 9));
		

	}

	@GetMapping("")
	public List<Recipe> getAll() {
		return recipeRepo.findAll();

	}
	
	

}
