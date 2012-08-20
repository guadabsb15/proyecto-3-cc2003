/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

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
public class EvaluatorTest {
    
    public EvaluatorTest() {
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

    @Test
    public void testEvaluate() {
        Evaluator e = new Evaluator();
        //e.evaluate("(2 * 3) + 4 * 2");
        e.evaluate("1 * (2 * 3)");
        
        
    }
}
