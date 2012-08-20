/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Set;
import java.util.Iterator;
import struct.BTPostOrderIterator;
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
public class RegexerTest {
    
    public RegexerTest() {
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
     * Test of evaluate method, of class Evaluator2.
     */
    @Test
    public void testEvaluate() {
        Regexer e = new Regexer("+*|?.", "()", "123");
        //e.evaluate("(2 * 3) + 4 * 2");
        try {
            e.evaluate("(1 | 2 + | 3) * ");
            BTPostOrderIterator it = new BTPostOrderIterator(e.returnAST());
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println();
        System.out.println();

    }   
    
    /**
     * Test of evaluate method, of class Evaluator2.
     */
    @Test
    public void testMakeSymbolSet() {
        Regexer e = new Regexer("+*|?.", "()", "abc");
        try {
            e.evaluate("(a | b + | c) * ");
            Set s = e.symbols();
            Iterator it = s.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }  
}
