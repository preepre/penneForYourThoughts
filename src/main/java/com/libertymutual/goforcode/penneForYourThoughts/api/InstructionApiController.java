package com.libertymutual.goforcode.penneForYourThoughts.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.libertymutual.goforcode.penneForYourThoughts.models.Instruction;
import com.libertymutual.goforcode.penneForYourThoughts.models.Recipe;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.InstructionRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.RecipeRepository;

@RestController
@RequestMapping("api/recipes/{id}/instructions")
public class InstructionApiController {

	private RecipeRepository recipeRepo;
	private InstructionRepository instructionRepo;

	public InstructionApiController(RecipeRepository recipeRepo,
			InstructionRepository instructionRepo) {

		this.recipeRepo = recipeRepo;
		this.instructionRepo = instructionRepo;
		

	}
	
	@PostMapping("")
	public Recipe createInstructionForARecipe(@PathVariable long id, @RequestBody Instruction instruction) {
		Recipe recipe = recipeRepo.findOne(id);
		instruction.setRecipe(recipe);
		
		instruction = instructionRepo.save(instruction);
		recipe.addInstruction(instruction);
		return recipe;
	}
	
	@DeleteMapping("{ins_id}")
	public Instruction delete(@PathVariable long ins_id) {
		try {
			Instruction step = instructionRepo.findOne(ins_id);
			instructionRepo.delete(ins_id);
			return step;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	} 
	
	
}
