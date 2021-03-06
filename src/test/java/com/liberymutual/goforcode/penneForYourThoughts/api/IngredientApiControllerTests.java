package com.liberymutual.goforcode.penneForYourThoughts.api;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import com.libertymutual.goforcode.penneForYourThoughts.models.Recipe;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.IngredientRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.RecipeRepository;
import com.libertymutual.goforcode.penneForYourThoughts.api.IngredientApiController;
import com.libertymutual.goforcode.penneForYourThoughts.api.RecipeNotFoundException;
import com.libertymutual.goforcode.penneForYourThoughts.models.Ingredient;

public class IngredientApiControllerTests {

	private RecipeRepository recipeRepo;
	private IngredientRepository ingredientRepo;
	private IngredientApiController controller;
	
	@Before
	public void setUp() {
		recipeRepo = mock(RecipeRepository.class);
		ingredientRepo = mock(IngredientRepository.class);
		controller = new IngredientApiController(recipeRepo, ingredientRepo);
	}
	
	@Test
	public void test_ingredient_gets_created_and_added_to_repo() {
		//arrange
		Ingredient ingredient = new Ingredient();
		Recipe recipe = new Recipe();
		when(ingredientRepo.save(ingredient)).thenReturn(ingredient);
		when(recipeRepo.findOne(1l)).thenReturn(recipe);
		//act
		Recipe actual = controller.createIngredientForARecipe(1l, ingredient);
		//assert
		assertThat(recipe).isSameAs(actual);
		
	}
	
	@Test
	public void test_ingredient_gets_deleted_from_repo() {
		//arrange
		Ingredient ingredient = new Ingredient();
		when(ingredientRepo.findOne(2l)).thenReturn(ingredient);
		//act
		Ingredient actual = controller.deleteIngredientForARecipe(2l);
		//assert
		assertThat(ingredient).isSameAs(actual);
		verify(ingredientRepo).delete(2l);
		verify(ingredientRepo).findOne(2l);
		
	}
	
	@Test
	public void test_that_null_is_returned_when_delete_throws_EmptyResultDataAccess() throws RecipeNotFoundException {
		//arrange
		when(ingredientRepo.findOne(8l)).thenThrow(new EmptyResultDataAccessException(0));
		
		//act
		Ingredient actual = controller.deleteIngredientForARecipe(8l);
		
		//assert
		assertThat(actual).isNull();
		verify(ingredientRepo).findOne(8l);
	}
	
	@Test
	public void test_ingredient_gets_updated_from_repo() {
		//arrange
		Ingredient ingredient = new Ingredient();
		Recipe recipe = new Recipe();
		when(ingredientRepo.save(ingredient)).thenReturn(ingredient);
		when(recipeRepo.findOne(1l)).thenReturn(recipe);
		Recipe actual = controller.createIngredientForARecipe(1l, ingredient);

		//act
		Ingredient actualIngredient = controller.updateIngredient(ingredient, 99l, 1l);
		
		//assert
		assertThat(actualIngredient.getId()).isEqualTo(99l);
	}
	
	/* THIS IS A TEST FOR AN OPTIONAL METHOD. PLEASE IGNORE.
	@Test
	public void test_getAll_returns_all_ingredients_for_specified_recipe() {
		// arrange
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(new Ingredient());
		ingredients.add(new Ingredient());
		
		when(ingredientRepo.findAll()).thenReturn(ingredients);
	
		//act
		List<Ingredient> actual = controller.getAll();
		
		//assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(ingredients.get(0));
		verify(ingredientRepo).findAll(); //verifying this method even got called
	} */
}
