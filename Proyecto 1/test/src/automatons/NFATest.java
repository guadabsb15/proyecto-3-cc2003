/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import src.Regexer;
import static org.junit.Assert.*;

/**
 *
 * @author asus
 */
public class NFATest {
    
    public NFATest() {
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
     * Test of eClosure method, of class NFA.
     */
    @Test
    public void testEClosure_State() {
        System.out.println("eClosure");
        State s1 = new State("1");
        State s2 = new State("2");
        State s3 = new State("3");
        State s4 = new State("4");
        Set<Symbol> sym = new LinkedHashSet<Symbol>();
        sym.add(new Symbol("a"));
        NFA instance = new NFA(sym);
        instance.addState(s1);
        instance.addState(s2);
        instance.addState(s3);
        instance.addState(s4);
        try {
            instance.addTransition(new Pair<State, Symbol>(s1, Regexer.EMPTY_STR), s2);
            instance.addTransition(new Pair<State, Symbol>(s2, Regexer.EMPTY_STR), s3);
            Set<State> expResult = new LinkedHashSet<State>();
            expResult.add(s1);
            expResult.add(s2);
            expResult.add(s3);
            //expResult.add(s4);
            Set result = instance.eClosure(s1);
            assertEquals(expResult, result);
            
            expResult = new LinkedHashSet<State>();
            expResult.add(s2);
            expResult.add(s3);
            result = instance.eClosure(s2);
            assertEquals(expResult, result);
            
            expResult = new LinkedHashSet<State>();
            expResult.add(s3);
            result = instance.eClosure(s3);
            assertEquals(expResult, result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

    }

    /**
     * Test of eClosure method, of class NFA.
     */
    @Test
    public void testEClosure_Set() {
        System.out.println("eClosure");
        Set<State> t = new LinkedHashSet();
        
        State s2 = new State("2");
        State s3 = new State("3");
        t.add(s2);
        t.add(s3);
        
        Set<Symbol> sym = new LinkedHashSet<Symbol>();
        sym.add(new Symbol("a"));
        NFA instance = new NFA(sym);
        instance.addState(s2);
        instance.addState(s3);
        
        Set<State> expResult = new LinkedHashSet<State>();
        expResult.add(s2);
        expResult.add(s3);
        Set result = instance.eClosure(t);
        assertEquals(expResult, result);
        
    }
}
