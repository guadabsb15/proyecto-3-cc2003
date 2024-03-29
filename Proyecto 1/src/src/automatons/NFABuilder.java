/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;
import src.Regexer;
import struct.BTPostOrderIterator;
import struct.BinaryTree;

/**
 *
 * @author asus
 */
public class NFABuilder {
    
    /**
     * Stack for the construction process
     */
    private Stack<Automaton> evalStack;
    
    /**
     * Regular expression analyzer for tree construction
     */
    private Regexer regexer;
    
    /**
     * Index to identify new states 
     */
    private int index;
    
    /**
     * Symbols of the automaton
     */
    private Set<Symbol> symbols;
    
    /**
     * Built NFA
     */
    private Automaton nfa;
    
    /**
     * Class constructor
     * @param rgxr 
     */
    public NFABuilder(Regexer rgxr) {
        evalStack = new Stack<Automaton>();
        index = 0;
        regexer = rgxr;
        symbols = rgxr.symbols();
        nfa = null;
    }
    
    /**
     * Builds an NFA using the McNaughton-Yamada-Thomson algorithm
     * @param regex
     * @return
     * @throws Exception 
     */
    public Automaton build(String regex) throws Exception {
        nfa = null;
        evalStack = new Stack<Automaton>();
        //index = 0;
        
        try {
            regexer.evaluate(regex);
            BinaryTree<Symbol> tree = regexer.returnAST();
            BTPostOrderIterator iterator = new BTPostOrderIterator(tree);
            
            while (iterator.hasNext()) {
                Symbol current = (Symbol) iterator.next();
                
                if (regexer.isOperand(current)) {
                    Automaton newAutomaton = new NFA();
                    newAutomaton.changeInitialState(new State(Integer.toString(index)));
                    index++;
                    newAutomaton.addAcc(new State(Integer.toString(index)));
                    index++;
                    Pair<State, Symbol> newKey = new Pair<State, Symbol>(newAutomaton.initial_state(), current);
                    newAutomaton.addTr(newKey, newAutomaton.accepting());
                    evalStack.push(newAutomaton);
                }
                
                else if (regexer.isOperator(current)) {
                    
                    if (current.equals(Regexer.OR_SYM)) {
                        Automaton op1 = evalStack.pop();
                        Automaton op2 = evalStack.pop();
                        
                        NFA newAutomaton = new NFA();
                        newAutomaton.absorb(op1);
                        newAutomaton.absorb(op2);
                        State newInitial = new State(Integer.toString(index));
                        newAutomaton.changeInitialState(newInitial);
                        index++;
                        State newAccepting = new State(Integer.toString(index));
                        newAutomaton.addAcc(newAccepting);
                        index++;
                        
                        Pair<State, Symbol> newKey = new Pair<State, Symbol>(newInitial, regexer.EMPTY_STR);
                        Set<State> fromInitial = new LinkedHashSet<State>();
                        fromInitial.add(op1.initial_state());
                        fromInitial.add(op2.initial_state());
                        newAutomaton.addTr(newKey, fromInitial);
                        
                        Iterator op1Accepting = op1.accepting.iterator();
                        State s1 = (State)op1Accepting.next();
                        //newAutomaton.addState(s1);
                        Pair<State, Symbol> newExit1Key = new Pair<State, Symbol>(s1, regexer.EMPTY_STR);
                        
                        Iterator op2Accepting = op2.accepting.iterator();
                        State s2 = (State)op2Accepting.next();
                        //newAutomaton.addState(s2);
                        Pair<State, Symbol> newExit2Key = new Pair<State, Symbol>(s2, regexer.EMPTY_STR);
                        newAutomaton.addTr(newExit1Key, newAccepting);
                        newAutomaton.addTr(newExit2Key, newAccepting);
                        
                        
                        evalStack.push(newAutomaton);
                        
                        
                    } else if (current.equals(Regexer.KLEENE_SYM)) {
                        
                        Automaton op1 = evalStack.pop(); 
                        
                        NFA newAutomaton = new NFA();
                        newAutomaton.absorb(op1);
                        
                        State newInitial = new State(Integer.toString(index));
                        newAutomaton.changeInitialState(newInitial);
                        index++;
                        State newAccepting = new State(Integer.toString(index));
                        newAutomaton.addAcc(newAccepting);
                        index++;
                        
                        Pair<State, Symbol> iniKey = new Pair<State, Symbol>(newInitial, regexer.EMPTY_STR);
                        Set<State> fromStart = new LinkedHashSet<State>();
                        fromStart.add(op1.initial_state());
                        fromStart.add(newAccepting);
                        newAutomaton.addTr(iniKey, fromStart);              
                        
                        Iterator op1Accepting = op1.accepting.iterator();
                        State s1 = (State)op1Accepting.next();
                        newAutomaton.addState(s1);
                        Pair<State, Symbol> finKey = new Pair<State, Symbol>(s1, regexer.EMPTY_STR);
                        Set<State> toAccepting = new LinkedHashSet<State>();
                        toAccepting.add(newAccepting);
                        toAccepting.add(op1.initial_state());
                        newAutomaton.addTr(finKey, toAccepting);
                        
                        evalStack.push(newAutomaton);
                        
                    } else if (current.equals(Regexer.CONCATENATION_SYM)) {
             
                        Automaton op2 = evalStack.pop();
                        Automaton op1 = evalStack.pop();
                        NFA newAutomaton = new NFA();
                        newAutomaton.absorb(op1);
                        newAutomaton.absorb(op2);
                        newAutomaton.changeInitialState(op1.initial_state());  
                        Iterator accepting = op2.accepting.iterator();
                        newAutomaton.addAcc((State)accepting.next());
                        
                        Iterator op1Accepting = op1.accepting.iterator();
                        Pair<State, Symbol> midKey = new Pair<State, Symbol>((State)op1Accepting.next(), regexer.EMPTY_STR);
                        newAutomaton.addTr(midKey, op2.initial_state().toSet());
                        
                        evalStack.push(newAutomaton);
                        
                    }       
                }            
            }
            
            nfa = evalStack.pop();
            nfa.setSymbols(regexer.symbols());
            return nfa;
            
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    /**
     * Returns the current NFA
     * @return 
     */
    public NFA nfa() {
        return (NFA) nfa;
    }
    
    
}
