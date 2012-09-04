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
    
    public void addSymbol(Symbol s) {
        symbols.add(s);
    }
    
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
    
    private void printTransitions(BufferedWriter w) throws Exception {
        Set<Pair<State, Symbol>> keys = transition.keySet();
        Iterator k = keys.iterator();
        while (k.hasNext()) {
            w.write("(");
            Pair<State, Symbol> current = (Pair<State, Symbol>) k.next();
            printState(current.returnFirst(), w);
            w.write(", ");
            w.write(current.returnSecond().toString() + ", (");
            
            Set<State> value = transition.get(current);
            Iterator vals = value.iterator();
            while (vals.hasNext()) {
                printState((State) vals.next(), w);
                if (vals.hasNext()) w.write(", ");
            }
            w.write("))");
            
            if (k.hasNext()) w.write(", ");
        }
        w.write("}");
        
    }
    
    public void removeKey(Pair<State, Symbol> k) {
        transition.remove(k);
    }
    
    public abstract Automaton toDfa();
    
    public abstract boolean simulate(String input);
    
    public abstract Automaton minimize();
}
