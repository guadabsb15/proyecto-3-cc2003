/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import struct.BinaryTree;
import java.util.Set;
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
public class DFABuilderTest {
    
    public DFABuilderTest() {
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
     * Test of build method, of class DFABuilder.
     */
    @Test
    public void testBuild() throws Exception {
        System.out.println("build");
        String regex = "(a|b)*·a·b·b";
        Regexer r = new Regexer();
        DFABuilder instance = new DFABuilder(r);

        
        Automaton result = instance.build(regex);
        result.printTable("dfaTest.txt");
        
        assertEquals(true, result.simulate("abb"));
        assertEquals(false, result.simulate("ab"));
        assertEquals(false, result.simulate("acb"));
        
    }
}
