package com.liberymutual.goforcode.penneForYourThoughts.models;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.penneForYourThoughts.models.Ingredient;
import com.libertymutual.goforcode.penneForYourThoughts.models.Instruction;
import com.libertymutual.goforcode.penneForYourThoughts.models.Recipe;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.IngredientRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.InstructionRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.RecipeRepository;

public class RecipeModelTests {
	
	
	private RecipeRepository recipeRepo;
	private IngredientRepository ingredientRepo;
	private InstructionRepository instructionRepo;

	@Before
	public void setUp() {
		recipeRepo = mock(RecipeRepository.class);
		ingredientRepo = mock(IngredientRepository.class);
		instructionRepo = mock(InstructionRepository.class);
	}
	
	@Test
    public void test_all_gets_and_sets() {
        new BeanTester().testBean(Recipe.class);
        new BeanTester().testBean(Instruction.class);
        new BeanTester().testBean(Ingredient.class);
	}
	
	@Test
    public void test_add_Instruction() {
		//arrange
		Instruction ins = new Instruction();
		ArrayList<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(ins);
		
		//act
		ArrayList<Instruction> actualInstructions = new ArrayList<Instruction>();
		Recipe recipe = new Recipe();
		recipe.addInstruction(ins);
		actualInstructions = recipe.getInstructions();
		
		//assert
		assertThat(actualInstructions).contains(ins);
    }
	
	@Test
    public void test_add_Ingredients() {
		//arrange
		Ingredient ing = new Ingredient();
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(ing);
	
		//act
		ArrayList<Ingredient> actualIngredients = new ArrayList<Ingredient>();
		Recipe recipe = new Recipe();
		recipe.addIngredient(ing);
		actualIngredients = recipe.getIngredients();
		
		//assert
		assertThat(actualIngredients).contains(ing);
    }
}
