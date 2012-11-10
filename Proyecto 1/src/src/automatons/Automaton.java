/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashSet;
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
        symbols = new LinkedHashSet();
    }
    
    /**
     * Absorbs the states and transitions of another automaton
     * @param other 
     */
    public void absorb(Automaton other) {
        absorbStates(other.states());
        absorbTransitions(other.transition());
    }
    
    /**
     * Absorbs the states from a set of states
     * @param other 
     */
    public void absorbStates(Set<State> other) {
        states.addAll(other);
    }
    
    /**
     * Absorbs the transitions from a given map
     * @param other 
     */
    public void absorbTransitions(Map<Pair<State, Symbol>, Set<State>> other) {
        transition.putAll(other);
    }
    
    /**
     * Sets the symbol set for the automaton
     * @param s 
     */
    public void setSymbols(Set<Symbol> s) {
        symbols = s;
    }
    
    /**
     * Adds an accepting state
     * @param s 
     */
    public void addAcc(State s) {
        accepting.add(s);
        addState(s);
    }
    
    /**
     * Changes the initial state of the automaton
     * @param s 
     */
    public void changeInitialState(State s) {
        initial_state = s;
        addState(s);
    }
    
    /**
     * Adds a state to the automaton
     * @param s 
     */
    public void addState(State s) {
        states.add(s);
    }
    
    public void attach(String s) {
        Iterator i = accepting.iterator();
        while (i.hasNext()) {
            ((State)i.next()).setAttached(s);
        }
        
    }
    
    /**
     * Adds a transition to the automaton
     * @param p
     * @param s
     * @throws Exception 
     */
    public void addTr(Pair<State, Symbol> p, State s) throws Exception {
        if (!states.contains(p.returnFirst())) {
            throw new Exception("Reference to non-existant state");
        }
        if (!symbols.contains(p.returnSecond())) {
            symbols.add(p.returnSecond());
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
    
    /**
     * Adds a transition to the automaton
     * @param p
     * @param s
     * @throws Exception 
     */
    public void intTr(Pair<State, Integer> p, State s) throws Exception {
        Pair<State, Symbol> newPair = new Pair(p.returnFirst(), new Symbol(p.returnSecond()));
        if (!states.contains(newPair.returnFirst())) {
            throw new Exception("Reference to non-existant state");
        }
        if (!symbols.contains(newPair.returnSecond())) {
            symbols.add(new Symbol(newPair.returnSecond()));
        }
        if (transition.containsKey(newPair)) {
            Set<State> value = transition.get(newPair);
            value.add(s);
            transition.put(newPair, value);
        } else {
            //symbols.add(p.returnSecond());
            transition.put(newPair, s.toSet());
        }
    }
    
    /**
     * Adds a transition to the automaton
     * @param p
     * @param s
     * @throws Exception 
     */
    public void addTr(Pair<State, Symbol> p, Set<State> s) throws Exception {
        if (!states.contains(p.returnFirst())) {
            throw new Exception("Reference to non-existant state");
        }
        if (transition.containsKey(p)) {
            Set<State> value = transition.get(p);
            value.addAll(s);
            transition.put(p, value);
        } else {
            transition.put(p, s);
        }
    }
    
    /**
     * Adds a transition to the automaton
     * @param p
     * @param s
     * @throws Exception 
     */
    public void intTr(Pair<State, Integer> p, Set<State> s) throws Exception {
        Pair<State, Symbol> newPair = new Pair(p.returnFirst(), new Symbol(p.returnSecond()));
        if (!states.contains(newPair.returnFirst())) {
            throw new Exception("Reference to non-existant state");
        }
        if (transition.containsKey(newPair)) {
            Set<State> value = transition.get(newPair);
            value.addAll(s);
            transition.put(newPair, value);
        } else {
            transition.put(newPair, s);
        }
    }
    
    /**
     * Removes an accepting state
     * @param s 
     */
    public void removeAcceptingState(State s) {
        accepting.remove(s);
    }
    
    /**
     * Removes a state from the automaton
     * @param s 
     */
    public void removeState(State s) {
        accepting.remove(s);
        states.remove(s);
        if (initial_state.equals(s)) initial_state = null;
    }
     
    /**
     * Returns the states of the automaton
     * @return 
     */
    public Set<State> states() {
        return states;
    }
    
    /**
     * Returns the accepting states
     * @return 
     */
    public Set<State> accepting() {
        return accepting;
    }
    
    /**
     * Returns the transitions map
     * @return 
     */
    public Map<Pair<State, Symbol>, Set<State>> transition() {
        return transition;
    }
    
    /**
     * Returns the initial state
     * @return 
     */
    public State initial_state() {
        return initial_state;
    }
    
    /**
     * Returns the symbols set of the automaton
     * @return 
     */
    public Set<Symbol> symbols() {
        return symbols;
    }
    
    /**
     * Writes the components of the automaton to a file
     * @param filename
     * @throws Exception 
     */    
    public void writeFile(String filename) throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(filename), "UTF-8"));
        try {
            out.write("ESTADOS = {");
            Iterator sts = states.iterator();
            while (sts.hasNext()) {
                printState((State) sts.next(), out);
                if (sts.hasNext()) out.write(", ");
            }
            out.write("}");
            out.newLine();
            
            out.write("SÍMBOLOS = {");
            Iterator sym = symbols.iterator();
            while (sym.hasNext()) {
                Symbol current = (Symbol) sym.next();
                out.write(current.toString());
                if (sym.hasNext()) out.write(", ");
            }
            out.write("}");
            out.newLine();
            
            out.write("INICIO = {");
            printState(initial_state, out);
            out.write("}");
            out.newLine();
            
            out.write("ACEPTACIÓN = {");
            Iterator acc = accepting.iterator();
            while (acc.hasNext()) {
                State current = (State) acc.next();
                printState(current, out);
                if (acc.hasNext()) out.write(", ");
            }
            out.write("}");
            out.newLine();
            
            out.write("TRANSICIÓN = {");
            printTransitions(out);     
            out.close();
            
        } finally {
            out.close();
        }
    }
    
    /**
     * Adds a symbol to the automaton
     * @param s 
     */
    public void addSymbol(Symbol s) {
        symbols.add(s);
    }
    
    /**
     * Writes a state to a file
     * @param s
     * @param w
     * @throws Exception 
     */
    private void printState(State s, Writer w) throws Exception {
        if (s.set() != null) {
            Iterator iterator = s.set().iterator();
            while (iterator.hasNext()) {
                State current = (State)iterator.next();
                w.write(current.id());
                if (iterator.hasNext()) w.write("-"); 
            }
            //w.newLine();
        } else {
            w.write(s.id());
        }
        
    }
    
    /**
     * Writes the transition functions of the automaton to a file
     * @param w
     * @throws Exception 
     */
    private void printTransitions(BufferedWriter w) throws Exception {
        Set<Pair<State, Symbol>> keys = transition.keySet();
        Iterator k = keys.iterator();
        while (k.hasNext()) {
            w.write("(");
            Pair<State, Symbol> current = (Pair<State, Symbol>) k.next();
            printState(current.returnFirst(), w);
            w.write(", ");
            w.write(current.returnSecond().toString() + ", [");
            
            Set<State> value = transition.get(current);
            Iterator vals = value.iterator();
            while (vals.hasNext()) {
                printState((State) vals.next(), w);
                if (vals.hasNext()) w.write(", ");
            }
            w.write("])");
            
            if (k.hasNext()) w.write(", ");
        }
        w.write("}");
        
    }
    
    /**
     * Removes a key from the transition functions map
     * @param k 
     */
    public void removeKey(Pair<State, Symbol> k) {
        transition.remove(k);
    }
    
    public void absorbSymbols(Automaton other) {
        symbols.addAll(other.symbols());
    }
    
    public void absorbAccepting(Automaton other) {
        accepting.addAll(other.accepting());
    }
    
    /**
     * Convert the automaton to a deterministic finite automaton
     * @return
     * @throws Exception 
     */
    public abstract Automaton toDfa() throws Exception;
    
    /**
     * Simulation of a given string
     * @param input
     * @return 
     */
    public abstract boolean simulate(String input);
    
    /**
     * Minimization algorithm
     * @return 
     */
    public abstract Automaton minimize();
}
