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
public class RestadorTest {
    
    public RestadorTest() {
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
     * Test of calcular method, of class Restador.
     */
    @Test
    public void testCalcular() throws Exception {
        System.out.println("calcular");
        ArrayList<String> args = new ArrayList();
        args.add("6");
        args.add("3");
        args.add("8");
        Restador instance = new Restador();
        double expResult = -5.0;
        double result = (double) instance.calcular(args);
    }
}
