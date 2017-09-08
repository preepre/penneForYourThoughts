package com.liberymutual.goforcode.penneForYourThoughts.models;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.penneForYourThoughts.models.Instruction;
import com.libertymutual.goforcode.penneForYourThoughts.models.Recipe;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.InstructionRepository;
import com.libertymutual.goforcode.penneForYourThoughts.repositories.RecipeRepository;

public class RecipeModelTests {
	
	@Test
    public void test_all_gets_and_sets() {
        new BeanTester().testBean(Recipe.class);
    }
	
	@Test
	public void test_add_Instruction() {
		
		Recipe recipe1 = new Recipe();
		Instruction instruction1 = new Instruction();
		recipe1.addInstruction(instruction1);		
		
		//assertThat(recipe1).hasFieldOrPropertyWithValue("instruction", instruction1)).has
		
		
		
	}

	
}
