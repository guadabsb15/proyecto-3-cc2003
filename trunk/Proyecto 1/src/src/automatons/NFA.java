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
    
    public NFA() {
        super();
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
    
    
    public Set<State> eClosure(State s) {
        Set<State> set = new LinkedHashSet();
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
    

    
    public Set<State> move(Set<State> T, Symbol a) {
        Iterator iterator = T.iterator();
        Set<State> result = new LinkedHashSet();
        
        while (iterator.hasNext()) {
            State current = (State) iterator.next();
            Pair<State, Symbol> key = new Pair<State, Symbol>(current, a);
            if (super.transition.containsKey(key)) {
                result.addAll(super.transition.get(key));
            }
        }
        
        return result;
        
    }
    
    public boolean simulate(String string) {
        Set<State> s = eClosure(super.initial_state);
        Symbol c;
        
        while (string.length() >= 1) {
            
            if (string.trim().equals("")) {
                c = Regexer.EMPTY_STR;
            } else {
                c = new Symbol(string.charAt(0));
            }
            s = eClosure(move(s, c));
            string = string.substring(1, string.length());
            
        }
        
        Set<State> intersection = new LinkedHashSet(s);
        intersection.retainAll(super.accepting);
        if (!intersection.isEmpty()) {
            return true;
        } else {
            return false;
        }
        
    }
      
}
