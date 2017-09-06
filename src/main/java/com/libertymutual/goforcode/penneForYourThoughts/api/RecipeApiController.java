package com.libertymutual.goforcode.penneForYourThoughts.api;

import java.util.HashSet;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PathVariable;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> 95102763c654831a474102a61635bfbd08c91e20
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

	// Get all recipes
	@GetMapping("")
	public List<Recipe> getAll() {
		return recipeRepo.findAll();

	}
<<<<<<< HEAD

	// Get recipe by id
	@GetMapping("{id}")
	public Recipe getOne(@PathVariable long id) throws RecipeNotFoundException {
		Recipe recipe = recipeRepo.findOne(id);
		if (recipe == null) {
			throw new RecipeNotFoundException();
		}
		
		return recipe;

	}
=======
	
	@PostMapping("") // requestbody will turn the json into that object
	public Recipe create(@RequestBody Recipe recipe) {
		return recipeRepo.save(recipe);
	}
	
>>>>>>> 95102763c654831a474102a61635bfbd08c91e20

}
