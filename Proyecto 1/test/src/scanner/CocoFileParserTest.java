/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import struct.GenericTree;

/**
 *
 * @author asus
 */
public class CocoFileParserTest {
    
    public CocoFileParserTest() {
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
     * Test of parse method, of class CocoFileParser.
     */
    @Test
    public void testParse() throws Exception {
        System.out.println("parse");
        CocoFileParser instance = new CocoFileParser("cocofile.txt");
        GenericTree expResult = null;
        instance.parse();
        int a = 3;
        
    }
}
