/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import src.scanner.Token;

/**
 *
 * @author asus
 */
public class HexNumberTest {
    
    public HexNumberTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of getToken method, of class Double.
     */
    @Test
    public void testGetToken() throws Exception {
        System.out.println("getToken");
        HexNumber instance = new HexNumber(new File("C:/Users/asus/Desktop/Pruebas/Lectura/hex.txt"));
        Token t = instance.getToken();
        while (!t.value().equals("-1")) {
            System.out.println(t);
            t = instance.getToken();
        }
    }
}
