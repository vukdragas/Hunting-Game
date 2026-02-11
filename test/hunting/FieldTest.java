/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package hunting;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wolfd
 */
public class FieldTest {
    /**
     * Test of clear method, of class Field.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        Field instance = new Field();
        instance.clear();
        
        assertEquals(-1, instance.getHunterId());
        assertEquals(false, instance.hasFugitive());
        assertEquals(false, instance.hasHunter());
    }

    /**
     * Test of isOccupied method, of class Field.
     */
    @Test
    public void testIsOccupied() {
        System.out.println("isOccupied");
        Field instance = new Field();
        boolean expResult = false;
        boolean result = instance.isOccupied();
        assertEquals(expResult, result);
    }
    
}
