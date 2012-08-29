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
import struct.BTPostOrderIterator;
import struct.BinaryTree;

/**
 *
 * @author asus
 */
public class DFABuilder {
    
    private Regexer regexer;
    
    private int index;
    
    private Set symbols;
    
    private Automaton nfa;
    
    private Map<String, Set<State>> followpos;
    
    private Map<Symbol, Set<State>> table;
    
    private State acceptingPos;
    
    public DFABuilder(Regexer rgxr) {
        index = 1;
        regexer = rgxr;
        followpos = new LinkedHashMap<String, Set<State>>();
        table = new LinkedHashMap<Symbol, Set<State>>();
        
    }
    
    public Automaton build(String regex) throws Exception {
        try {
            String extended = regex + "·#";
            regexer.evaluate(extended);
            symbols = regexer.symbols();
            BinaryTree<Symbol> tree = regexer.returnAST();
            
            label(tree);
            computeFollowpos(tree);
            return construct(tree);
            //return new DFA();
        } catch (Exception e) {
            throw e;
        }    
    }

    public void label(BinaryTree<Symbol> tree) {
        
        if ((tree.left().value() == null) && (tree.right().value() == null)) {
            State s = new State(Integer.toString(index));
            index++;
            tree.value().setState(s);
            
            Symbol key = tree.value();
            if (key.id() == ("#".charAt(0))) acceptingPos = s;
            
            if (table.containsKey(key)) {
                Set<State> val = table.get(key);
                val.add(s);
                table.put(key, val);
            } else {
                Set<State> val = new LinkedHashSet();
                val.add(s);
                table.put(key, val);
            }          
            return;
            
        }  else {
            if (tree.left().value() != null) {
                label(tree.left());
            }
            if (tree.right().value() != null) {
                label(tree.right());
            }
            return;
        }
        
    }
    
    public boolean isLeaf(BinaryTree<Symbol> node) {
        if ((node.left().value() == null) && (node.right().value() == null)) return true;
        return false;
    }
    
    public boolean nullable(BinaryTree<Symbol> node) {
        if (isLeaf(node) && node.value().equals(Regexer.EMPTY_STR)) {
            return true;
        } else if (isLeaf(node) && (node.value().associated() != null)) {
            return false;
        } else if (node.value().equals(Regexer.OR)) {
            return (nullable(node.left()) || nullable(node.right()));
        } else if (node.value().equals(Regexer.CONCATENATION)) {
            return (nullable(node.left()) && nullable(node.right()));
        } else if (node.value().equals(Regexer.KLEENE)) {
            return true;
        } else {
            return false;
        }
    }
    
    public Set<State> firstpos(BinaryTree<Symbol> node) {
        Set<State> result = new LinkedHashSet();
        if (isLeaf(node) && node.value().equals(Regexer.EMPTY_STR)) {
            return result;
        } else if (isLeaf(node) && (node.value().associated() != null)) {
            result.add(node.value().associated());
            return result;
        } else if (node.value().equals(Regexer.OR)) {
            result.addAll(firstpos(node.left()));
            result.addAll(firstpos(node.right()));
            return result;
        } else if (node.value().equals(Regexer.CONCATENATION)) {
            if (nullable(node.left())) {
                result.addAll(firstpos(node.left()));
                result.addAll(firstpos(node.right()));
                return result;
            } else {
                result.addAll(firstpos(node.left()));
                return result;
            }
        } else if (node.value().equals(Regexer.KLEENE)) {
            result.addAll(firstpos(node.left()));
            return result;
        } else {
            return null;
        }
    }
    
    public Set<State> lastpos(BinaryTree<Symbol> node) {
        Set<State> result = new LinkedHashSet();
        if (isLeaf(node) && node.value().equals(Regexer.EMPTY_STR)) {
            return result;
        } else if (isLeaf(node) && (node.value().associated() != null)) {
            result.add(node.value().associated());
            return result;
        } else if (node.value().equals(Regexer.OR)) {
            result.addAll(lastpos(node.left()));
            result.addAll(lastpos(node.right()));
            return result;
        } else if (node.value().equals(Regexer.CONCATENATION)) {
            if (nullable(node.right())) {
                result.addAll(lastpos(node.left()));
                result.addAll(lastpos(node.right()));
                return result;
            } else {
                result.addAll(lastpos(node.right()));
                return result;
            }
        } else if (node.value().equals(Regexer.KLEENE)) {
            result.addAll(lastpos(node.left()));
            return result;
        } else {
            return null;
        }
    }
    
    public void followpos(BinaryTree<Symbol> node) {
        if (isLeaf(node)) return;
        
        if (node.value().equals(Regexer.CONCATENATION)) {
            Set<State> positions = lastpos(node.left());
            Set<State> newValues = firstpos(node.right());
            
            Iterator iterator = positions.iterator();
            while (iterator.hasNext()) {
                State current = (State) iterator.next();
                String currentKey = current.id();
                
                Set<State> previous = followpos.get(currentKey);
                if (previous == null) {
                    previous = new LinkedHashSet<State>();
                }
                previous.addAll(newValues);
                followpos.put(currentKey, newValues);    
            }
            
            
        } else if (node.value().equals(Regexer.KLEENE)) {
            Set<State> positions = lastpos(node);
            Set<State> newValues = firstpos(node);
            
            Iterator iterator = positions.iterator();
            while (iterator.hasNext()) {
                State current = (State) iterator.next();
                String currentKey = current.id();
                
                Set<State> previous = followpos.get(currentKey);
                if (previous == null) {
                    previous = new LinkedHashSet<State>();
                }
                newValues.addAll(previous);
                followpos.put(currentKey, newValues);    
            }
        }
    }
    
    public void computeFollowpos(BinaryTree<Symbol> node) {
        followpos(node);
        if (node.left().value() != null) computeFollowpos(node.left());
        if (node.right().value() != null) computeFollowpos(node.right());
    }

    public Automaton construct(BinaryTree<Symbol> root) {
        Set<State> dStates = new LinkedHashSet();
        Map<Pair<State, Symbol>, Set<State>> transitions = new LinkedHashMap();
        Automaton dfa = new DFA();
         
        State initial = new State(firstpos(root));
        dfa.changeInitialState(initial);
        Stack<State> unmarked = new Stack<State>();
        unmarked.push(initial);
        
        while (!unmarked.isEmpty()) {
            Set<State> S = unmarked.pop().set();
            State sState = new State(S);
            
            Iterator syms = symbols.iterator();
            while (syms.hasNext()) {
                Symbol a = (Symbol) syms.next();
                Set<State> U = new LinkedHashSet();
                Set<State> positions = table.get(a);
                
                Iterator p = S.iterator();
                
                while (p.hasNext()) {
                    State current = (State) p.next();
                    if ((positions!=null) && (positions.contains(current))) {
                        Set<State> toAdd = followpos.get(current.id());
                        if (toAdd != null) U.addAll(toAdd);
                    }    

                }
                
                if (!U.isEmpty() && !S.isEmpty()) {
                   State uState = new State(U);
                    boolean added = dStates.add(uState);
                    if (added) {
                        unmarked.push(uState);
                    }
                    if (U.contains(acceptingPos)) dfa.addAcceptingState(uState);
                    Pair<State, Symbol> k = new Pair<State, Symbol>(sState, a); 
                    transitions.put(k, uState.toSet()); 
                }               
            }     
        }
        
        dfa.absorbStates(dStates);
        dfa.absorbTransitions(transitions);
        dfa.setSymbols(symbols);
        return dfa;

    }
    
}