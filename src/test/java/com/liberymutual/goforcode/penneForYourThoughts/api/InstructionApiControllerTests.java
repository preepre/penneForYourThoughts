package com.liberymutual.goforcode.penneForYourThoughts.api;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import com.libertymutual.goforcode.penneForYourThoughts.models.Recipe;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.InstructionRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.RecipeRepository;
import com.libertymutual.goforcode.penneForYourThoughts.api.InstructionApiController;
import com.libertymutual.goforcode.penneForYourThoughts.api.RecipeNotFoundException;
import com.libertymutual.goforcode.penneForYourThoughts.models.Ingredient;
import com.libertymutual.goforcode.penneForYourThoughts.models.Instruction;

public class InstructionApiControllerTests {

	private RecipeRepository recipeRepo;
	private InstructionRepository instructionRepo;
	private InstructionApiController controller;
	
	@Before
	public void setUp() {
		recipeRepo = mock(RecipeRepository.class);
		instructionRepo = mock(InstructionRepository.class);
		controller = new InstructionApiController(recipeRepo, instructionRepo);
	}
	
	@Test
	public void test_instruction_gets_created_and_added_to_repo() {
		Instruction instruction = new Instruction();
		Recipe recipe = new Recipe();
		when(instructionRepo.save(instruction)).thenReturn(instruction);
		when(recipeRepo.findOne(1l)).thenReturn(recipe);
		
		Recipe actual = controller.createInstructionForARecipe(1l, instruction);
		
		assertThat(recipe).isSameAs(actual);
		
	}
	
	@Test
	public void test_ingredient_gets_deleted_from_repo() {
		Instruction instruction = new Instruction();
		when(instructionRepo.findOne(2l)).thenReturn(instruction);
		
		Instruction actual = controller.delete(2l);
		
		assertThat(instruction).isSameAs(actual);
		verify(instructionRepo).delete(2l);
		verify(instructionRepo).findOne(2l);
		
	}
	
	@Test
	public void test_that_null_is_returned_when_delete_throws_EmptyResultDataAccess() throws RecipeNotFoundException {
		//arrange
		when(instructionRepo.findOne(8l)).thenThrow(new EmptyResultDataAccessException(0));
		
		//act
		Instruction actual = controller.delete(8l);
		
		//assert
		assertThat(actual).isNull();
		verify(instructionRepo).findOne(8l);
	}
	
	@Test
	public void test_instruction_gets_updated_from_repo() {
		//arrange
		Instruction instruction = new Instruction();
		Recipe recipe = new Recipe();
		when(instructionRepo.save(instruction)).thenReturn(instruction);
		when(recipeRepo.findOne(1l)).thenReturn(recipe);
		Recipe actual = controller.createInstructionForARecipe(1l, instruction);

		//act
		Instruction actualInstruction = controller.updateInstruction(instruction, 99l, 1l);
		
		//assert
		assertThat(actualInstruction.getId()).isEqualTo(99l);
	}
	
}
