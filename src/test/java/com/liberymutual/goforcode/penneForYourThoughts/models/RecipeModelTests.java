package com.liberymutual.goforcode.penneForYourThoughts.models;

import static org.assertj.core.api.Assertions.*;
import java.util.*;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import com.libertymutual.goforcode.penneForYourThoughts.models.Ingredient;
import com.libertymutual.goforcode.penneForYourThoughts.models.Instruction;
import com.libertymutual.goforcode.penneForYourThoughts.models.Recipe;

public class RecipeModelTests {
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
		Set<Instruction> instructions = new LinkedHashSet<Instruction>();
		instructions.add(ins);
		
		//act
		Set<Instruction> actualInstructions = new LinkedHashSet<Instruction>();
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
		Set<Ingredient> ingredients = new LinkedHashSet<Ingredient>();
		ingredients.add(ing);
	
		//act
		Set<Ingredient> actualIngredients = new LinkedHashSet<Ingredient>();
		Recipe recipe = new Recipe();
		recipe.addIngredient(ing);
		actualIngredients = recipe.getIngredients();
		
		//assert
		assertThat(actualIngredients).contains(ing);
    }
}
