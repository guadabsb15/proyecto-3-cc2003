/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import src.Regexer;

/**
 *
 * @author asus
 */
public class DFA extends Automaton  {
    
    /**
     * Dead state for the simulation
     */
    public final static State deadState = new State("dead");
    
    private State lastState;
    
    /**
     * Class constructor
     */
    public DFA() {
        super();
    }
    
    public State lastState() {
        return lastState;
    }
    
    /**
     * Verifies that transitions are correct according to the definition of a DFA
     * @param transitions
     * @return
     * @throws Exception 
     */
    private boolean validate(Map transitions) throws Exception {
        Collection<Set> values = transitions.values();
        Iterator<Set> iter = values.iterator();
        while (iter.hasNext()) {
            Set s = iter.next();
            if (s.size() != 1) {
                throw new Exception("Multiple transitions cannot be defined for a Deterministic Finite Automaton");
            }
        }
        return true;
       
    }

    /**
     * Simulates the string provided as an input
     * @param input
     * @return 
     */
    @Override
    public boolean simulate(String input) {
        //input = input.replace(" ", "");
        State s = super.initial_state;
        Symbol c;
        
        while (input.length() >= 1) {
            
            if (input.equals("")) {
                c = Regexer.EMPTY_STR;
            } else {
                c = new Symbol(input.charAt(0));
            }
            
            if (c.equals((Regexer.EMPTY_STR))) {
            }
            else if (move(s, c) != null) {
                s = move(s, c);
            } else {
                s = deadState;
            }
            input = input.substring(1, input.length());
            
        }
        
        lastState = s;
        if (super.accepting.contains(s)) {
            
            return true;
        }
        return false;
    }
    
    @Override
    public Automaton toDfa() throws Exception {
        return this;
    }

    /**
     * Move function for a DFA
     * @param s
     * @param c
     * @return 
     */
    private State move(State s, Symbol c) {
        Pair<State, Symbol> key = new Pair<State, Symbol>(s, c);
        Set<State> next = super.transition.get(key);
        
        if (next != null) {
            Iterator it = next.iterator();
            return (State) it.next();  
        }
        return null;
        
    }
    
    /**
     * Implements the DFA minimization algorithm
     * @return 
     */
    @Override
    public DFA minimize() {
        
        Set<Set<State>> P = new ConcurrentSkipListSet();
        
        Set<State> initial = states;
        initial.removeAll(accepting);
        P.add(initial);
        P.add(accepting);
        
        Set<Set<State>> W = new ConcurrentSkipListSet();
        W.addAll(P);
        
        while (!W.isEmpty()) {
            Iterator i = W.iterator();
            Set<State> A = (Set<State>) i.next();
            W.remove(A);
            Iterator syms = super.symbols.iterator();
            while (syms.hasNext()) {
                Symbol c = (Symbol) syms.next();
                Set<State> X = new LinkedHashSet();
                Iterator keys = super.transition.keySet().iterator();
                while (keys.hasNext()) {
                    Pair<State, Symbol> current = (Pair<State, Symbol>) keys.next();
                    if (current.returnSecond().equals(c) && A.contains(transition.get(current))) X.add((State)transition.get(current));
                }
                Iterator ways = P.iterator();
                while (ways.hasNext()) {
                    Set<State> Y = (Set<State>) ways.next();
                    Set<State> intersection = new ConcurrentSkipListSet<State>();
                    intersection.addAll(X);
                    intersection.retainAll(Y);
                    if (!intersection.isEmpty()) {
                        P.remove(Y);
                        P.add(intersection);
                        Set<State> difference = new ConcurrentSkipListSet<State>();
                        difference.addAll(Y);
                        difference.removeAll(X);
                        P.add(difference);
                        
                        if (W.contains(Y)) {
                            W.remove(Y);
                            W.add(intersection);
                            W.add(difference);
                        } else {
                            if (intersection.size() <= difference.size()) {
                                W.add(intersection);
                            } else {
                                W.add(difference);
                            }
                        }
                        
                        
                    }
                }
            }
            
        }
        
        DFA minimized = new DFA();
        
        return minimized;
        
        
    }

    
    
    
}
