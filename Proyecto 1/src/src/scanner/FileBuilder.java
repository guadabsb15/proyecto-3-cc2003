/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import src.Regexer;
import src.automatons.Automaton;
import src.automatons.DFA;
import src.automatons.NFA;
import src.automatons.NFABuilder;
import src.automatons.Pair;
import src.automatons.State;

/**
 *
 * @author asus
 */
public class FileBuilder {
    
    private CocoFileParser parser;
    private Regexer regexer;
    private NFABuilder nfaBuilder;
    private DFA dfa;
    private NFA nfa;
    private BufferedWriter out;
    private Map<State, String> mapping;
    
    public FileBuilder(String readName, String writeName) throws Exception {
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeName), "UTF-8"));
            parser = new CocoFileParser(readName);
            parser.parse();
        } catch(Exception e) {
            throw e; // new Exception("Error al crear el archivo");
        }
        regexer = new Regexer();
        nfaBuilder = new NFABuilder(regexer);
        dfa = new DFA();
        nfa = new NFA();
        mapping = new LinkedHashMap<State, String>();
        
    }
    
    public void buildAutomaton() throws Exception {
        Map<String, String> keywordsTable = parser.keywordsTable();
        Map<String, String> ignoreTable = parser.ignoreTable();
        Map<String, String> tokensTable = parser.tokensTable();
        ArrayList<String> excepts = parser.excepts();
        
        ArrayList<DFA> automata = new ArrayList<DFA>();
        
        createSubAutomata(keywordsTable, mapping, automata);
        createSubAutomata(tokensTable, mapping, automata);
        createSubAutomata(ignoreTable, mapping, automata);
        
        State initial = new State("start");
        nfa.changeInitialState(initial);
        for (int i = 0; i < automata.size(); i++) {
            DFA current = automata.get(i);
            nfa.addTransition(new Pair(initial, Regexer.EMPTY_STR), current.initial_state().toSet());
            nfa.absorbSymbols(current);
            nfa.absorb(current);
            nfa.absorbAccepting(current);
        }
        dfa = (DFA) nfa.toDfa();
    }
    
    public DFA dfa() {
        return dfa;
    }

    private void createSubAutomata(Map<String, String> table, Map<State, String> mapping, ArrayList<DFA> automata) throws Exception {
        Iterator keys = table.keySet().iterator();
        while (keys.hasNext()) {
            String currentId = (String) keys.next();
            String currentString = table.get(currentId);
            
            NFA automaton = (NFA) nfaBuilder.build(currentString);
            DFA converted = (DFA) automaton.toDfa();
            converted.attach(currentId);
            automata.add(converted);
            
            addMappings(automaton.accepting(), currentId);
            
        }
    }
    
    public String lookup(State s) {
        return mapping.get(s);
    }

    private void addMappings(Set<State> sts, String currentId) {
        Iterator states = sts.iterator();
        while (states.hasNext()) {
            mapping.put((State) states.next(), currentId);
        }
    }
    
    
}
