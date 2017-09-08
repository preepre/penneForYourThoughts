package com.liberymutual.goforcode.penneForYourThoughts.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.penneForYourThoughts.api.RecipeApiController;
import com.libertymutual.goforcode.penneForYourThoughts.api.RecipeNotFoundException;
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
		//arrange
		Recipe tea = new Recipe();
		when(recipeRepo.save(tea)).thenReturn(tea);
		
		//act
		Recipe actual = controller.create(tea);
		
		//assert
		assertThat(actual).isSameAs(tea);
		verify(recipeRepo).save(tea);
	}
	
	@Test
	public void test_delete_returns_recipe_deleted_when_found() {
		//arrange
		Recipe bob = new Recipe();
		when(recipeRepo.findOne(3l)).thenReturn(bob);
		
		//act
		Recipe actual = controller.delete(3l);
		
		//assert
		assertThat(bob).isSameAs(actual);
		verify(recipeRepo).delete(3l);
		verify(recipeRepo).findOne(3l);
		
	}
	
	@Test
	public void test_that_null_is_returned_when_delete_throws_EmptyResultDataAccess() throws RecipeNotFoundException {
		//arrange
		when(recipeRepo.findOne(8l)).thenThrow(new EmptyResultDataAccessException(0));
		
		//act
		Recipe actual = controller.delete(8l);
		
		//assert
		assertThat(actual).isNull();
		verify(recipeRepo).findOne(8l);
	}
	
	@Test
	public void test_getOne_returns_one_recipe_returned_by_the_repo() throws RecipeNotFoundException {
		Recipe tea = new Recipe();
		when(recipeRepo.findOne(4l)).thenReturn(tea);
		
		Recipe actual = controller.getOne(4l);
		
		assertThat(actual).isSameAs(tea);
		verify(recipeRepo).findOne(4l);
	}
	
	@Test
	public void test_getOne_throws_StuffNotFoundException_when_no_recipe_returned_from_repo() {
		try {
			controller.getOne(1);
			fail("The controller didn't throw the StuffNotFoundException");
			
		} catch (RecipeNotFoundException rnfe) {
			
		}
	}
	
	@Test
	public void test_that_recipe_gets_updated_with_ID_and_added_to_repo() {
		//arange
		Recipe tea = new Recipe();
		tea.setId(99l);
		when(recipeRepo.save(tea)).thenReturn(tea);	
		
		Recipe actual = controller.create(tea);
		actual.setId(99l);
		
		//act
		actual = controller.update(tea, 100l);
		
		//assert
		assertThat(actual.getId()).isEqualTo(100);
	}
	
	
	@Test
	public void test_getAll_returns_all_recipes_returned_by_the_repo_if_no_partial_string() {
		// arrange
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(new Recipe());
		recipes.add(new Recipe());
		String test1 = null;
		
		when(recipeRepo.findAll()).thenReturn(recipes);
	
		//act
		List<Recipe> actual = controller.getAll(test1);
		
		//assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(recipes.get(0));
		verify(recipeRepo).findAll(); //verifying this method even got called
	}
	
	
	@Test
	public void test_getAll_returns_all_recipes_returned_by_the_repo_if_containing_partial_string() {
		// arrange
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(new Recipe("lasagna", "A layered pasta with tomato sauce", 74));
		recipes.add(new Recipe("lalala", "A layered pasta with tomato sauce", 77));
		String test1 = "la";
		
		when(recipeRepo.findByTitleContaining(test1)).thenReturn(recipes);
	
		//act
		List<Recipe> actual = controller.getAll(test1);
		
		//assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(recipes.get(0));
		verify(recipeRepo).findByTitleContaining(test1); //verifying this method even got called
		
	}
	
	
}
