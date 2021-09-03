package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for inventory class.
 *
 * @author Panida Ounnaitham
 */
public class InventoryTest {
    /**
     * The object under test.
     */
    private Inventory inventory;

    /**
     * Initializes some value of an ingredient
     */
    @Before
    public void setUp() {
        inventory = new Inventory();
        inventory.setSugar(5);
        inventory.setMilk(5);
        inventory.setCoffee(5);
        inventory.setChocolate(5);
    }

    /**
     * Test inventory when set sugar
     */
    @Test
    public void testSetSugar() {
        inventory.setSugar(-5);
        int check = inventory.getSugar();
        assertEquals(5,check);

    }

    /**
     * Test inventory when set milk
     */
    @Test
    public void testSetMilk() {
        inventory.setMilk(-5);
        int check = inventory.getMilk();
        assertEquals(5,check);
    }

    /**
     * Test inventory when set coffee
     */
    @Test
    public void testSetCoffee() {
        inventory.setCoffee(-5);
        int check = inventory.getCoffee();
        assertEquals(5,check);
    }

    /**
     * Test inventory when set chocolate
     */
    @Test
    public void testSetChocolate() {
        inventory.setChocolate(-5);
        int check = inventory.getChocolate();
        assertEquals(5,check);
    }

    /**
     * Test inventory when coffee is added by negative number
     */
    @Test(expected =InventoryException.class )
    public void testAddInventoryCoffeeWithNegativeNum() throws InventoryException {
        inventory.addCoffee("-4");
    }

    /**
     * Test inventory when coffee is added by string
     */
    @Test(expected =InventoryException.class )
    public void testAddInventoryCoffeeWithString() throws InventoryException {
        inventory.addCoffee("four");
    }

    /**
     * Test inventory when milk is added by negative number
     */
    @Test(expected =InventoryException.class )
    public void testAddInventoryMilkWithNegativeNum() throws InventoryException {
        inventory.addMilk("-4");
    }

    /**
     * Test inventory when milk is added by string
     */
    @Test(expected =InventoryException.class )
    public void testAddInventoryMilkWithString() throws InventoryException {
        inventory.addSugar("four");
    }

    /**
     * Test inventory when sugar is added by negative number
     */
    @Test(expected =InventoryException.class )
    public void testAddInventorySugarWithNegativeNum() throws InventoryException {
        inventory.addSugar("-4");
    }

    /**
     * Test inventory when sugar is added by string
     */
    @Test(expected =InventoryException.class )
    public void testAddInventorySugarWithString() throws InventoryException {
        inventory.addSugar("four");
    }

    /**
     * Test inventory when chocolate is added by negative number
     */
    @Test(expected =InventoryException.class )
    public void testAddInventoryChocWithNegativeNum() throws InventoryException {
        inventory.addChocolate("-4");
    }

    /**
     * Test inventory when chocolate is added by string
     */
    @Test(expected =InventoryException.class )
    public void testAddInventoryChocWithString() throws InventoryException {
        inventory.addChocolate("four");
    }
}
