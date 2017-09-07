package com.libertymutual.goforcode.penneForYourThoughts.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.penneForYourThoughts.models.Ingredient;
import com.libertymutual.goforcode.penneForYourThoughts.models.Instruction;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.IngredientRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.InstructionRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.RecipeRepository;

@RestController
@RequestMapping("/recipes/{id}/instructions")
public class InstructionApiController {

	private RecipeRepository recipeRepo;
	private IngredientRepository ingredientRepo;
	private InstructionRepository instructionRepo;

	public InstructionApiController(RecipeRepository recipeRepo, IngredientRepository ingredientRepo,
			InstructionRepository instructionRepo) {

		this.recipeRepo = recipeRepo;
		this.ingredientRepo = ingredientRepo;
		this.instructionRepo = instructionRepo;
		

	}
	
	@PostMapping("") // requestbody will turn the json into that object
	public Instruction create(@PathVariable Instruction instruction) {
		return instructionRepo.save(instruction);
	}
}
