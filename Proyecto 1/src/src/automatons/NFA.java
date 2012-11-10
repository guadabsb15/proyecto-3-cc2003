/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import java.util.Iterator;
import java.util.LinkedHashMap;
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
    
    /**
     * Class constructor
     */
    public NFA() {
        super();
    }
    
    /**
     * Adds a state to the automaton
     * @param s 
     */
    @Override
    public void addState(State s) {
        if (!super.states.contains(s)) {
            super.addState(s);
            Pair selfEpsilon = new Pair(s, Regexer.EMPTY_STR);
            try {
                super.addTr(selfEpsilon, s);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }  
    }
    
    @Override
    public void absorb(Automaton other) {
        Iterator s = other.states().iterator();
        while (s.hasNext()) {
            this.addState((State) s.next());
        } 
        absorbTransitions(other.transition());
    }
       
    /**
     * Epsilon closure function
     * @param s
     * @return 
     */
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
    
    /**
     * Epsilon closure function for a set
     * @param T
     * @return 
     */
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
    
    /**
     * Move function
     * @param T
     * @param a
     * @return 
     */
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
    
    /**
     * Simulates the string introduced an input, returning true if it belongs to the language accepted by the automaton (or false if it does not belong)
     * @param string
     * @return 
     */
    @Override
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
    
    /**
     * Converts the NFA to a DFA applying the subset construction algorithm
     * @return
     * @throws Exception 
     */
    @Override
    public Automaton toDfa() throws Exception {
        if (this == null) throw new Exception("No se puede convertir a DFA");
        Automaton dfa = new DFA();
        Map<Pair<State, Symbol>, Set<State>> transitions = new LinkedHashMap();
        
        Stack<Set<State>> unmarked = new Stack<Set<State>>();
        Set<State> initSet = eClosure(super.initial_state);
        unmarked.push(initSet);
        
        State initial = new State(eClosure(super.initial_state));
        dfa.changeInitialState(initial);
        Iterator acc = super.accepting.iterator();
        while (acc.hasNext()) {
            State current = (State) acc.next();
            if (initSet.contains(current)) {
                dfa.addAcc(initial);
                break;
            }
        }
        
        Set<State> dStates = new LinkedHashSet<State>();
        dStates.add(initial);

        
        while (!unmarked.isEmpty()) {
            Iterator syms = super.symbols.iterator();
            Set<State> T = unmarked.pop();
            while (syms.hasNext()) {
                boolean accepting = false;
                Symbol a = (Symbol) syms.next();
                
                State tState = new State(T);
                Set<State> U = eClosure(move(T, a));
                if (U.size() != 0) {
                    State uState = new State(U);
                    boolean added = dStates.add(uState);
                    if (added) {
                        unmarked.push(uState.set());
                    }
                    Iterator it = U.iterator();
                    while (it.hasNext()) {
                        State current = (State) it.next();
                        if (super.accepting().contains(current)) {
                            accepting = true;
                            uState.setAttached(current.attached());
                        }
                    }
                    if (accepting) dfa.addAcc(uState);
                    transitions.put(new Pair<State, Symbol>(tState, a), uState.toSet());

                }
                         
            }
        }
        
        dfa.absorbStates(dStates);
        dfa.absorbTransitions(transitions);
        dfa.setSymbols(super.symbols);
        return dfa;
    }
    
    
    
    @Override
    public Automaton minimize() {
        return this;
    }
      
}
