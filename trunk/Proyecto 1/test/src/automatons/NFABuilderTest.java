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
public class NFABuilderTest {
    
    public NFABuilderTest() {
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
     * Test of build method, of class NFABuilder.
     */
    @Test
    public void testBuild() throws Exception {
        System.out.println("build");
        String regex = "(a | b )*";
        Regexer regexer = new Regexer();
        NFABuilder instance = new NFABuilder(regexer);
        instance.build(regex);
        
    }
    
    /**
     * Test of build method, of class NFABuilder.
     */
    @Test
    public void testSimulate() throws Exception {
        System.out.println("simulate");
        String regex = "a·b·(a | b )";
        Regexer regexer = new Regexer();
        NFABuilder instance = new NFABuilder(regexer);
        Automaton nfa = instance.build(regex);
        boolean result = nfa.simulate("aba");
        boolean expResult = true;
        assertEquals(result, expResult);
        
    }
}
