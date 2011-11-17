/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lang;

import java.util.ArrayList;
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
public class DivisorTest {
    
    public DivisorTest() {
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
     * Test of calcular method, of class Divisor.
     */
    @Test
    public void testCalcular() throws Exception {
        System.out.println("calcular");
        ArrayList<String> args = new ArrayList();
        args.add("6");
        args.add("3");
        args.add("1");
        Divisor instance = new Divisor();
        double expResult = 2.0;
        double result = (Double) instance.calcular(args);
        assertEquals(expResult, result, 0.0);
    }
}
