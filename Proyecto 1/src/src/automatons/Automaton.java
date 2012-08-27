/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
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
public abstract class Automaton {
    
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
     * Set of accepting states
     */
    protected Set<State> accepting;
    
    public Automaton() {
        states = new LinkedHashSet();
        transition = new LinkedHashMap();
        accepting = new LinkedHashSet();
    }
    

    
  
    
    
    public void absorb(Automaton other) {
        absorbStates(other.states());
        absorbTransitions(other.transition());
    }
    
    public void absorbStates(Set<State> other) {
        states.addAll(other);
    }
    
    public void absorbTransitions(Map<Pair<State, Symbol>, Set<State>> other) {
        transition.putAll(other);
    }
    
    public void setSymbols(Set<Symbol> s) {
        symbols = s;
    }
    
    public void addAcceptingState(State s) {
        accepting.add(s);
        addState(s);
    }
    
    public void changeInitialState(State s) {
        initial_state = s;
        addState(s);
    }
    
    public void addState(State s) {
        states.add(s);
    }
    
    public void addTransition(Pair<State, Symbol> p, State s) throws Exception {
        if (!states.contains(p.returnFirst())) {
            throw new Exception("Reference to non-existant state");
        }
        if (transition.containsKey(p)) {
            Set<State> value = transition.get(p);
            value.add(s);
            transition.put(p, value);
        } else {
            //symbols.add(p.returnSecond());
            transition.put(p, s.toSet());
        }
    }
    
    public void addTransition(Pair<State, Symbol> p, Set<State> s) throws Exception {
        if (!states.contains(p.returnFirst())) {
            throw new Exception("Reference to non-existant state");
        }
        if (transition.containsKey(p)) {
            Set<State> value = transition.get(p);
            value.addAll(s);
            transition.put(p, value);
        } else {
            //.add(p.returnSecond());
            transition.put(p, s);
        }
        
    }
    
    public void removeAcceptingState(State s) {
        accepting.remove(s);
    }
    
    public void removeState(State s) {
        accepting.remove(s);
        states.remove(s);
        if (initial_state.equals(s)) initial_state = null;
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
    
    public void printTable(String filename) throws Exception {
        
        
        File file = new File(filename);
        
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            
            
            output.write("Initial: ");
            output.newLine();
            printState(initial_state, output);
            
            output.write("Accepting: ");
            output.newLine();
            Iterator acc = accepting.iterator();
            while (acc.hasNext()) {
                State current = (State)acc.next();
                printState(current, output);
            }
            
            output.write("States:  ");
            output.newLine();
            Iterator sta = states.iterator();
            while (sta.hasNext()) {
                State current = (State)sta.next();
                printState(current, output);
            }
            
            output.write("Transitions:  ");
            output.newLine();
            printTransitions(output);
            
            
            output.close();
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    private void printState(State s, BufferedWriter w) throws Exception {
        if (s.set() != null) {
            Iterator iterator = s.set().iterator();
            w.write("          -");
            while (iterator.hasNext()) {
                State current = (State)iterator.next();
                w.write(current.id() + ", ");
            }
            w.newLine();
        } else {
            w.write("          -" +s.id() + "\n");
            w.newLine();
        }
        
    }
    
    private void printTransitions(BufferedWriter w) throws Exception {
        Set<Pair<State, Symbol>> keys = transition.keySet();
        Iterator k = keys.iterator();
        while (k.hasNext()) {
            w.write("          -State: ");
            Pair<State, Symbol> current = (Pair<State, Symbol>) k.next();
            printState(current.returnFirst(), w);
            w.write(" Symbol: " + current.returnSecond().toString() + "---> ");
            
            Set<State> value = transition.get(current);
            Iterator vals = value.iterator();
            while (vals.hasNext()) {
                printState((State) vals.next(), w);
            }
            w.newLine();
            w.write("------------------------------------");
            w.newLine();
        }
        
    }
    
    public abstract Automaton toDfa();
    
    public abstract boolean simulate(String input);
}
