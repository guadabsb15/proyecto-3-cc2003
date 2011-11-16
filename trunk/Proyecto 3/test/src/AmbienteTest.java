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
public class AmbienteTest {
    
    public AmbienteTest() {
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
     * Test of contieneVariable method, of class Ambiente.
     */
    @Test
    public void testAmbiente() {
        System.out.println("Ambiente");
        Ambiente instance = new Ambiente();
        instance.agregarVariable("x", "4");
        instance.agregarVariable("y", "x+1");
        Ambiente subinstance = new Ambiente(instance);
        subinstance.agregarVariable("y", "3");
        Ambiente subsubinst = new Ambiente(subinstance);
               
        boolean expResult = true;
        boolean result = subinstance.contieneVariable("x");
        assertEquals(expResult, result);

        try {
            String res = subinstance.obtenerValorVariable("x");
            String subres = subsubinst.obtenerValorVariable("x");
            assertEquals(res, "4");
            assertEquals(subres, "4");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        

    }

}
