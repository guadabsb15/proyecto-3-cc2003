/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

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
public class AnalizadorTest {
    
    public AnalizadorTest() {
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
     * Test of separarParametros method, of class Analizador.
     */
    @Test
    public void testSepararParametros() throws Exception {
        System.out.println("separarParametros");
        String exp = "3 2";
        ArrayList campos = Analizador.separarParametros(exp);
        System.out.println(campos.get(0));
        System.out.println(campos.get(1));
        
        exp = "(+ 3 (- 2 3)) (/ 2 2) 3";
        campos = Analizador.separarParametros(exp);
        assertEquals(campos.get(0), "(+ 3 (- 2 3))");
        assertEquals(campos.get(1), "(/ 2 2)");
        assertEquals(campos.get(2), "3");
              
    }
    
    /**
     * Test of validar method, of class Analizador.
     */
    @Test
    public void testValidar() throws Exception {
        System.out.println("Validar");
        String exp = "(+ 3 2)";
        assertEquals(true, Analizador.validar(exp));
        
        exp = "3";
        assertEquals(true, Analizador.validar(exp));
        
        exp = "(+  (+ 9 9) (- 3 2) 3";
        assertEquals(false, Analizador.validar(exp));

    }
    
    /**
     * Test of separarAritmetica method, of class Analizador.
     */
    @Test
    public void testSepararAritmetica() throws Exception {
        String expresion = "(+ 3 2)";
        String[] campos = Analizador.separarAritmetica(expresion);
        assertEquals(campos[0], "+");
        assertEquals(campos[1], "3 2");
        
        expresion = "(+ (- 3 1) (* 2 2))";
        campos = Analizador.separarAritmetica(expresion);
        assertEquals(campos[0], "+");
        assertEquals(campos[1], "(- 3 1) (* 2 2)");
        
        ArrayList parametros = Analizador.separarParametros(campos[1]);
        assertEquals(parametros.get(0), "(- 3 1)");
        assertEquals(parametros.get(1), "(* 2 2)");
    }

}
