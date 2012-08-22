/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import src.Regexer;

/**
 *
 * @author asus
 */
public class NFA extends Automaton {
    
    //Stack<State> todo = new Stack<State>();
    
    public NFA(Set<Symbol> syms) {
        super(syms);
    }
    
    @Override
    public void addState(State s) {
        if (!super.states.contains(s)) {
            super.addState(s);
            Pair selfEpsilon = new Pair(s, Regexer.EMPTY_STR);
            try {
                super.addTransition(selfEpsilon, s);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }
        
    }
    
    public boolean simulate (String input) {
        
        return false;
    }
    
    public Set<State> eClosure(State s) {
        Set<State> set = new LinkedHashSet();
        //set.addAll(c);
        Set<State> visited = new LinkedHashSet();
        Pair<State, Symbol> key = new Pair<State, Symbol>(s, Regexer.EMPTY_STR);
        Set<State> value = super.transition.get(key);
        set.addAll(value);
        visited.add(s);
        
        if (value.size() == 1) {
            return set;
        } else {
            Iterator ite = value.iterator();
            while (ite.hasNext()) {
                State next = (State) ite.next();
                if (!visited.contains(next)) {
                    set.addAll(eClosure(next));
                    visited.add(next);
                }
                
            }
            return set;
        }
    }
    
    public Set<State> eClosure(Set<State> T) {
        Set<State> result = new LinkedHashSet();
        Stack<State> todo = new Stack<State>();
        
        Iterator ite = T.iterator();
        while (ite.hasNext()) {
            todo.push((State)ite.next());
        }
        
        while (!todo.isEmpty()) {
            result.addAll(eClosure(todo.pop()));
        }
        return result;
    }
    

    
    //private Set<State> move(Set<State> T, Symbol a)
      
}
