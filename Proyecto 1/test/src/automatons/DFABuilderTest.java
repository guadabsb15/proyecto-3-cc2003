/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import struct.BinaryTree;
import java.util.Set;
import src.Regexer;
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
public class DFABuilderTest {
    
    public DFABuilderTest() {
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
     * Test of build method, of class DFABuilder.
     */
    @Test
    public void testBuild() throws Exception {
        System.out.println("build");
        String regex = "(a|b)*abb";
        Regexer r = new Regexer();
        DFABuilder instance = new DFABuilder(r);

        
        Automaton dfa = instance.build(regex);
        
        assertEquals(true, dfa.simulate("abb"));
        assertEquals(false, dfa.simulate(""));
        assertEquals(false, dfa.simulate("ab"));
        assertEquals(false, dfa.simulate("acb"));
        
        
        
        regex = "(a|b)*abb";
        dfa = instance.build(regex);
        assertEquals(true, dfa.simulate("abb"));
        assertEquals(false, dfa.simulate(""));
        assertEquals(false, dfa.simulate("ab"));
        assertEquals(false, dfa.simulate("acb"));
        boolean result;
        
        regex = "(a | b )?";
        dfa = instance.build(regex);
        result = dfa.simulate("");
        assertEquals(result, true);
        
        regex = "";
        dfa = instance.build(regex);
        result = dfa.simulate("a");
        assertEquals(result, false);
        
        regex = "";
        dfa = instance.build(regex);
        result = dfa.simulate("");
        assertEquals(result, true);
        
        
        regex = "a|ε";
        dfa = instance.build(regex);
        result = dfa.simulate("a");
        assertEquals(result, true);
        
        regex = "a|ε";
        dfa = instance.build(regex);
        result = dfa.simulate("");
        assertEquals(result, true);
        
        regex = "a|ε";
        dfa = instance.build(regex);
        result = dfa.simulate("aa");
        assertEquals(result, false);
        
        regex = "ab";
        dfa = instance.build(regex);
        result = dfa.simulate("a");
        assertEquals(result, false);
        
        
        
        regex = "(a | b )?";
        dfa = instance.build(regex);
        result = dfa.simulate("");
        assertEquals(result, true);
        
        regex = "(a | b )?";
        dfa = instance.build(regex);
        result = dfa.simulate("ab");
        assertEquals(result, false);
        
        regex = "(a | b )*";
        dfa = instance.build(regex);
        result = dfa.simulate("");
        assertEquals(result, true);
        
        regex = "(a | b )*";
        dfa = instance.build(regex);
        result = dfa.simulate("abababa");
        assertEquals(result, true);
        
        regex = "b *";
        dfa = instance.build(regex);
        result = dfa.simulate("abababa");
        assertEquals(result, false);
        
        regex = "b *";
        dfa = instance.build(regex);
        result = dfa.simulate("");
        assertEquals(result, true);
        
        regex = "b*";
        dfa = instance.build(regex);
        result = dfa.simulate("bbb");
        assertEquals(result, true);
        
        regex = "b *";
        dfa = instance.build(regex);
        result = dfa.simulate("bbba");
        assertEquals(result, false);
        
        regex = "(a | b )+";
        dfa = instance.build(regex);
        dfa.writeFile("archivo.txt");
        result = dfa.simulate("ababab");
        assertEquals(result, true);
        
        regex = "(a | b )+";
        dfa = instance.build(regex);
        result = dfa.simulate("");
        assertEquals(result, false);
        
        regex = "(a b )+";
        dfa = instance.build(regex);
        result = dfa.simulate("ab");
        assertEquals(result, true);
        
        regex = "ab(a | b )*";
        dfa = instance.build(regex);
        result = dfa.simulate("aba");
        assertEquals(result, true);
        
        regex = "ab(a | b )*b";
        dfa = instance.build(regex);
        result = dfa.simulate("abab");
        assertEquals(result, true);
        
        regex = "ab(a | b )*b";
        dfa = instance.build(regex);
        result = dfa.simulate("abbb");
        assertEquals(result, true);
        
        regex = "ab(a | b )*b";
        dfa = instance.build(regex);
        result = dfa.simulate("abaaaabaabbb");
        assertEquals(result, true);
        
        regex = "ab(a | b )*b";
        dfa = instance.build(regex);
        result = dfa.simulate("abaaaabaabba");
        assertEquals(result, false);
        
        regex = "abcd";
        dfa = instance.build(regex);
        result = dfa.simulate("abcd");
        assertEquals(result, true);
          
        regex = "aε";
        dfa = instance.build(regex);
        result = dfa.simulate("a");
        assertEquals(result, true);
    }
}
