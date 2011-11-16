/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import lang.Operador;
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
public class InterpreteTest {
    
    public InterpreteTest() {
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
     * Test of eval method, of class Interprete.
     */
    @Test
    public void testEval() throws Exception {
        System.out.println("eval");
        String exp = "8";
        Ambiente amb = null;
        Interprete instance = new Interprete();
        Object expResult = "8";
        Object result = instance.eval(exp, amb);
        assertEquals(expResult, result);
        
        exp = "(+ (+ 3 2) 2)";
        result = instance.eval(exp, amb);
        assertEquals(result, "7.0");
        
        exp = "(+ (- (+ 3 2) (* 2 4)) 3)";
        result = instance.eval(exp, amb);
        assertEquals(result, "0.0");

    }
    
    /**
     * Test de esDefinicion
     */
    @Test
    public void testEsDefinicion() throws Exception {
        Interprete instance = new Interprete();
        String exp = "(define x 3)";
        assertEquals(true, instance.esDefinicion(exp));
        
        exp = "define x 3";
        assertEquals(true, instance.esDefinicion(exp));
        assertEquals(true, instance.esAsignacion(exp));
    }
    
    @Test
    public void definirTest() throws Exception {
        Interprete instance = new Interprete();
        Ambiente amb = new Ambiente();
        
        instance.definir("(define x 3)", amb);
        String valor = amb.obtenerValorVariable("x");
        assertEquals(valor, "3");
        
        instance.definir("define y 45", amb);
        valor = amb.obtenerValorVariable("y");
        assertEquals(valor, "45");
    }
   
    /**
     * Test of aplicar method, of class Interprete
     */
    /*
    @Test
    public void testAplicar() throws Exception {
        System.out.println("aplicar");
        Operador op = null;
        String args = "";
        Interprete instance = new Interprete();
        String expResult = "";
        String result = instance.aplicar(op, args);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
}
