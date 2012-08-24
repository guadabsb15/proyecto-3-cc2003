/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

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
    
    private Stack<Automaton> evalStack;
    
    private Regexer regexer;
    
    private int index;
    
    private Set symbols;
    
    private Automaton nfa;
    
    public NFABuilder(Regexer rgxr) {
        evalStack = new Stack<Automaton>();
        index = 0;
        regexer = rgxr;
        symbols = new LinkedHashSet();
        nfa = null;
    }
    
    public Automaton build(String regex) throws Exception {
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
                    newAutomaton.addAcceptingState(new State(Integer.toString(index)));
                    index++;
                    Pair<State, Symbol> newKey = new Pair<State, Symbol>(newAutomaton.initial_state(), current);
                    newAutomaton.addTransition(newKey, newAutomaton.accepting());
                    evalStack.push(newAutomaton);
                }
                
                else if (regexer.isOperator(current)) {
                    
                    if (current.equals(Regexer.OR)) {
                        Automaton op1 = evalStack.pop();
                        Automaton op2 = evalStack.pop();
                        
                        NFA newAutomaton = new NFA();
                        newAutomaton.absorb(op1);
                        newAutomaton.absorb(op2);
                        State newInitial = new State(Integer.toString(index));
                        newAutomaton.changeInitialState(newInitial);
                        index++;
                        State newAccepting = new State(Integer.toString(index));
                        newAutomaton.addAcceptingState(newAccepting);
                        index++;
                        
                        Pair<State, Symbol> newKey = new Pair<State, Symbol>(newInitial, regexer.EMPTY_STR);
                        Set<State> fromInitial = new LinkedHashSet<State>();
                        fromInitial.add(op1.initial_state());
                        fromInitial.add(op2.initial_state());
                        newAutomaton.addTransition(newKey, fromInitial);
                        
                        Iterator op1Accepting = op1.accepting.iterator();
                        State s1 = (State)op1Accepting.next();
                        //newAutomaton.addState(s1);
                        Pair<State, Symbol> newExit1Key = new Pair<State, Symbol>(s1, regexer.EMPTY_STR);
                        
                        Iterator op2Accepting = op2.accepting.iterator();
                        State s2 = (State)op2Accepting.next();
                        //newAutomaton.addState(s2);
                        Pair<State, Symbol> newExit2Key = new Pair<State, Symbol>(s2, regexer.EMPTY_STR);
                        newAutomaton.addTransition(newExit1Key, newAccepting);
                        newAutomaton.addTransition(newExit2Key, newAccepting);
                        
                        
                        evalStack.push(newAutomaton);
                        
                        
                    } else if (current.equals(Regexer.KLEENE)) {
                        
                        Automaton op1 = evalStack.pop(); 
                        
                        NFA newAutomaton = new NFA();
                        newAutomaton.absorb(op1);
                        
                        State newInitial = new State(Integer.toString(index));
                        newAutomaton.changeInitialState(newInitial);
                        index++;
                        State newAccepting = new State(Integer.toString(index));
                        newAutomaton.addAcceptingState(newAccepting);
                        index++;
                        
                        Pair<State, Symbol> iniKey = new Pair<State, Symbol>(newInitial, regexer.EMPTY_STR);
                        Set<State> fromStart = new LinkedHashSet<State>();
                        fromStart.add(op1.initial_state());
                        fromStart.add(newAccepting);
                        newAutomaton.addTransition(iniKey, fromStart);              
                        
                        Iterator op1Accepting = op1.accepting.iterator();
                        State s1 = (State)op1Accepting.next();
                        newAutomaton.addState(s1);
                        Pair<State, Symbol> finKey = new Pair<State, Symbol>(s1, regexer.EMPTY_STR);
                        Set<State> toAccepting = new LinkedHashSet<State>();
                        toAccepting.add(newAccepting);
                        toAccepting.add(op1.initial_state());
                        newAutomaton.addTransition(finKey, toAccepting);
                        
                        evalStack.push(newAutomaton);
                        
                    } else if (current.equals(Regexer.CONCATENATION)) {
                        
                        Automaton op2 = evalStack.pop();
                        Automaton op1 = evalStack.pop();
                        
                        NFA newAutomaton = new NFA();
                        newAutomaton.absorb(op1);
                        newAutomaton.absorb(op2);
                        newAutomaton.changeInitialState(op1.initial_state());  
                        Iterator accepting = op2.accepting.iterator();
                        newAutomaton.addAcceptingState((State)accepting.next());
                        
                        Iterator op1Accepting = op1.accepting.iterator();
                        Pair<State, Symbol> midKey = new Pair<State, Symbol>((State)op1Accepting.next(), regexer.EMPTY_STR);
                        newAutomaton.addTransition(midKey, op2.initial_state().toSet());
                       
                        evalStack.push(newAutomaton);
                        
                    }
                    
                    
                }
                
            }
        } catch (Exception e) {
            throw e;
        }
        
        nfa = evalStack.pop();
        return nfa;
        
    }
    
    public NFA nfa() {
        return (NFA) nfa;
    }
    
    
}
