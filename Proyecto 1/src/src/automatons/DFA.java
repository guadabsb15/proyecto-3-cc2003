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
import src.Regexer;

/**
 *
 * @author asus
 */
public class DFA extends Automaton  {
    
    /**
     * Dead state for the simulation
     */
    private State deadState = new State("dead");
    
    /**
     * Class constructor
     */
    public DFA() {
        super();
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
        input = input.replace(" ", "");
        State s = super.initial_state;
        Symbol c;
        
        while (input.length() >= 1) {
            
            if (input.trim().equals("")) {
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
    public Automaton minimize() {
        
        Set<Set<State>> partitions = new LinkedHashSet();
        
        Set<State> initial = states;
        initial.removeAll(accepting);
        partitions.add(initial);
        partitions.add(accepting);
        
        Set<Set<State>> newPartition = new LinkedHashSet();
        
        while (!partitions.equals(newPartition)) {
            
            Map<State, Set<State>> table = new LinkedHashMap();
            Iterator parts = partitions.iterator();
            while (parts.hasNext()) {
                Set<State> currentValue = (Set<State>) parts.next();
                Iterator keys = currentValue.iterator();
                while (keys.hasNext()) {
                    table.put(((State) keys.next()), currentValue);
                }
            }
            
            newPartition = partitions;
            
            parts = partitions.iterator();
            
            
            
            while (parts.hasNext()) {
                Set<State> G = (Set<State>) parts.next();
                
                Iterator subgroupStates = G.iterator();
                while (subgroupStates.hasNext()) {
                    State currentState = (State) subgroupStates.next();
                }
                
            }  
        }
        
        
        return this;
    }
    
    
}
