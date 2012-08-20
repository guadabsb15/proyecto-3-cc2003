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

        
        
/**
 *
 * @author asus
 */
public abstract class Automaton {
    
    /**
     * 
     */
    private Setter setter = new Setter();

    /**
     * Set of states
     */
    protected Set<State> states;
    
    /**
     * Set of symbols
     */
    protected Set<Symbol> symbols;
    
    /**
     * Transition function
     */
    protected Map<Pair<State, Symbol>, Set<State>> transition;
    
    /**
     * Initial state of the automaton
     */
    protected State initial_state;
    
    /**
     * Current states of the automaton
     */
    protected Set<State> current_states;
    
    /**
     * Set of accepting states
     */
    protected Set<State> accepting;
    
    public Automaton(Set<Symbol> syms) {
        symbols = syms;
        states = new LinkedHashSet();
        transition = new LinkedHashMap();
        accepting = new LinkedHashSet();
    }
    
    /**
     * Class constructor
     */
    public Automaton(State q0, Set syms, Set qs, Map trs, Set qfs) {
        initial_state = q0;
        symbols = syms;
        states = qs;
        transition = trs;
        accepting = qfs;
        
        current_states = new LinkedHashSet();
        current_states.add(initial_state);
        states.add(initial_state);
        states.addAll(accepting);
    }
    
    public Set<State> next(Pair<State, Symbol> k) {
        //current_states = transition.get(k);
        if (transition.containsKey(k)) {
            return transition.get(k);
        }
        LinkedHashSet<State> s = new LinkedHashSet<State>();
        s.add(k.returnFirst());
        return s;
        
    }
    
    
    public void go(Symbol sym) throws Exception {
        if (symbols.contains(sym)) {
            Iterator<State> setIterator = current_states.iterator();
            Set newCurrent = new LinkedHashSet();
            while (setIterator.hasNext()) {
                State current = setIterator.next();
                Pair p = new Pair(current, sym);
            //System.out.println(transition.containsKey(p));
                LinkedHashSet<State> n = new LinkedHashSet<State>(next(p));
                newCurrent.addAll(n);
            }
            current_states = newCurrent;
        } else {
            throw new Exception("A symbol that does not exist was provided as input");
        }
    }
    
    public void go(String str) throws Exception {
        while (str.length() >= 1) {
            Symbol s = new Symbol(str.charAt(0));
            go(s);
            str = str.substring(1, str.length());
        }
    }
    
    public void absorb(Automaton other) {
        states.addAll(other.states());
        transition.putAll(other.transition());
    }
    
    public void addAcceptingState(State s) {
        accepting.add(s);
        states.add(s);
    }
    
    public void changeInitialState(State s) {
        initial_state = s;
        states.add(s);
    }
    
    public void addTransition(Pair<State, Symbol> p, Set<State> s) {
        transition.put(p, s);
    }
    
    public void removeAcceptingState(State s) {
        accepting.remove(s);
    }
    
    public void removeState(State s) {
        accepting.remove(s);
        states.remove(s);
        if (initial_state.equals(s)) initial_state = null;
    }
    
    public Set<State> current_states() {
        return current_states;
    }
    
    public Set<State> states() {
        return states;
    }
    
    public Set<State> accepting() {
        return accepting;
    }
    
    public Map<Pair<State, Symbol>, Set<State>> transition() {
        return transition;
    }
    
    public State initial_state() {
        return initial_state;
    }
    
    public Set<Symbol> symbols() {
        return symbols;
    }
}
