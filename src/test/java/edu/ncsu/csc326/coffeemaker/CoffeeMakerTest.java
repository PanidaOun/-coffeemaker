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

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import static org.junit.Assert.*;

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
		coffeeMaker.addInventory("4","7","5","9");
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

	/**
	 * Test adding a recipe.
	 */
	@Test
	public void testAddRecipe() {
		coffeeMaker.addRecipe(recipe1);
	}

	/**
	 * Test adding more than three recipe.
	 */
	@Test
	public void testAddThreeRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertFalse(coffeeMaker.addRecipe(recipe4));
	}

	/**
	 * Test adding recipe that have the same name.
	 */
	@Test
	public void testAddRecipeUniqueName() throws RecipeException {
		coffeeMaker.addRecipe(recipe1);
		Recipe recipe5 = new Recipe();
		recipe5.setName("Coffee");
		recipe5.setAmtChocolate("4");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("1");
		recipe5.setAmtSugar("1");
		recipe5.setPrice("65");
		assertFalse(coffeeMaker.addRecipe(recipe5));
	}

	/**
	 * Test adding recipe that it's price is not an integer.
	 */
	@Test(expected = RecipeException.class)
	public void testAddRecipeNotIntegerPrice() throws RecipeException {
		Recipe recipe5 = new Recipe();
		recipe5.setName("Coffee");
		recipe5.setAmtChocolate("4");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("1");
		recipe5.setAmtSugar("1");
		recipe5.setPrice("sixty");
	}

	/**
	 * Test deleting recipe.
	 */
	@Test
	public void testDeleteRecipe()  {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.deleteRecipe(1);
		assertNotEquals(recipe2,coffeeMaker.getRecipes()[1]);
	}

	/**
	 * Test deleting recipe that not in the list.
	 */
	@Test
	public void testDeleteRecipeNotInList()  {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertEquals(coffeeMaker.deleteRecipe(4),null);
	}

	/**
	 * Test deleting recipe that given position is negative.
	 */
	@Test
	public void testDeleteRecipeNegativePosition()  {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertEquals(coffeeMaker.deleteRecipe(-1),null);
	}

	/**
	 * Test editing recipe.
	 */
	@Test
	public void testEditRecipe()  {
		Recipe [] recipeArray = coffeeMaker.getRecipes();
		coffeeMaker.addRecipe(recipe1);
		assertEquals(recipeArray[0].getName(),"Coffee" );
		coffeeMaker.editRecipe(0, recipe2);
		recipeArray = coffeeMaker.getRecipes();
		assertEquals(recipeArray[0].getName(),"Coffee" );
	}

	/**
	 * Test check inventory of coffee when add inventory.
	 * @throws InventoryException  if there was an error parsing the quanity
	 * to a positive integer.
	 */
	@Test
	public void testCheckInventoryCoffeeWhenAdd() throws InventoryException {
		coffeeMaker.addInventory("4","0","0","0");
		String expectedInventoryString = "Coffee: 19\nMilk: 15\nSugar: 15\nChocolate: 15\n";
		String check = coffeeMaker.checkInventory();
		assertEquals(expectedInventoryString,check);
	}

	/**
	 * Test check inventory of milk when add inventory.
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testCheckInventoryMilkWhenAdd() throws InventoryException {
		coffeeMaker.addInventory("0","4","0","0");
		String expectedInventoryString = "Coffee: 15\nMilk: 19\nSugar: 15\nChocolate: 15\n";
		String check = coffeeMaker.checkInventory();
		assertEquals(expectedInventoryString,check);
	}

	/**
	 * Test check inventory of sugar when add inventory.
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testCheckInventorySugarWhenAdd() throws InventoryException {
		coffeeMaker.addInventory("0","0","4","0");
		String expectedInventoryString = "Coffee: 15\nMilk: 15\nSugar: 19\nChocolate: 15\n";
		String check = coffeeMaker.checkInventory();
		assertEquals(expectedInventoryString,check);
	}

	/**
	 * Test check inventory of chocolate when add inventory.
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testCheckInventoryChocWhenAdd() throws InventoryException {
		coffeeMaker.addInventory("0","0","0","4");
		String expectedInventoryString = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 19\n";
		String check = coffeeMaker.checkInventory();
		assertEquals(expectedInventoryString,check);
	}

	/**
	 * Test check inventory of coffee when use the ingredient.
	 *@throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testCheckInventoryCoffeeWhenUse() throws InventoryException, RecipeException {
		Recipe recipe5 = new Recipe();
		recipe5.setName("Coffee");
		recipe5.setAmtChocolate("0");
		recipe5.setAmtCoffee("4");
		recipe5.setAmtMilk("0");
		recipe5.setAmtSugar("0");
		recipe5.setPrice("5");
		coffeeMaker.addRecipe(recipe5);
		coffeeMaker.makeCoffee(0, 5);
		String expectedInventoryString = "Coffee: 11\nMilk: 15\nSugar: 15\nChocolate: 15\n";
		String check = coffeeMaker.checkInventory();
		assertEquals(expectedInventoryString,check);
	}

	/**
	 * Test check inventory of milk when use the ingredient.
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testCheckInventoryMilkWhenUse() throws InventoryException, RecipeException {
		Recipe recipe5 = new Recipe();
		recipe5.setName("Coffee");
		recipe5.setAmtChocolate("0");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("4");
		recipe5.setAmtSugar("0");
		recipe5.setPrice("5");
		coffeeMaker.addRecipe(recipe5);
		coffeeMaker.makeCoffee(0, 5);
		String expectedInventoryString = "Coffee: 15\nMilk: 11\nSugar: 15\nChocolate: 15\n";
		String check = coffeeMaker.checkInventory();
		assertEquals(expectedInventoryString,check);
	}

	/**
	 * Test check inventory of sugar when use the ingredient.
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testCheckInventorySugarWhenUse() throws InventoryException, RecipeException {
		Recipe recipe5 = new Recipe();
		recipe5.setName("Coffee");
		recipe5.setAmtChocolate("0");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("0");
		recipe5.setAmtSugar("4");
		recipe5.setPrice("5");
		coffeeMaker.addRecipe(recipe5);
		coffeeMaker.makeCoffee(0, 5);
		String expectedInventoryString = "Coffee: 15\nMilk: 15\nSugar: 11\nChocolate: 15\n";
		String check = coffeeMaker.checkInventory();
		assertEquals(expectedInventoryString,check);
	}

	/**
	 * Test check inventory of chocolate when use the ingredient.
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testCheckInventoryChocWhenUse() throws InventoryException, RecipeException {
		Recipe recipe5 = new Recipe();
		recipe5.setName("Coffee");
		recipe5.setAmtChocolate("4");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("0");
		recipe5.setAmtSugar("0");
		recipe5.setPrice("5");
		coffeeMaker.addRecipe(recipe5);
		coffeeMaker.makeCoffee(0, 5);
		String expectedInventoryString = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 11\n";
		String check = coffeeMaker.checkInventory();
		assertEquals(expectedInventoryString,check);
	}

	/**
	 * Test change when price of coffee more than amount.
	 * Then it will return change equal to amount and check inventory will equal to initially.
	 */
	@Test
	public void testPurchaseBeveragePriceMoreThanAmt() {
		coffeeMaker.addRecipe(recipe1);
		int change = coffeeMaker.makeCoffee(0, 40);
		assertEquals(40,change);
		String expectedInventoryString = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
		String check = coffeeMaker.checkInventory();
		assertEquals(expectedInventoryString,check);
	}

	/**
	 * Test change when price of coffee equal to amount.
	 */
	@Test
	public void testPurchaseBeveragePriceEqualAmt() {
		coffeeMaker.addRecipe(recipe1);
		int change = coffeeMaker.makeCoffee(0, 50);
		assertEquals(0,change);
	}

}
