/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

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
public class NFABuilderTest {
    
    public NFABuilderTest() {
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
     * Test of build method, of class NFABuilder.
     */
    @Test
    public void testBuild() throws Exception {
        System.out.println("build");
        String regex = "(a | b )*";
        Regexer regexer = new Regexer();
        NFABuilder instance = new NFABuilder(regexer);
        Automaton nfa = instance.build(regex);
        nfa.printTable("nfa.txt");
        
    }
    
     @Test
    public void testToDfa() throws Exception {
        System.out.println("simulate");
        
        String regex = "(a | b )";
        Regexer regexer = new Regexer();
        NFABuilder instance = new NFABuilder(regexer);
        Automaton nfa = instance.build(regex);
        
        Automaton dfa = nfa.toDfa();
        dfa.printTable("dfa.txt");
        
     }
    
    /**
     * Test of build method, of class NFABuilder.
     */
    @Test
    public void testSimulate() throws Exception {
        System.out.println("simulate");
        
        String regex = "(a | b )?";
        Regexer regexer = new Regexer();
        NFABuilder instance = new NFABuilder(regexer);
        Automaton nfa = instance.build(regex);
        boolean result = nfa.simulate("");
        boolean expResult = true;
        assertEquals(result, expResult);
        
        regex = "(a | b )?";
        nfa = instance.build(regex);
        result = nfa.simulate("");
        assertEquals(result, expResult);
        
        regex = "(a | b )?";
        nfa = instance.build(regex);
        result = nfa.simulate("");
        assertEquals(result, expResult);
        
        regex = "(a | b )?";
        nfa = instance.build(regex);
        result = nfa.simulate("ab");
        assertEquals(result, false);
        
        regex = "(a | b )*";
        nfa = instance.build(regex);
        result = nfa.simulate("");
        assertEquals(result, expResult);
        
        regex = "(a | b )*";
        nfa = instance.build(regex);
        result = nfa.simulate("abababa");
        assertEquals(result, expResult);
        
        regex = "b *";
        nfa = instance.build(regex);
        result = nfa.simulate("abababa");
        assertEquals(result, false);
        
        regex = "b *";
        nfa = instance.build(regex);
        result = nfa.simulate("");
        assertEquals(result, true);
        
        regex = "b*";
        nfa = instance.build(regex);
        result = nfa.simulate("bbb");
        assertEquals(result, true);
        
        regex = "b *";
        nfa = instance.build(regex);
        result = nfa.simulate("bbba");
        assertEquals(result, false);
        
        regex = "(a | b )+";
        nfa = instance.build(regex);
        result = nfa.simulate("abababa");
        assertEquals(result, expResult);
        
        regex = "(a | b )+";
        nfa = instance.build(regex);
        result = nfa.simulate("");
        assertEquals(result, false);
        
        regex = "(a · b )+";
        nfa = instance.build(regex);
        result = nfa.simulate("ab");
        assertEquals(result, true);
        
        regex = "a·b·(a | b )*";
        nfa = instance.build(regex);
        result = nfa.simulate("aba");
        assertEquals(result, true);
        
        regex = "a·b·(a | b )*·b";
        nfa = instance.build(regex);
        result = nfa.simulate("abab");
        assertEquals(result, true);
        
        regex = "a·b·(a | b )*·b";
        nfa = instance.build(regex);
        result = nfa.simulate("abbb");
        assertEquals(result, true);
        
        regex = "a·b·(a | b )*·b";
        nfa = instance.build(regex);
        result = nfa.simulate("abaaaabaabbb");
        assertEquals(result, true);
        
        regex = "a·b·c·d";
        nfa = instance.build(regex);
        result = nfa.simulate("abcd");
        assertEquals(result, true);
        
        regex = "a·b·c·d";
        nfa = instance.build(regex);
        result = nfa.simulate("abdc");
        assertEquals(result, false);
    }
}
