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
public class HuntingGameTest {

    /**
     * Test of gameEnded method, of class HuntingGame.
     */
    @Test
    public void testGameEnded() {
        System.out.println("gameEnded");
        boolean huntersWin = false;
        HuntingGame instance = new HuntingGame();
        instance.gameEnded(huntersWin);
        assertEquals(instance.message, "Fugitive survived! Draw!");
    }
    
}
