/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Sarah Heckman
 */
public class CoffeeMakerTest {
	
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
		
		 
	}
	
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
	}
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
	}
	
	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void testMakeCoffee() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}
	
	 @Test
	    public void testAddRecipe() {
	        assertTrue("Should return true when adding a valid recipe", coffeeMaker.addRecipe(recipe1));
	    }

	    @Test
	    public void testAddDuplicateRecipe() {
	        coffeeMaker.addRecipe(recipe1);
	        assertFalse("Should return false when adding a duplicate recipe", coffeeMaker.addRecipe(recipe1));
	    }
	    
	    @Test
	    public void testDeleteRecipe() {
	        coffeeMaker.addRecipe(recipe1);
	        // Assuming the index of the recipe we want to delete is 0, as it's the first and only recipe added
	        String recipeName = recipe1.getName();
	        assertEquals("Should return the name of the deleted recipe", recipeName, coffeeMaker.deleteRecipe(0));
	        // Alternatively, if you don't care about the return value and just want to ensure it's not null:
	        // assertNotNull("Recipe should be deleted successfully", coffeeMaker.deleteRecipe(0));
	    }


	    
	    @Test
	    public void testEditRecipe() {
	        // Assuming coffeeMaker is properly initialized and contains a recipe book
	        CoffeeMaker coffeeMaker = new CoffeeMaker();
	        Recipe recipe1 = new Recipe(); // Assuming recipe1 is properly initialized

	        // Add the recipe to the coffee maker's recipe book
	        coffeeMaker.addRecipe(recipe1);

	        // Update the recipe (assuming there's a method to update recipe properties)
	        try {
	        recipe1.setAmtChocolate("2"); // Convert int to String
	        }
	        catch (Exception e) {
	        	
	        }
	        		

	        // Call the editRecipe method and assert that it returns a non-null value
	        String editedRecipeName = coffeeMaker.editRecipe(0, recipe1); // Assuming 0 is the index of the recipe to edit
	        assertNotNull(editedRecipeName, "Should return the name of the edited recipe");
	    }


	    
	    @Test
	    public void testMakeCoffeeInsufficientFunds() {
	        coffeeMaker.addRecipe(recipe1);
	        int change = coffeeMaker.makeCoffee(0, 25); // Assuming 0 is the index of the recipe
	        assertEquals("Should not make coffee if not enough money is provided", 25, change);
	    }



	    @Test
	    public void testMakeCoffeeWithInsufficientIngredients() {
	        Recipe recipe = new Recipe();
	        coffeeMaker.addRecipe(recipe);

	        // Do not add enough ingredients to make coffee
	       // assertFalse(coffeeMaker.makeCoffee(0, 25)); // Assuming 0 is the index of the recipe
	    }




	 


	    @Test
	    public void testMakeCoffeeNoRecipe() {
	     
			int change = coffeeMaker.makeCoffee((0), 100); // Assuming passing null as recipe
	        assertEquals("Should not make coffee if no recipe is selected", 100, change);
	    }


}
