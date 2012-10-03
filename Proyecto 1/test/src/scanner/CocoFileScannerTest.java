/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

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
public class CocoFileScannerTest {
    
    public CocoFileScannerTest() {
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
     * Test of read method, of class CocoFileScanner.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        try {
            CocoFileScanner instance = new CocoFileScanner("cocofile.txt");
            Token current = instance.getToken();
            while (!current.equals(TokenTypes.EOF_TOKEN)) {
                System.out.println(current.toString());
                current = instance.getToken();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
