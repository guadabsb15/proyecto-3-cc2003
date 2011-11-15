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
     * Test of separarParentesis method, of class Analizador.
     */
    @Test
    public void testSepararExpresion() {
        System.out.println("separarExpresion");
        String exp = "(* 4 5 (+ 3 (/ 6 2)) 5 (- 3 1))";
        try {
            String[] tokens = Analizador.separarExpresion(exp); 
            for (int i = 0; i < tokens.length; i++) {
                System.out.println(tokens[i]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /*
        exp = "(+ 3 2) 4 5";
        try {
            String[] tokens = Analizador.separarExpresion(exp); 
            for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]);
        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        exp = "4 5";
        try {
            String[] tokens = Analizador.separarExpresion(exp); 
            tokens = Analizador.separarExpresion(tokens[1]);
            System.out.println(tokens[1]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
           
    }
    
    /**
     * Test of separarParametros method, of class Analizador.
     */
    @Test
    public void testSepararParametros() {
        System.out.println("separarParametros");
        String exp = "* 4 5 (+ 3 (/ 6 2)) 5 (- 3 1)";
        try {
            String[] tokens = Analizador.separarExpresion(exp); 
            ArrayList parametros = Analizador.separarParametros(tokens[1]);
            for (int i = 0; i < parametros.size(); i++) {
                System.out.println(parametros.get(i));
            }
        }   catch (Exception e) {
            System.out.println(e.getMessage());
        }   
    }
}
