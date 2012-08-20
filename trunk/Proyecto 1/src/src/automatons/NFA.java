/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author asus
 */
public class NFA extends Automaton {
    
    public NFA(State q0, Set syms, Set qs, Map trs, Set qfs) throws Exception {
        super(q0, syms, qs, trs, qfs);  
    }
    
    public NFA(Set<Symbol> syms) {
        super(syms);
    }
      
}
