/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import src.Regexer;

/**
 *
 * @author asus
 */
public class DFA extends Automaton  {
    
    public DFA() {
        super();
    }
    
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

    @Override
    public boolean simulate(String input) {
        State s = super.initial_state;
        Symbol c;
        
        while (input.length() >= 1) {
            
            if (input.trim().equals("")) {
                c = Regexer.EMPTY_STR;
            } else {
                c = new Symbol(input.charAt(0));
            }
            
            if (move(s, c) != null) s = move(s, c);
            input = input.substring(1, input.length());
            
        }
        
        if (super.accepting.contains(s)) return true;
        return false;
    }
    
    @Override
    public Automaton toDfa() {
        return this;
    }

    private State move(State s, Symbol c) {
        Pair<State, Symbol> key = new Pair<State, Symbol>(s, c);
        Set<State> next = super.transition.get(key);
        
        if (next != null) {
            Iterator it = next.iterator();
            return (State) it.next();  
        }
        return null;
        
    }
    

    
    
}
