package src;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;import java.io.InputStreamReader;import java.io.BufferedReader;import java.util.ArrayList;
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
import src.automatons.Symbol;
import src.scanner.Token;

public class ProyectoLexer {
private DFA dfa;
private BufferedReader reader;
private int currentCharacter;
private int nextCharacter;

public ProyectoLexer() throws Exception {
     reader = new BufferedReader(new InputStreamReader(new FileInputStream("prueba.txt"), "UTF-8"));
     consume();
     dfa = new DFA();
     State s1 = new State("1");
     dfa.addState(s1);
     dfa.changeInitialState(s1);
     State s2 = new State("2");
     dfa.addState(s2);
     s2.setAttached("id");
     dfa.addAcc(s2);
     State s3 = new State("3");
     dfa.addState(s3);
     s3.setAttached("id");
     dfa.addAcc(s3);
     State s4 = new State("4");
     dfa.addState(s4);
     s4.setAttached("IGNORE");
     dfa.addAcc(s4);
     State s5 = new State("5");
     dfa.addState(s5);
     s5.setAttached("id");
     dfa.addAcc(s5);
     State s6 = new State("6");
     dfa.addState(s6);
     s6.setAttached("id");
     dfa.addAcc(s6);
     State s7 = new State("7");
     dfa.addState(s7);
     s7.setAttached("sum");
     dfa.addAcc(s7);
     State s8 = new State("8");
     dfa.addState(s8);
     s8.setAttached("sum");
     dfa.addAcc(s8);
     State s9 = new State("9");
     dfa.addState(s9);
     s9.setAttached("while");
     dfa.addAcc(s9);
     State s10 = new State("10");
     dfa.addState(s10);
     s10.setAttached("if");
     dfa.addAcc(s10);
     State s11 = new State("11");
     dfa.addState(s11);
     State s12 = new State("12");
     dfa.addState(s12);
     State s13 = new State("13");
     dfa.addState(s13);
     State s14 = new State("14");
     dfa.addState(s14);
     State s15 = new State("15");
     dfa.addState(s15);
     State s16 = new State("16");
     dfa.addState(s16);
     State s17 = new State("17");
     dfa.addState(s17);
     dfa.addTr(new Pair(s1, new Symbol((char)949)), s1);
     dfa.addTr(new Pair(s1, new Symbol((char)105)), s11);
     dfa.addTr(new Pair(s1, new Symbol((char)119)), s12);
     dfa.addTr(new Pair(s1, new Symbol((char)97)), s2);
     dfa.addTr(new Pair(s1, new Symbol((char)98)), s3);
     dfa.addTr(new Pair(s1, new Symbol((char)32)), s4);
     dfa.addTr(new Pair(s4, new Symbol((char)949)), s4);
     dfa.addTr(new Pair(s3, new Symbol((char)949)), s3);
     dfa.addTr(new Pair(s3, new Symbol((char)97)), s5);
     dfa.addTr(new Pair(s3, new Symbol((char)98)), s6);
     dfa.addTr(new Pair(s3, new Symbol((char)45)), s13);
     dfa.addTr(new Pair(s3, new Symbol((char)43)), s14);
     dfa.addTr(new Pair(s14, new Symbol((char)949)), s14);
     dfa.addTr(new Pair(s14, new Symbol((char)97)), s7);
     dfa.addTr(new Pair(s14, new Symbol((char)98)), s8);
     dfa.addTr(new Pair(s8, new Symbol((char)949)), s8);
     dfa.addTr(new Pair(s7, new Symbol((char)949)), s7);
     dfa.addTr(new Pair(s13, new Symbol((char)949)), s13);
     dfa.addTr(new Pair(s13, new Symbol((char)97)), s7);
     dfa.addTr(new Pair(s13, new Symbol((char)98)), s8);
     dfa.addTr(new Pair(s6, new Symbol((char)949)), s6);
     dfa.addTr(new Pair(s6, new Symbol((char)97)), s5);
     dfa.addTr(new Pair(s6, new Symbol((char)98)), s6);
     dfa.addTr(new Pair(s5, new Symbol((char)949)), s5);
     dfa.addTr(new Pair(s5, new Symbol((char)97)), s5);
     dfa.addTr(new Pair(s5, new Symbol((char)98)), s6);
     dfa.addTr(new Pair(s2, new Symbol((char)949)), s2);
     dfa.addTr(new Pair(s2, new Symbol((char)97)), s5);
     dfa.addTr(new Pair(s2, new Symbol((char)98)), s6);
     dfa.addTr(new Pair(s2, new Symbol((char)45)), s13);
     dfa.addTr(new Pair(s2, new Symbol((char)43)), s14);
     dfa.addTr(new Pair(s12, new Symbol((char)949)), s12);
     dfa.addTr(new Pair(s12, new Symbol((char)104)), s15);
     dfa.addTr(new Pair(s15, new Symbol((char)949)), s15);
     dfa.addTr(new Pair(s15, new Symbol((char)105)), s16);
     dfa.addTr(new Pair(s16, new Symbol((char)949)), s16);
     dfa.addTr(new Pair(s16, new Symbol((char)108)), s17);
     dfa.addTr(new Pair(s17, new Symbol((char)949)), s17);
     dfa.addTr(new Pair(s17, new Symbol((char)101)), s9);
     dfa.addTr(new Pair(s9, new Symbol((char)949)), s9);
     dfa.addTr(new Pair(s11, new Symbol((char)949)), s11);
     dfa.addTr(new Pair(s11, new Symbol((char)102)), s10);
     dfa.addTr(new Pair(s10, new Symbol((char)949)), s10);
}

public Token getToken() throws Exception {
     StringBuilder s = new StringBuilder();
     while((dfa.lastState() == null) || (!dfa.lastState().equals(DFA.deadState))) {
         consume();
         if (currentCharacter == -1) return Token.EOF_TOKEN;
         s.append((char)currentCharacter);
         dfa.simulate(s.toString()+((char)nextCharacter));
     }
     if (dfa.lastState().equals(DFA.deadState)) {
         if (dfa.simulate(s.toString())) {
             return new Token(dfa.lastState().attached(), s.toString());
         }
     }
     throw new Exception("Invalid lexeme");
}
private void consume() throws Exception {
     currentCharacter = nextCharacter;
     nextCharacter = reader.read();
}
}
