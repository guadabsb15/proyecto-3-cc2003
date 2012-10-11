/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import src.automatons.DFA;

/**
 *
 * @author asus
 */
public class FileBuilderTest {
    
    public FileBuilderTest() {
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
     * Test of buildAutomaton method, of class FileBuilder.
     */
    @Test
    public void testBuildAutomaton() throws Exception {
        System.out.println("buildAutomaton");
        FileBuilder instance = new FileBuilder("cocofile.txt");
        instance.buildAutomaton();
        DFA dfa = instance.dfa();
        //dfa.writeFile("P2.txt");
        System.out.println(dfa.simulate("while") + " Type: " + (dfa.lastState().attached()));
        System.out.println(dfa.simulate("if")+ " Type: " + (dfa.lastState().attached()));
        System.out.println(dfa.simulate("aab") + " Type: " + (dfa.lastState().attached()));
        System.out.println(dfa.simulate("aabg") + " Type: " + (dfa.lastState().attached()));
    }

    /**
     * Test of dfa method, of class FileBuilder.
     
    @Test
    public void testDfa() {
        System.out.println("dfa");
        FileBuilder instance = null;
        DFA expResult = null;
        DFA result = instance.dfa();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     * */
}
