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

/**
 *
 * @author asus
 */
public class DFA extends Automaton {
    
    
    public DFA(State q0, Set syms, Set qs, Map trs, Set qfs) throws Exception {
            super(q0, syms, qs, trs, qfs);
            validate(trs);
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
    

    
    
}
