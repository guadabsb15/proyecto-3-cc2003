/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import src.Regexer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author asus
 */
public class SymbolTest {
    
    public SymbolTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Symbol.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Symbol instance = new Symbol("|");
        String expResult = "|";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Symbol.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Symbol instance = new Symbol("|");
        boolean result = instance.equals("|");
        assertEquals(true, result);
        
        instance = new Symbol(Regexer.CONCATENATION);
        assertEquals(true, instance.equals(Regexer.CONCATENATION_SYM));

    }
}
