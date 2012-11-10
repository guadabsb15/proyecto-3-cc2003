/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

import com.sun.accessibility.internal.resources.accessibility;
import java.io.BufferedWriter;
import java.io.File;
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
import src.automatons.DFABuilder;
import src.automatons.NFA;
import src.automatons.NFABuilder;
import src.automatons.Pair;
import src.automatons.State;
import src.automatons.Symbol;



/**
 * Writes the generated lexical analyzer file in Java
 * @author asus
 */
public class FileBuilder {
    
    private CocoFileParser parser;
    private Regexer regexer;
    private NFABuilder nfaBuilder;
    private DFABuilder dfaBuilder;
    private DFA dfa;
    private NFA nfa;
    private BufferedWriter out;
    private Map<State, String> mapping;
    //private String fileReadName;
    private String fileWriteName;
    private ArrayList<String> excepts;
    private Set<String> excepted;
    private Map<State, State> simplification;
    
    /**
     * Class constructor
     * @param readName
     * @param writeName
     * @param generatedRead
     * @throws Exception 
     */
    public FileBuilder(String readName, String writeName, String generatedRead) throws Exception {
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/src/" + writeName + ".java"), "UTF-8"));
            parser = new CocoFileParser(readName + ".txt");
            parser.parse();
        } catch(Exception e) {
            throw e; // new Exception("Error al crear el archivo");
        }
        regexer = new Regexer();
        nfaBuilder = new NFABuilder(regexer);
        dfaBuilder = new DFABuilder(regexer);
        dfa = new DFA();
        nfa = new NFA();
        mapping = new LinkedHashMap<State, String>();
        //fileReadName = generatedRead + ".txt";
        fileWriteName = writeName;
        simplification = new LinkedHashMap<State, State>();
        
    }
    
    /**
     * Class constructor
     * @param specification
     * @param generated
     * @throws Exception 
     */
    public FileBuilder(File specification, File generated) throws Exception {
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(generated), "UTF-8"));
            parser = new CocoFileParser(specification);
            parser.parse();
        } catch(Exception e) {
            throw e; // new Exception("Error al crear el archivo");
        }
        regexer = new Regexer();
        nfaBuilder = new NFABuilder(regexer);
        dfaBuilder = new DFABuilder(regexer);
        dfa = new DFA();
        nfa = new NFA();
        mapping = new LinkedHashMap<State, String>();
        //fileReadName = generatedRead + ".txt";
        fileWriteName = generated.getName();
        simplification = new LinkedHashMap<State, State>();
        
    }
    
    /**
     * Builts the automaton that will serve as the lexical analyzer, from the information collected by the parser
     * @throws Exception 
     */
    public void buildAutomaton() throws Exception {
        Map<String, String> keywordsTable = parser.keywordsTable();
        Map<String, String> ignoreTable = parser.ignoreTable();
        Map<String, String> tokensTable = parser.tokensTable();
        //excepts = parser.excepts();
        //excepted = (Set<String>) keywordsTable.values();
        
        ArrayList<DFA> automata = new ArrayList<DFA>();
        
        if (keywordsTable != null) createSubAutomata(keywordsTable, mapping, automata);
        if (tokensTable != null)createSubAutomata(tokensTable, mapping, automata);
        if (ignoreTable != null)createSubAutomata(ignoreTable, mapping, automata);
        
        State initial = new State("start");
        nfa.changeInitialState(initial);
        for (int i = 0; i < automata.size(); i++) {
            DFA current = automata.get(i);
            nfa.addTr(new Pair(initial, Regexer.EMPTY_STR), current.initial_state().toSet());
            nfa.absorbSymbols(current);
            nfa.absorb(current);
            nfa.absorbAccepting(current);
        }
        dfa = (DFA) nfa.toDfa();
    }
    
    /**
     * Writes the java file that implements the lexical analyzer
     * @throws Exception 
     */
    public void writeFile() throws Exception {
        try {
            buildAutomaton();
            writeImports(out);
            out.write('\n');
            writeClassName(out);
            out.write('\n');
            writeConstructor(out);
            out.write('\n');
            writeNextToken(out);
            writeConsume(out);
            out.write("}" + '\n');
            out.close();
            
        } catch (Exception e) {
            throw e;
        }
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
            //converted = converted.minimize();
            
            
            //DFA converted = (DFA) dfaBuilder.build(currentString);
            converted.attach(currentId);
            automata.add(converted);
            
            //automaton.attach(currentId);
            //automata.add(automaton);
            
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

    private void writeImports(BufferedWriter out) throws Exception {
        out.write("package src;" + '\n');
        out.write("import java.io.File;" + '\n');
        out.write("import java.io.FileInputStream;");
        out.write("import java.io.InputStreamReader;");
        out.write("import java.io.BufferedReader;");
        out.write("import java.util.ArrayList;"+'\n');
        out.write("import java.util.Set;"+'\n');
        out.write("import src.automatons.DFA;"+'\n');
        out.write("import src.automatons.Pair;"+'\n');
        out.write("import src.automatons.State;"+'\n');
        out.write("import src.automatons.Symbol;"+'\n');
        out.write("import src.scanner.Scanner;"+'\n');
        out.write("import src.scanner.Token;"+'\n');
        
    }

    private void writeClassName(BufferedWriter out) throws Exception {
        out.write("public class " + fileWriteName +  " implements Scanner {" + '\n');
        out.write("     public DFA dfa;" + '\n');
        out.write("     private BufferedReader reader;" + '\n');
        out.write("     private int currentCharacter;" + '\n');
        out.write("     private int nextCharacter;" + '\n');
        out.write("     private int line;" + '\n');
        out.write("     private ArrayList<String> excepts;" + '\n');
        out.write("     Set<String> excepted;" + '\n');
        
        Set<State> states = dfa.states();
        int index = 1;
        for (int i = 0; i < states.size(); i++) {
            out.write("         private State s" + index + " = new State(\"" + index + "\");" + '\n');
            index++;
        }
    }

    private void writeConstructor(BufferedWriter out) throws Exception {
        out.write("     public " + fileWriteName +  "(File file) throws Exception {" + '\n');
        out.write("         reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), \"UTF-8\"));" + '\n');
        out.write("         consume();" + '\n');
        out.write("         line = 1;" + '\n');
        out.write("         dfa = new DFA();" + '\n');
        out.write("         createStates();" + '\n');
        out.write("         createTransitions();" + '\n');
        out.write("     }" + '\n' + '\n');
        
        /**
        out.write("         excepts = new ArrayList<String>();" + '\n');
        for (int i = 0; i < excepts.size(); i++) {
            out.write("         excepts.add(" + excepts.get(i) + ")" + '\n' + '\n');
        }
        
        out.write("         excepted = new Set<String>();" + '\n');
        Iterator e = excepted.iterator();
        while (e.hasNext()) {
            out.write("         excepted.add(" + (String) e.next() + ");" + '\n');
        }*/
        
        crystallizeAutomaton(out);
        

    }
    
    
    
    /**
     * Crystallizes the automaton that resides in memory to the Java file
     * @param out
     * @throws Exception 
     */
    private void crystallizeAutomaton(BufferedWriter out) throws Exception {
        //Map<State, State> simplification = new LinkedHashMap<State, State>();
        
        Set<State> states = dfa.states();
        Iterator i = states.iterator();
        int index = 1;
        
        out.write("     private void createStates() throws Exception {" + '\n');
        while (i.hasNext()) {
            State old = (State) i.next();
            State simplified = new State(Integer.toString(index));
            simplification.put(old, simplified);
            //out.write("         State s" + index + " = new State(\"" + index + "\");" + '\n');
            out.write("         dfa.addState(s" + index + ");" + '\n');
            if (old.attached() != null) {
                out.write("         s" + index + ".setAttached(\"" + old.attached() + "\");" + '\n');
            }
            if (dfa.accepting().contains(old)) {
                out.write("         dfa.addAcc(s" + index + ");" + '\n');
            }
            if (dfa.initial_state().equals(old)) {
                out.write("         dfa.changeInitialState(s" + index + ");" + '\n');
            }
            index++;
        }
        out.write("     }" + '\n' + '\n');
        //out.write("         writeTransitions();" + '\n');
        ////out.write("     }" + '\n');
        
        Iterator transitions = dfa.transition().keySet().iterator();
        
        //out.write("     public void writeTransitions() throws Exception {" + '\n');
        out.write("     private void createTransitions() throws Exception {" + '\n');
        while (transitions.hasNext()) {
            Pair<State, Symbol> currentPair = (Pair<State, Symbol>) transitions.next();
            State stateKey = currentPair.returnFirst();
            Symbol symbolKey = currentPair.returnSecond();
            Iterator stateValues = dfa.transition().get(currentPair).iterator();
            while (stateValues.hasNext()) {
                State currentVal = (State) stateValues.next();
                out.write("         dfa.intTr(new Pair(s" + simplification.get(stateKey).id() + ", " + ((int)symbolKey.id()) + "), s" + simplification.get(currentVal).id() + ");" + '\n');
            }
            
        }
        out.write("     }" + '\n' + '\n');
        
    }

    private void writeNextToken(BufferedWriter out) throws Exception {
        out.write("     @Override" + '\n');
        out.write("     public Token getToken() throws Exception {" + '\n');
        out.write("         StringBuilder s = new StringBuilder();" + '\n');
        out.write("         while((dfa.lastState() == null) || (!dfa.lastState().equals(DFA.deadState))) {" + '\n');
        out.write("             consume();"+ '\n');
        out.write("             if (currentCharacter == (int) \'\\n\') line++;" + '\n');
        out.write("             if (currentCharacter == -1) return Token.EOF_TOKEN;" + '\n');
        out.write("             s.append((char)currentCharacter);" + '\n');
        out.write("             dfa.simulate(s.toString()+((char)nextCharacter));" + '\n');
        out.write("         }" + '\n');
        out.write("         if (dfa.lastState().equals(DFA.deadState)) {" + '\n');
        out.write("             if (dfa.simulate(s.toString())) {" + '\n');
        out.write("                 return new Token(dfa.lastState().attached(), s.toString());" + '\n');
        out.write("             }" + '\n');
        out.write("         }" + '\n');
        out.write("         throw new Exception(\"Line : \" + line + \"Invalid lexeme\");" + '\n');
        out.write("     }" + '\n' + '\n');
    }

    private void writeConsume(BufferedWriter out) throws Exception {
        out.write("     private void consume() throws Exception {" + '\n');
        out.write("         currentCharacter = nextCharacter;" + '\n');
        out.write("         nextCharacter = reader.read();" + '\n');
        out.write("     }" + '\n');
        
        out.write("     public int line() {" + '\n');
        out.write("         return line;" + '\n');
        out.write("     }" + '\n');
    }
}
