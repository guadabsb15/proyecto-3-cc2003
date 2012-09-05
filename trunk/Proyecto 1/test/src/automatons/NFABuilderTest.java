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
        
    }
    
     @Test
    public void testToDfa() throws Exception {
        System.out.println("to DFA");
        
        String regex = "(a|b)";
        Regexer regexer = new Regexer();
        NFABuilder instance = new NFABuilder(regexer);
        Automaton nfa = instance.build(regex);
        
        Automaton dfa = nfa.toDfa();
        
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
        nfa.writeFile("zerone.txt");
        Automaton dfa = nfa.toDfa();
        dfa.writeFile("zeronedfa.txt");
        boolean result = nfa.simulate("");
        assertEquals(result, true);
        //assertEquals(dfa.simulate(""), true);
        
        regex = "";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("a");
        assertEquals(result, false);
        assertEquals(dfa.simulate("a"), false);
        
        regex = "a?";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("");
        assertEquals(result, true);
        assertEquals(dfa.simulate("a"), true);
        
        regex = "";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("");
        assertEquals(result, true);
        assertEquals(dfa.simulate(""), true);
        
        
        regex = "a|ε";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("a");
        assertEquals(result, true);
        assertEquals(dfa.simulate("a"), true);
        
        regex = "a|ε";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("");
        assertEquals(result, true);
        assertEquals(dfa.simulate(""), true);
        
        regex = "a|ε";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("aa");
        assertEquals(result, false);
        assertEquals(dfa.simulate("aa"), false);
        
        regex = "ab";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("a");
        assertEquals(result, false);
        assertEquals(dfa.simulate("a"), false);
        
        regex = "(a | b)?";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("");
        assertEquals(result, true);
        assertEquals(dfa.simulate(""), true);
        
        regex = "(a | b )?";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("");
        assertEquals(result, true);
        assertEquals(dfa.simulate(""), true);
        
        regex = "(a | b )?";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("ab");
        assertEquals(result, false);
        assertEquals(dfa.simulate("ab"), false);
        
        regex = "(a| b )*";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("");
        assertEquals(result, true);
        assertEquals(dfa.simulate(""), true);
        
        regex = "(a|b )*";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("abababa");
        assertEquals(result, true);
        assertEquals(dfa.simulate("abababa"), true);
        
        regex = "b *";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("abababa");
        assertEquals(result, false);
        assertEquals(dfa.simulate("abababa"), false);
        
        regex = "b *";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("");
        assertEquals(result, true);
        assertEquals(dfa.simulate(""), true);
        
        regex = "b*";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("bbb");
        assertEquals(result, true);
        assertEquals(dfa.simulate("bbb"), true);
        
        regex = "b *";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("bbba");
        assertEquals(result, false);
        assertEquals(dfa.simulate("bbba"), false);
        
        regex = "(a | b)+";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("abababa");
        assertEquals(result, true);
        assertEquals(dfa.simulate("abababa"), true);
        
        regex = "(a | b )+";
        nfa = instance.build(regex);
        result = nfa.simulate("");
        assertEquals(result, false);
        assertEquals(dfa.simulate(""), false);
        
        regex = "(a b )+";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("ab");
        assertEquals(result, true);
        assertEquals(dfa.simulate("ab"), true);
        
        regex = "ab(a | b )*";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("aba");
        assertEquals(result, true);
        assertEquals(dfa.simulate("aba"), true);
        
        regex = "ab(a | b )*b";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("abab");
        assertEquals(result, true);
        assertEquals(dfa.simulate("abab"), true);
        
        regex = "ab(a | b )*b";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("abbb");
        assertEquals(result, true);
        assertEquals(dfa.simulate("abbb"), true);
        
        regex = "ab(a | b )*b";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("abaaaabaabbb");
        nfa.writeFile("nfa.txt");
        assertEquals(result, true);
        assertEquals(dfa.simulate("abaaaabaabbb"), true);
        
        regex = "abcd";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("abcd");
        assertEquals(result, true);
        assertEquals(dfa.simulate("abcd"), true);
          
        regex = "aε";
        nfa = instance.build(regex);
        dfa = nfa.toDfa();
        result = nfa.simulate("a");
        assertEquals(result, true);
        assertEquals(dfa.simulate("a"), true);
    }
}
