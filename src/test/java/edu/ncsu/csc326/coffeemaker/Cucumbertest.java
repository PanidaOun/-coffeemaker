package edu.ncsu.csc326.coffeemaker;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertEquals;

public class Cucumbertest {

    private CoffeeMaker coffeeMaker;

    @Given("Initial number of ingredients")
    public void initialNumberOfIngredients() {
        coffeeMaker = new CoffeeMaker();
    }

    @When("Add {int} coffee to amount of ingredients")
    public void addCoffeeToAmountOfIngredients(Integer int1) throws InventoryException {
        // Write code here that turns the phrase above into concrete actions
        coffeeMaker.addInventory(int1.toString(),"0","0","0");
    }

    @Then("^The total of coffee will have (\\d+)$")
    public void theTotalOfCoffeeWillHave(int arg0) {
        String expectedInventoryString = String.format("Coffee: %d\nMilk: 15\nSugar: 15\nChocolate: 15\n",arg0);
        String check = coffeeMaker.checkInventory();
        assertEquals(expectedInventoryString,check);
    }

    @When("Add {int} milk to amount of ingredients")
    public void addMilkToAmountOfIngredients(Integer int1) throws InventoryException {
        coffeeMaker.addInventory("0",int1.toString(),"0","0");
    }

    @Then("The total of milk will have {int}")
    public void theTotalOfMilkWillHave(Integer int1) {
        String expectedInventoryString = String.format("Coffee: 15\nMilk: %d\nSugar: 15\nChocolate: 15\n",int1);
        String check = coffeeMaker.checkInventory();
        assertEquals(expectedInventoryString,check);
    }

    @When("Add {int} chocolate to amount of ingredients")
    public void addChocolateToAmountOfIngredients(Integer int1) throws InventoryException {
        coffeeMaker.addInventory("0","0","0",int1.toString());
    }

    @Then("The total of chocolate will have {int}")
    public void theTotalOfChocolateWillHave(Integer int1) {
        String expectedInventoryString = String.format("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: %d\n",int1);
        String check = coffeeMaker.checkInventory();
        assertEquals(expectedInventoryString,check);
    }

    @When("Add {int} sugar to amount of ingredients")
    public void addSugarToAmountOfIngredients(Integer int1) throws InventoryException {
        coffeeMaker.addInventory("0","0",int1.toString(),"0");
    }

    @Then("The total of sugar will have {int}")
    public void theTotalOfSugarWillHave(Integer int1) {
        String expectedInventoryString = String.format("Coffee: 15\nMilk: 15\nSugar: %d\nChocolate: 15\n",int1);
        String check = coffeeMaker.checkInventory();
        assertEquals(expectedInventoryString,check);
    }

}
