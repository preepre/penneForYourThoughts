package com.liberymutual.goforcode.penneForYourThoughts.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.penneForYourThoughts.api.RecipeApiController;
import com.libertymutual.goforcode.penneForYourThoughts.models.Recipe;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.IngredientRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.InstructionRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.RecipeRepository;


public class RecipeApiControllerTests {

	
	private RecipeRepository recipeRepo;
	private IngredientRepository ingredientRepo;
	private InstructionRepository instructionRepo;
	private RecipeApiController controller;

	@Before
	public void setUp() {
		recipeRepo = mock(RecipeRepository.class);
		ingredientRepo = mock(IngredientRepository.class);
		instructionRepo = mock(InstructionRepository.class);
		controller = new RecipeApiController(recipeRepo, ingredientRepo, instructionRepo);
	}
	
	@Test
	public void test_that_recipe_gets_created_and_added_to_repo() {
		//arange
		Recipe tea = new Recipe();
		when(recipeRepo.save(tea)).thenReturn(tea);
		
		//act
		Recipe actual = controller.create(tea);
		
		//assert
		assertThat(actual).isSameAs(tea);
		verify(recipeRepo).save(tea);
	}
}
