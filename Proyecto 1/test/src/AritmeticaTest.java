/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import src.scanner.Token;

/**
 *
 * @author asus
 */
public class AritmeticaTest {
    
    public AritmeticaTest() {
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
     * Test of getToken method, of class Aritmetica.
     */
    @Test
    public void testGetToken() throws Exception {
        System.out.println("getToken");
        Aritmetica instance = new Aritmetica(new File("C:/Users/asus/Desktop/Pruebas/Lectura/aritmetica.txt"));
        System.out.println(instance.getToken());
        System.out.println(instance.getToken());
        System.out.println(instance.getToken());
        System.out.println(instance.getToken());
        System.out.println(instance.getToken());
        System.out.println(instance.getToken());
        System.out.println(instance.getToken());
        System.out.println(instance.getToken());
        System.out.println(instance.getToken());
        System.out.println(instance.getToken());
        System.out.println(instance.getToken());
        System.out.println(instance.getToken());
        System.out.println(instance.getToken());
        
        
        
    }
}
