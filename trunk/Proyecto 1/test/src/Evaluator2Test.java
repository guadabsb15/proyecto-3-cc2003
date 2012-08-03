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
public class Evaluator2Test {
    
    public Evaluator2Test() {
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
     * Test of evaluate method, of class Evaluator2.
     */
    @Test
    public void testEvaluate() {
        Evaluator2 e = new Evaluator2();
        //e.evaluate("(2 * 3) + 4 * 2");
        try {
            e.evaluate("3 + 4 * 2 + 9");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
