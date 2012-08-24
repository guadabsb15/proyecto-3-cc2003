/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
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
public class AutomatonTest {
    
    public AutomatonTest() {
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
     * Test of next method, of class Automaton.
     */
    @Test
    public void testDFA() {
        System.out.println("DFA");
        
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");
        State q4 = new State("q4");
        
        LinkedHashSet qs = new LinkedHashSet();
        qs.add(q1);
        qs.add(q3);
        
        LinkedHashSet qfs = new LinkedHashSet();
        qfs.add(q2);
        qfs.add(q4);
        
        Symbol a = new Symbol("a");
        Symbol b = new Symbol("b");
        Symbol c = new Symbol("c");
        LinkedHashSet lang = new LinkedHashSet();
        lang.add(a);
        lang.add(b);
        lang.add(c);
        
        LinkedHashMap<Pair<State, Symbol>, Set<State>> tr = new LinkedHashMap<Pair<State, Symbol>, Set<State>>();
        
        LinkedHashSet<State> states1 = new LinkedHashSet<State>();
        states1.add(q2);
        states1.add(q3);
        
        tr.put(new Pair(q0, a), q2.toSet());
        tr.put(new Pair(q0, b), q3.toSet());
        tr.put(new Pair(q3, c), q4.toSet());
        
        try {
            Automaton instance = new DFA(lang);
            String testString = "bc";
            System.out.println("Test string: " + testString);
            instance.go(testString);  
            
            Set s = instance.current_states();
            Iterator it = s.iterator();
            System.out.println("The automaton is on the following state(s): ");
            while (it.hasNext()) {
                System.out.println(it.next().toString());
            }
            System.out.println();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
   
    @Test
    public void testNFA() {
        System.out.println("NFA");
        
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");
        State q4 = new State("q4");
        
        LinkedHashSet qs = new LinkedHashSet();
        qs.add(q1);
        qs.add(q3);
        
        LinkedHashSet qfs = new LinkedHashSet();
        qfs.add(q2);
        qfs.add(q4);
        
        Symbol a = new Symbol("a");
        Symbol b = new Symbol("b");
        Symbol c = new Symbol("c");
        LinkedHashSet lang = new LinkedHashSet();
        lang.add(a);
        lang.add(b);
        lang.add(c);
        
        LinkedHashMap<Pair<State, Symbol>, Set<State>> tr = new LinkedHashMap<Pair<State, Symbol>, Set<State>>();
        LinkedHashSet<State> states1 = new LinkedHashSet<State>();
        states1.add(q2);
        states1.add(q3);
        tr.put(new Pair(q0, a), states1);
        tr.put(new Pair(q0, b), q3.toSet());
        tr.put(new Pair(q3, c), q4.toSet());
        
        try {
            Automaton instance = new NFA();
            String testString = "al";
            System.out.println("Test string: " + testString);
            instance.go(testString);   
            
            Set s = instance.current_states();
            Iterator it = s.iterator();
            System.out.println("The automaton is on the following state(s): ");
            while (it.hasNext()) {
                System.out.println(it.next().toString());
            }
            System.out.println();
           
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
