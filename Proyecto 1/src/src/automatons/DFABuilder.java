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
 * Builds non-deterministic finite automata using the direct construction method
 * @author asus
 */
public class DFABuilder {
    
    /**
     * Regular expression analyzer
     */
    private Regexer regexer;
    
    /**
     * Index to identifiy each new state
     */
    private int index;
    
    /**
     * Set of symbols of the automata
     */
    private Set symbols;
    
    /**
     * Map that holds the computed follopos
     */
    private Map<String, Set<State>> followpos;
    
    /**
     * Holds the labels associated to each symbol
     */
    private Map<Symbol, Set<State>> table;
    
    /**
     * Label for the accepting position
     */
    private State acceptingPos;
    
    /**
     * Class constructor
     * @param rgxr 
     */
    public DFABuilder(Regexer rgxr) {
        index = 1;
        regexer = rgxr;
        followpos = new LinkedHashMap<String, Set<State>>();
        table = new LinkedHashMap<Symbol, Set<State>>();
        acceptingPos = null;
        symbols = null;
    }
    
    /**
     * Builds the automaton for the regular expression introduced as input
     * @param regex
     * @return
     * @throws Exception 
     */
    public Automaton build(String regex) throws Exception {
        index = 1;
        followpos = new LinkedHashMap<String, Set<State>>();
        table = new LinkedHashMap<Symbol, Set<State>>();
        acceptingPos = null;
        symbols = null;
        
        try {
            if (regex.trim().equals("")) {
                regex = Regexer.EMPTY_STR.toString();
            }
            String extended = "(" + regex + ")#";
            regexer.evaluate(extended);
            symbols = regexer.symbols();
            symbols.remove(Regexer.EMPTY_STR);
            BinaryTree<Symbol> tree = regexer.returnAST();
            BinaryTree<Symbol> valueTree = new BinaryTree(tree.value());
            label(tree);
            computeFollowpos(tree);
            return construct(tree);
            //return new DFA();
        } catch (Exception e) {
            throw e;
        }    
    }

    /**
     * Labels the leaves with a position
     * @param tree 
     */
    public void label(BinaryTree<Symbol> tree ) {
        
        if ((tree.left().value() == null) && (tree.right().value() == null)) {
            State s = new State(Integer.toString(index));
            index++;
            Symbol current = (Symbol) tree.value();
            Symbol replacement = new Symbol(current);
            replacement.setState(s);
            tree.setValue(replacement);
            
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
    
    /**
     * Returns whether a node is a leaf
     * @param node
     * @return 
     */
    public boolean isLeaf(BinaryTree<Symbol> node) {
        if ((node.left().value() == null) && (node.right().value() == null)) return true;
        return false;
    }
    
    /**
     * Nullable function
     * @param node
     * @return 
     */
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
    
    /**
     * Firstpos function
     * @param node
     * @return 
     */
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
    
    /**
     * Lastpos function
     * @param node
     * @return 
     */
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
    
    /**
     * Followpos function
     * @param node 
     */
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
                followpos.put(currentKey, previous);    
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
    
    /**
     * Computes the followpos function for a regular expression's tree
     * @param node 
     */
    public void computeFollowpos(BinaryTree<Symbol> node) {
        followpos(node);
        if (node.left().value() != null) computeFollowpos(node.left());
        if (node.right().value() != null) computeFollowpos(node.right());
    }

    /**
     * Builds the automaton applying the direct construction algorithm
     * @param root
     * @return 
     */
    public Automaton construct(BinaryTree<Symbol> root) {
        Set<State> dStates = new LinkedHashSet();
        Map<Pair<State, Symbol>, Set<State>> transitions = new LinkedHashMap();
        Automaton dfa = new DFA();
         
        State initial = new State(firstpos(root));
        if (firstpos(root).contains(acceptingPos)) dfa.addAcceptingState(initial);
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
