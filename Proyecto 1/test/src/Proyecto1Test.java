/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import src.automatons.Symbol;
import src.automatons.Pair;
import src.automatons.State;
import src.automatons.NFA;
import src.automatons.DFABuilder;
import src.automatons.Automaton;
import src.automatons.NFABuilder;
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
public class Proyecto1Test {
    
    public Proyecto1Test() {
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

    @Test
    public void testbuildNFA() throws Exception {
        try {
            Regexer r = new Regexer();
            String regex = "(a|b)*";
            NFABuilder instance = new NFABuilder(r);
        
            Automaton nfa = instance.build(regex); 
            Automaton dfa = nfa.toDfa();
                  
            Automaton parcial = new NFA();
            for (int i = 1; i <= 18; i++) {
                parcial.addState(new State(Integer.toString(i)));
            }
            parcial.changeInitialState(new State("1"));
            parcial.addAcc(new State("18"));
            parcial.addSymbol(new Symbol("a"));
            parcial.addSymbol(new Symbol("b"));
            
            parcial.addTr(new Pair<State, Symbol>(new State("1"), Regexer.EMPTY_STR), new State("2"));
            parcial.addTr(new Pair<State, Symbol>(new State("1"), Regexer.EMPTY_STR), new State("8"));
            parcial.addTr(new Pair<State, Symbol>(new State("2"), Regexer.EMPTY_STR), new State("3"));
            parcial.addTr(new Pair<State, Symbol>(new State("2"), Regexer.EMPTY_STR), new State("4"));
            parcial.addTr(new Pair<State, Symbol>(new State("3"), new Symbol("a")), new State("5"));
            parcial.addTr(new Pair<State, Symbol>(new State("4"), new Symbol("b")), new State("6"));
            parcial.addTr(new Pair<State, Symbol>(new State("5"), Regexer.EMPTY_STR), new State("7"));
            parcial.addTr(new Pair<State, Symbol>(new State("6"), Regexer.EMPTY_STR), new State("7"));
            parcial.addTr(new Pair<State, Symbol>(new State("7"), Regexer.EMPTY_STR), new State("2"));
            parcial.addTr(new Pair<State, Symbol>(new State("8"), new Symbol("a")), new State("9"));
            parcial.addTr(new Pair<State, Symbol>(new State("9"), Regexer.EMPTY_STR), new State("10"));
            parcial.addTr(new Pair<State, Symbol>(new State("9"), Regexer.EMPTY_STR), new State("13"));
            parcial.addTr(new Pair<State, Symbol>(new State("10"), Regexer.EMPTY_STR), new State("11"));
            parcial.addTr(new Pair<State, Symbol>(new State("10"), Regexer.EMPTY_STR), new State("12"));
            parcial.addTr(new Pair<State, Symbol>(new State("11"), new Symbol("a")), new State("14"));
            parcial.addTr(new Pair<State, Symbol>(new State("12"), new Symbol("b")), new State("15"));
            parcial.addTr(new Pair<State, Symbol>(new State("13"), Regexer.EMPTY_STR), new State("16"));
            parcial.addTr(new Pair<State, Symbol>(new State("14"), Regexer.EMPTY_STR), new State("17"));
            parcial.addTr(new Pair<State, Symbol>(new State("15"), Regexer.EMPTY_STR), new State("17"));
            parcial.addTr(new Pair<State, Symbol>(new State("16"), Regexer.EMPTY_STR), new State("18"));
            parcial.addTr(new Pair<State, Symbol>(new State("17"), Regexer.EMPTY_STR), new State("18"));
            
            Automaton dfaParcial = parcial.toDfa();
            
            
            
            
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    @Test
    public void testbuildDFA() {
        try {
            Regexer r = new Regexer();
            String regex = "(a|b)*·(a|(b·b))*·c";
            DFABuilder instance = new DFABuilder(r);
        
            Automaton dfa = instance.build(regex); 
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    /*
    /**
     * Test of main method, of class Proyecto1.
     
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Proyecto1.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
}
