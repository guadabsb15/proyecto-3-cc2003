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
     * Test of separarAritmetica method, of class Analizador.
     */
    @Test
    public void testSepararAritmetica() throws Exception {
        System.out.println("separarAritmetica");
        String exp = "(+ 3 2)";
        String[] campos = Analizador.separarAritmetica(exp);
        System.out.println(campos[0]);
        System.out.println(campos[1]);
        /*
        exp = "(+  (+ 9 9) (- 3 2) 3)";
        campos = Analizador.separarAritmetica(exp);
        System.out.println(campos[0]);
        System.out.println(campos[1]);*/
    }
    
    /**
     * Test of separarAritmetica method, of class Analizador.
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


}
