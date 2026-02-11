/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package hunting;

import java.awt.Point;
import java.util.List;
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
public class BoardTest {

    /**
     * Test of isValidMove method, of class Board.
     */
    @Test
    public void testIsValidMove() {
        System.out.println("isValidMove");
        int x = 1;
        int y = 0;
        boolean isFugitiveTurn = true;
        Board instance = new Board(3);
        boolean expResult = true;
        boolean result = instance.isValidMove(x, y, isFugitiveTurn);
        assertEquals(expResult, result);
    }

    /**
     * Test of moveFugitive method, of class Board.
     */
    @Test
    public void testMoveFugitive() {
        System.out.println("moveFugitive");
        int x = 0;
        int y = 1;
        Board instance = new Board(3);
        instance.moveFugitive(x, y);
        assertEquals(x,instance.getFugitivePosition().x);
        assertEquals(y,instance.getFugitivePosition().y);
    }

    /**
     * Test of isFugitiveTrapped method, of class Board.
     */
    @Test
    public void testIsFugitiveTrapped() {
        System.out.println("isFugitiveTrapped");
        Board instance = new Board(3);
        assertEquals(false, instance.isFugitiveTrapped());
    }
    
}
