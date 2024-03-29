package src;
import java.io.File;
import java.io.FileInputStream;import java.io.InputStreamReader;import java.io.BufferedReader;import java.util.ArrayList;
import java.util.Set;
import src.automatons.DFA;
import src.automatons.Pair;
import src.automatons.State;
import src.automatons.Symbol;
import src.scanner.Scanner;
import src.scanner.Token;

public class Double implements Scanner {
     public DFA dfa;
     private BufferedReader reader;
     private int currentCharacter;
     private int nextCharacter;
     private int line;
     private ArrayList<String> excepts;
     Set<String> excepted;
         private State s1 = new State("1");
         private State s2 = new State("2");
         private State s3 = new State("3");
         private State s4 = new State("4");
         private State s5 = new State("5");
         private State s6 = new State("6");
         private State s7 = new State("7");
         private State s8 = new State("8");
         private State s9 = new State("9");
         private State s10 = new State("10");
         private State s11 = new State("11");
         private State s12 = new State("12");
         private State s13 = new State("13");
         private State s14 = new State("14");
         private State s15 = new State("15");
         private State s16 = new State("16");
         private State s17 = new State("17");
         private State s18 = new State("18");
         private State s19 = new State("19");
         private State s20 = new State("20");
         private State s21 = new State("21");
         private State s22 = new State("22");
         private State s23 = new State("23");
         private State s24 = new State("24");
         private State s25 = new State("25");
         private State s26 = new State("26");
         private State s27 = new State("27");
         private State s28 = new State("28");
         private State s29 = new State("29");
         private State s30 = new State("30");
         private State s31 = new State("31");
         private State s32 = new State("32");
         private State s33 = new State("33");
         private State s34 = new State("34");
         private State s35 = new State("35");
         private State s36 = new State("36");
         private State s37 = new State("37");
         private State s38 = new State("38");
         private State s39 = new State("39");
         private State s40 = new State("40");
         private State s41 = new State("41");
         private State s42 = new State("42");
         private State s43 = new State("43");
         private State s44 = new State("44");
         private State s45 = new State("45");
         private State s46 = new State("46");
         private State s47 = new State("47");
         private State s48 = new State("48");
         private State s49 = new State("49");
         private State s50 = new State("50");
         private State s51 = new State("51");
         private State s52 = new State("52");
         private State s53 = new State("53");
         private State s54 = new State("54");
         private State s55 = new State("55");
         private State s56 = new State("56");
         private State s57 = new State("57");

     public Double(File file) throws Exception {
         reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
         consume();
         line = 1;
         dfa = new DFA();
         createStates();
         createTransitions();
     }

     private void createStates() throws Exception {
         dfa.addState(s1);
         dfa.changeInitialState(s1);
         dfa.addState(s2);
         s2.setAttached("number");
         dfa.addAcc(s2);
         dfa.addState(s3);
         s3.setAttached("number");
         dfa.addAcc(s3);
         dfa.addState(s4);
         s4.setAttached("number");
         dfa.addAcc(s4);
         dfa.addState(s5);
         s5.setAttached("number");
         dfa.addAcc(s5);
         dfa.addState(s6);
         s6.setAttached("number");
         dfa.addAcc(s6);
         dfa.addState(s7);
         s7.setAttached("number");
         dfa.addAcc(s7);
         dfa.addState(s8);
         s8.setAttached("number");
         dfa.addAcc(s8);
         dfa.addState(s9);
         s9.setAttached("number");
         dfa.addAcc(s9);
         dfa.addState(s10);
         s10.setAttached("number");
         dfa.addAcc(s10);
         dfa.addState(s11);
         s11.setAttached("number");
         dfa.addAcc(s11);
         dfa.addState(s12);
         s12.setAttached("white");
         dfa.addAcc(s12);
         dfa.addState(s13);
         s13.setAttached("white");
         dfa.addAcc(s13);
         dfa.addState(s14);
         s14.setAttached("white");
         dfa.addAcc(s14);
         dfa.addState(s15);
         s15.setAttached("white");
         dfa.addAcc(s15);
         dfa.addState(s16);
         s16.setAttached("white");
         dfa.addAcc(s16);
         dfa.addState(s17);
         s17.setAttached("white");
         dfa.addAcc(s17);
         dfa.addState(s18);
         s18.setAttached("white");
         dfa.addAcc(s18);
         dfa.addState(s19);
         s19.setAttached("white");
         dfa.addAcc(s19);
         dfa.addState(s20);
         s20.setAttached("number");
         dfa.addAcc(s20);
         dfa.addState(s21);
         s21.setAttached("number");
         dfa.addAcc(s21);
         dfa.addState(s22);
         s22.setAttached("number");
         dfa.addAcc(s22);
         dfa.addState(s23);
         s23.setAttached("number");
         dfa.addAcc(s23);
         dfa.addState(s24);
         s24.setAttached("number");
         dfa.addAcc(s24);
         dfa.addState(s25);
         s25.setAttached("number");
         dfa.addAcc(s25);
         dfa.addState(s26);
         s26.setAttached("number");
         dfa.addAcc(s26);
         dfa.addState(s27);
         s27.setAttached("number");
         dfa.addAcc(s27);
         dfa.addState(s28);
         s28.setAttached("number");
         dfa.addAcc(s28);
         dfa.addState(s29);
         s29.setAttached("number");
         dfa.addAcc(s29);
         dfa.addState(s30);
         s30.setAttached("decnumber");
         dfa.addAcc(s30);
         dfa.addState(s31);
         s31.setAttached("decnumber");
         dfa.addAcc(s31);
         dfa.addState(s32);
         s32.setAttached("decnumber");
         dfa.addAcc(s32);
         dfa.addState(s33);
         s33.setAttached("decnumber");
         dfa.addAcc(s33);
         dfa.addState(s34);
         s34.setAttached("decnumber");
         dfa.addAcc(s34);
         dfa.addState(s35);
         s35.setAttached("decnumber");
         dfa.addAcc(s35);
         dfa.addState(s36);
         s36.setAttached("decnumber");
         dfa.addAcc(s36);
         dfa.addState(s37);
         s37.setAttached("decnumber");
         dfa.addAcc(s37);
         dfa.addState(s38);
         s38.setAttached("decnumber");
         dfa.addAcc(s38);
         dfa.addState(s39);
         s39.setAttached("decnumber");
         dfa.addAcc(s39);
         dfa.addState(s40);
         s40.setAttached("decnumber");
         dfa.addAcc(s40);
         dfa.addState(s41);
         s41.setAttached("decnumber");
         dfa.addAcc(s41);
         dfa.addState(s42);
         s42.setAttached("decnumber");
         dfa.addAcc(s42);
         dfa.addState(s43);
         s43.setAttached("decnumber");
         dfa.addAcc(s43);
         dfa.addState(s44);
         s44.setAttached("decnumber");
         dfa.addAcc(s44);
         dfa.addState(s45);
         s45.setAttached("decnumber");
         dfa.addAcc(s45);
         dfa.addState(s46);
         s46.setAttached("decnumber");
         dfa.addAcc(s46);
         dfa.addState(s47);
         s47.setAttached("decnumber");
         dfa.addAcc(s47);
         dfa.addState(s48);
         s48.setAttached("decnumber");
         dfa.addAcc(s48);
         dfa.addState(s49);
         s49.setAttached("decnumber");
         dfa.addAcc(s49);
         dfa.addState(s50);
         s50.setAttached("do");
         dfa.addAcc(s50);
         dfa.addState(s51);
         s51.setAttached("while");
         dfa.addAcc(s51);
         dfa.addState(s52);
         dfa.addState(s53);
         dfa.addState(s54);
         dfa.addState(s55);
         dfa.addState(s56);
         dfa.addState(s57);
     }

     private void createTransitions() throws Exception {
         dfa.intTr(new Pair(s1, 949), s1);
         dfa.intTr(new Pair(s1, 119), s52);
         dfa.intTr(new Pair(s1, 100), s53);
         dfa.intTr(new Pair(s1, 48), s2);
         dfa.intTr(new Pair(s1, 49), s3);
         dfa.intTr(new Pair(s1, 50), s4);
         dfa.intTr(new Pair(s1, 51), s5);
         dfa.intTr(new Pair(s1, 52), s6);
         dfa.intTr(new Pair(s1, 53), s7);
         dfa.intTr(new Pair(s1, 54), s8);
         dfa.intTr(new Pair(s1, 55), s9);
         dfa.intTr(new Pair(s1, 56), s10);
         dfa.intTr(new Pair(s1, 57), s11);
         dfa.intTr(new Pair(s1, 10), s12);
         dfa.intTr(new Pair(s1, 13), s13);
         dfa.intTr(new Pair(s1, 9), s14);
         dfa.intTr(new Pair(s1, 32), s15);
         dfa.intTr(new Pair(s15, 949), s15);
         dfa.intTr(new Pair(s15, 10), s16);
         dfa.intTr(new Pair(s15, 13), s17);
         dfa.intTr(new Pair(s15, 9), s18);
         dfa.intTr(new Pair(s15, 32), s19);
         dfa.intTr(new Pair(s19, 949), s19);
         dfa.intTr(new Pair(s19, 10), s16);
         dfa.intTr(new Pair(s19, 13), s17);
         dfa.intTr(new Pair(s19, 9), s18);
         dfa.intTr(new Pair(s19, 32), s19);
         dfa.intTr(new Pair(s18, 949), s18);
         dfa.intTr(new Pair(s18, 10), s16);
         dfa.intTr(new Pair(s18, 13), s17);
         dfa.intTr(new Pair(s18, 9), s18);
         dfa.intTr(new Pair(s18, 32), s19);
         dfa.intTr(new Pair(s17, 949), s17);
         dfa.intTr(new Pair(s17, 10), s16);
         dfa.intTr(new Pair(s17, 13), s17);
         dfa.intTr(new Pair(s17, 9), s18);
         dfa.intTr(new Pair(s17, 32), s19);
         dfa.intTr(new Pair(s16, 949), s16);
         dfa.intTr(new Pair(s16, 10), s16);
         dfa.intTr(new Pair(s16, 13), s17);
         dfa.intTr(new Pair(s16, 9), s18);
         dfa.intTr(new Pair(s16, 32), s19);
         dfa.intTr(new Pair(s14, 949), s14);
         dfa.intTr(new Pair(s14, 10), s16);
         dfa.intTr(new Pair(s14, 13), s17);
         dfa.intTr(new Pair(s14, 9), s18);
         dfa.intTr(new Pair(s14, 32), s19);
         dfa.intTr(new Pair(s13, 949), s13);
         dfa.intTr(new Pair(s13, 10), s16);
         dfa.intTr(new Pair(s13, 13), s17);
         dfa.intTr(new Pair(s13, 9), s18);
         dfa.intTr(new Pair(s13, 32), s19);
         dfa.intTr(new Pair(s12, 949), s12);
         dfa.intTr(new Pair(s12, 10), s16);
         dfa.intTr(new Pair(s12, 13), s17);
         dfa.intTr(new Pair(s12, 9), s18);
         dfa.intTr(new Pair(s12, 32), s19);
         dfa.intTr(new Pair(s11, 949), s11);
         dfa.intTr(new Pair(s11, 48), s20);
         dfa.intTr(new Pair(s11, 49), s21);
         dfa.intTr(new Pair(s11, 50), s22);
         dfa.intTr(new Pair(s11, 51), s23);
         dfa.intTr(new Pair(s11, 52), s24);
         dfa.intTr(new Pair(s11, 53), s25);
         dfa.intTr(new Pair(s11, 54), s26);
         dfa.intTr(new Pair(s11, 55), s27);
         dfa.intTr(new Pair(s11, 56), s28);
         dfa.intTr(new Pair(s11, 57), s29);
         dfa.intTr(new Pair(s11, 46), s54);
         dfa.intTr(new Pair(s54, 949), s54);
         dfa.intTr(new Pair(s54, 48), s30);
         dfa.intTr(new Pair(s54, 49), s31);
         dfa.intTr(new Pair(s54, 50), s32);
         dfa.intTr(new Pair(s54, 51), s33);
         dfa.intTr(new Pair(s54, 52), s34);
         dfa.intTr(new Pair(s54, 53), s35);
         dfa.intTr(new Pair(s54, 54), s36);
         dfa.intTr(new Pair(s54, 55), s37);
         dfa.intTr(new Pair(s54, 56), s38);
         dfa.intTr(new Pair(s54, 57), s39);
         dfa.intTr(new Pair(s39, 949), s39);
         dfa.intTr(new Pair(s39, 48), s40);
         dfa.intTr(new Pair(s39, 49), s41);
         dfa.intTr(new Pair(s39, 50), s42);
         dfa.intTr(new Pair(s39, 51), s43);
         dfa.intTr(new Pair(s39, 52), s44);
         dfa.intTr(new Pair(s39, 53), s45);
         dfa.intTr(new Pair(s39, 54), s46);
         dfa.intTr(new Pair(s39, 55), s47);
         dfa.intTr(new Pair(s39, 56), s48);
         dfa.intTr(new Pair(s39, 57), s49);
         dfa.intTr(new Pair(s49, 949), s49);
         dfa.intTr(new Pair(s49, 48), s40);
         dfa.intTr(new Pair(s49, 49), s41);
         dfa.intTr(new Pair(s49, 50), s42);
         dfa.intTr(new Pair(s49, 51), s43);
         dfa.intTr(new Pair(s49, 52), s44);
         dfa.intTr(new Pair(s49, 53), s45);
         dfa.intTr(new Pair(s49, 54), s46);
         dfa.intTr(new Pair(s49, 55), s47);
         dfa.intTr(new Pair(s49, 56), s48);
         dfa.intTr(new Pair(s49, 57), s49);
         dfa.intTr(new Pair(s48, 949), s48);
         dfa.intTr(new Pair(s48, 48), s40);
         dfa.intTr(new Pair(s48, 49), s41);
         dfa.intTr(new Pair(s48, 50), s42);
         dfa.intTr(new Pair(s48, 51), s43);
         dfa.intTr(new Pair(s48, 52), s44);
         dfa.intTr(new Pair(s48, 53), s45);
         dfa.intTr(new Pair(s48, 54), s46);
         dfa.intTr(new Pair(s48, 55), s47);
         dfa.intTr(new Pair(s48, 56), s48);
         dfa.intTr(new Pair(s48, 57), s49);
         dfa.intTr(new Pair(s47, 949), s47);
         dfa.intTr(new Pair(s47, 48), s40);
         dfa.intTr(new Pair(s47, 49), s41);
         dfa.intTr(new Pair(s47, 50), s42);
         dfa.intTr(new Pair(s47, 51), s43);
         dfa.intTr(new Pair(s47, 52), s44);
         dfa.intTr(new Pair(s47, 53), s45);
         dfa.intTr(new Pair(s47, 54), s46);
         dfa.intTr(new Pair(s47, 55), s47);
         dfa.intTr(new Pair(s47, 56), s48);
         dfa.intTr(new Pair(s47, 57), s49);
         dfa.intTr(new Pair(s46, 949), s46);
         dfa.intTr(new Pair(s46, 48), s40);
         dfa.intTr(new Pair(s46, 49), s41);
         dfa.intTr(new Pair(s46, 50), s42);
         dfa.intTr(new Pair(s46, 51), s43);
         dfa.intTr(new Pair(s46, 52), s44);
         dfa.intTr(new Pair(s46, 53), s45);
         dfa.intTr(new Pair(s46, 54), s46);
         dfa.intTr(new Pair(s46, 55), s47);
         dfa.intTr(new Pair(s46, 56), s48);
         dfa.intTr(new Pair(s46, 57), s49);
         dfa.intTr(new Pair(s45, 949), s45);
         dfa.intTr(new Pair(s45, 48), s40);
         dfa.intTr(new Pair(s45, 49), s41);
         dfa.intTr(new Pair(s45, 50), s42);
         dfa.intTr(new Pair(s45, 51), s43);
         dfa.intTr(new Pair(s45, 52), s44);
         dfa.intTr(new Pair(s45, 53), s45);
         dfa.intTr(new Pair(s45, 54), s46);
         dfa.intTr(new Pair(s45, 55), s47);
         dfa.intTr(new Pair(s45, 56), s48);
         dfa.intTr(new Pair(s45, 57), s49);
         dfa.intTr(new Pair(s44, 949), s44);
         dfa.intTr(new Pair(s44, 48), s40);
         dfa.intTr(new Pair(s44, 49), s41);
         dfa.intTr(new Pair(s44, 50), s42);
         dfa.intTr(new Pair(s44, 51), s43);
         dfa.intTr(new Pair(s44, 52), s44);
         dfa.intTr(new Pair(s44, 53), s45);
         dfa.intTr(new Pair(s44, 54), s46);
         dfa.intTr(new Pair(s44, 55), s47);
         dfa.intTr(new Pair(s44, 56), s48);
         dfa.intTr(new Pair(s44, 57), s49);
         dfa.intTr(new Pair(s43, 949), s43);
         dfa.intTr(new Pair(s43, 48), s40);
         dfa.intTr(new Pair(s43, 49), s41);
         dfa.intTr(new Pair(s43, 50), s42);
         dfa.intTr(new Pair(s43, 51), s43);
         dfa.intTr(new Pair(s43, 52), s44);
         dfa.intTr(new Pair(s43, 53), s45);
         dfa.intTr(new Pair(s43, 54), s46);
         dfa.intTr(new Pair(s43, 55), s47);
         dfa.intTr(new Pair(s43, 56), s48);
         dfa.intTr(new Pair(s43, 57), s49);
         dfa.intTr(new Pair(s42, 949), s42);
         dfa.intTr(new Pair(s42, 48), s40);
         dfa.intTr(new Pair(s42, 49), s41);
         dfa.intTr(new Pair(s42, 50), s42);
         dfa.intTr(new Pair(s42, 51), s43);
         dfa.intTr(new Pair(s42, 52), s44);
         dfa.intTr(new Pair(s42, 53), s45);
         dfa.intTr(new Pair(s42, 54), s46);
         dfa.intTr(new Pair(s42, 55), s47);
         dfa.intTr(new Pair(s42, 56), s48);
         dfa.intTr(new Pair(s42, 57), s49);
         dfa.intTr(new Pair(s41, 949), s41);
         dfa.intTr(new Pair(s41, 48), s40);
         dfa.intTr(new Pair(s41, 49), s41);
         dfa.intTr(new Pair(s41, 50), s42);
         dfa.intTr(new Pair(s41, 51), s43);
         dfa.intTr(new Pair(s41, 52), s44);
         dfa.intTr(new Pair(s41, 53), s45);
         dfa.intTr(new Pair(s41, 54), s46);
         dfa.intTr(new Pair(s41, 55), s47);
         dfa.intTr(new Pair(s41, 56), s48);
         dfa.intTr(new Pair(s41, 57), s49);
         dfa.intTr(new Pair(s40, 949), s40);
         dfa.intTr(new Pair(s40, 48), s40);
         dfa.intTr(new Pair(s40, 49), s41);
         dfa.intTr(new Pair(s40, 50), s42);
         dfa.intTr(new Pair(s40, 51), s43);
         dfa.intTr(new Pair(s40, 52), s44);
         dfa.intTr(new Pair(s40, 53), s45);
         dfa.intTr(new Pair(s40, 54), s46);
         dfa.intTr(new Pair(s40, 55), s47);
         dfa.intTr(new Pair(s40, 56), s48);
         dfa.intTr(new Pair(s40, 57), s49);
         dfa.intTr(new Pair(s38, 949), s38);
         dfa.intTr(new Pair(s38, 48), s40);
         dfa.intTr(new Pair(s38, 49), s41);
         dfa.intTr(new Pair(s38, 50), s42);
         dfa.intTr(new Pair(s38, 51), s43);
         dfa.intTr(new Pair(s38, 52), s44);
         dfa.intTr(new Pair(s38, 53), s45);
         dfa.intTr(new Pair(s38, 54), s46);
         dfa.intTr(new Pair(s38, 55), s47);
         dfa.intTr(new Pair(s38, 56), s48);
         dfa.intTr(new Pair(s38, 57), s49);
         dfa.intTr(new Pair(s37, 949), s37);
         dfa.intTr(new Pair(s37, 48), s40);
         dfa.intTr(new Pair(s37, 49), s41);
         dfa.intTr(new Pair(s37, 50), s42);
         dfa.intTr(new Pair(s37, 51), s43);
         dfa.intTr(new Pair(s37, 52), s44);
         dfa.intTr(new Pair(s37, 53), s45);
         dfa.intTr(new Pair(s37, 54), s46);
         dfa.intTr(new Pair(s37, 55), s47);
         dfa.intTr(new Pair(s37, 56), s48);
         dfa.intTr(new Pair(s37, 57), s49);
         dfa.intTr(new Pair(s36, 949), s36);
         dfa.intTr(new Pair(s36, 48), s40);
         dfa.intTr(new Pair(s36, 49), s41);
         dfa.intTr(new Pair(s36, 50), s42);
         dfa.intTr(new Pair(s36, 51), s43);
         dfa.intTr(new Pair(s36, 52), s44);
         dfa.intTr(new Pair(s36, 53), s45);
         dfa.intTr(new Pair(s36, 54), s46);
         dfa.intTr(new Pair(s36, 55), s47);
         dfa.intTr(new Pair(s36, 56), s48);
         dfa.intTr(new Pair(s36, 57), s49);
         dfa.intTr(new Pair(s35, 949), s35);
         dfa.intTr(new Pair(s35, 48), s40);
         dfa.intTr(new Pair(s35, 49), s41);
         dfa.intTr(new Pair(s35, 50), s42);
         dfa.intTr(new Pair(s35, 51), s43);
         dfa.intTr(new Pair(s35, 52), s44);
         dfa.intTr(new Pair(s35, 53), s45);
         dfa.intTr(new Pair(s35, 54), s46);
         dfa.intTr(new Pair(s35, 55), s47);
         dfa.intTr(new Pair(s35, 56), s48);
         dfa.intTr(new Pair(s35, 57), s49);
         dfa.intTr(new Pair(s34, 949), s34);
         dfa.intTr(new Pair(s34, 48), s40);
         dfa.intTr(new Pair(s34, 49), s41);
         dfa.intTr(new Pair(s34, 50), s42);
         dfa.intTr(new Pair(s34, 51), s43);
         dfa.intTr(new Pair(s34, 52), s44);
         dfa.intTr(new Pair(s34, 53), s45);
         dfa.intTr(new Pair(s34, 54), s46);
         dfa.intTr(new Pair(s34, 55), s47);
         dfa.intTr(new Pair(s34, 56), s48);
         dfa.intTr(new Pair(s34, 57), s49);
         dfa.intTr(new Pair(s33, 949), s33);
         dfa.intTr(new Pair(s33, 48), s40);
         dfa.intTr(new Pair(s33, 49), s41);
         dfa.intTr(new Pair(s33, 50), s42);
         dfa.intTr(new Pair(s33, 51), s43);
         dfa.intTr(new Pair(s33, 52), s44);
         dfa.intTr(new Pair(s33, 53), s45);
         dfa.intTr(new Pair(s33, 54), s46);
         dfa.intTr(new Pair(s33, 55), s47);
         dfa.intTr(new Pair(s33, 56), s48);
         dfa.intTr(new Pair(s33, 57), s49);
         dfa.intTr(new Pair(s32, 949), s32);
         dfa.intTr(new Pair(s32, 48), s40);
         dfa.intTr(new Pair(s32, 49), s41);
         dfa.intTr(new Pair(s32, 50), s42);
         dfa.intTr(new Pair(s32, 51), s43);
         dfa.intTr(new Pair(s32, 52), s44);
         dfa.intTr(new Pair(s32, 53), s45);
         dfa.intTr(new Pair(s32, 54), s46);
         dfa.intTr(new Pair(s32, 55), s47);
         dfa.intTr(new Pair(s32, 56), s48);
         dfa.intTr(new Pair(s32, 57), s49);
         dfa.intTr(new Pair(s31, 949), s31);
         dfa.intTr(new Pair(s31, 48), s40);
         dfa.intTr(new Pair(s31, 49), s41);
         dfa.intTr(new Pair(s31, 50), s42);
         dfa.intTr(new Pair(s31, 51), s43);
         dfa.intTr(new Pair(s31, 52), s44);
         dfa.intTr(new Pair(s31, 53), s45);
         dfa.intTr(new Pair(s31, 54), s46);
         dfa.intTr(new Pair(s31, 55), s47);
         dfa.intTr(new Pair(s31, 56), s48);
         dfa.intTr(new Pair(s31, 57), s49);
         dfa.intTr(new Pair(s30, 949), s30);
         dfa.intTr(new Pair(s30, 48), s40);
         dfa.intTr(new Pair(s30, 49), s41);
         dfa.intTr(new Pair(s30, 50), s42);
         dfa.intTr(new Pair(s30, 51), s43);
         dfa.intTr(new Pair(s30, 52), s44);
         dfa.intTr(new Pair(s30, 53), s45);
         dfa.intTr(new Pair(s30, 54), s46);
         dfa.intTr(new Pair(s30, 55), s47);
         dfa.intTr(new Pair(s30, 56), s48);
         dfa.intTr(new Pair(s30, 57), s49);
         dfa.intTr(new Pair(s29, 949), s29);
         dfa.intTr(new Pair(s29, 48), s20);
         dfa.intTr(new Pair(s29, 49), s21);
         dfa.intTr(new Pair(s29, 50), s22);
         dfa.intTr(new Pair(s29, 51), s23);
         dfa.intTr(new Pair(s29, 52), s24);
         dfa.intTr(new Pair(s29, 53), s25);
         dfa.intTr(new Pair(s29, 54), s26);
         dfa.intTr(new Pair(s29, 55), s27);
         dfa.intTr(new Pair(s29, 56), s28);
         dfa.intTr(new Pair(s29, 57), s29);
         dfa.intTr(new Pair(s29, 46), s54);
         dfa.intTr(new Pair(s28, 949), s28);
         dfa.intTr(new Pair(s28, 48), s20);
         dfa.intTr(new Pair(s28, 49), s21);
         dfa.intTr(new Pair(s28, 50), s22);
         dfa.intTr(new Pair(s28, 51), s23);
         dfa.intTr(new Pair(s28, 52), s24);
         dfa.intTr(new Pair(s28, 53), s25);
         dfa.intTr(new Pair(s28, 54), s26);
         dfa.intTr(new Pair(s28, 55), s27);
         dfa.intTr(new Pair(s28, 56), s28);
         dfa.intTr(new Pair(s28, 57), s29);
         dfa.intTr(new Pair(s28, 46), s54);
         dfa.intTr(new Pair(s27, 949), s27);
         dfa.intTr(new Pair(s27, 48), s20);
         dfa.intTr(new Pair(s27, 49), s21);
         dfa.intTr(new Pair(s27, 50), s22);
         dfa.intTr(new Pair(s27, 51), s23);
         dfa.intTr(new Pair(s27, 52), s24);
         dfa.intTr(new Pair(s27, 53), s25);
         dfa.intTr(new Pair(s27, 54), s26);
         dfa.intTr(new Pair(s27, 55), s27);
         dfa.intTr(new Pair(s27, 56), s28);
         dfa.intTr(new Pair(s27, 57), s29);
         dfa.intTr(new Pair(s27, 46), s54);
         dfa.intTr(new Pair(s26, 949), s26);
         dfa.intTr(new Pair(s26, 48), s20);
         dfa.intTr(new Pair(s26, 49), s21);
         dfa.intTr(new Pair(s26, 50), s22);
         dfa.intTr(new Pair(s26, 51), s23);
         dfa.intTr(new Pair(s26, 52), s24);
         dfa.intTr(new Pair(s26, 53), s25);
         dfa.intTr(new Pair(s26, 54), s26);
         dfa.intTr(new Pair(s26, 55), s27);
         dfa.intTr(new Pair(s26, 56), s28);
         dfa.intTr(new Pair(s26, 57), s29);
         dfa.intTr(new Pair(s26, 46), s54);
         dfa.intTr(new Pair(s25, 949), s25);
         dfa.intTr(new Pair(s25, 48), s20);
         dfa.intTr(new Pair(s25, 49), s21);
         dfa.intTr(new Pair(s25, 50), s22);
         dfa.intTr(new Pair(s25, 51), s23);
         dfa.intTr(new Pair(s25, 52), s24);
         dfa.intTr(new Pair(s25, 53), s25);
         dfa.intTr(new Pair(s25, 54), s26);
         dfa.intTr(new Pair(s25, 55), s27);
         dfa.intTr(new Pair(s25, 56), s28);
         dfa.intTr(new Pair(s25, 57), s29);
         dfa.intTr(new Pair(s25, 46), s54);
         dfa.intTr(new Pair(s24, 949), s24);
         dfa.intTr(new Pair(s24, 48), s20);
         dfa.intTr(new Pair(s24, 49), s21);
         dfa.intTr(new Pair(s24, 50), s22);
         dfa.intTr(new Pair(s24, 51), s23);
         dfa.intTr(new Pair(s24, 52), s24);
         dfa.intTr(new Pair(s24, 53), s25);
         dfa.intTr(new Pair(s24, 54), s26);
         dfa.intTr(new Pair(s24, 55), s27);
         dfa.intTr(new Pair(s24, 56), s28);
         dfa.intTr(new Pair(s24, 57), s29);
         dfa.intTr(new Pair(s24, 46), s54);
         dfa.intTr(new Pair(s23, 949), s23);
         dfa.intTr(new Pair(s23, 48), s20);
         dfa.intTr(new Pair(s23, 49), s21);
         dfa.intTr(new Pair(s23, 50), s22);
         dfa.intTr(new Pair(s23, 51), s23);
         dfa.intTr(new Pair(s23, 52), s24);
         dfa.intTr(new Pair(s23, 53), s25);
         dfa.intTr(new Pair(s23, 54), s26);
         dfa.intTr(new Pair(s23, 55), s27);
         dfa.intTr(new Pair(s23, 56), s28);
         dfa.intTr(new Pair(s23, 57), s29);
         dfa.intTr(new Pair(s23, 46), s54);
         dfa.intTr(new Pair(s22, 949), s22);
         dfa.intTr(new Pair(s22, 48), s20);
         dfa.intTr(new Pair(s22, 49), s21);
         dfa.intTr(new Pair(s22, 50), s22);
         dfa.intTr(new Pair(s22, 51), s23);
         dfa.intTr(new Pair(s22, 52), s24);
         dfa.intTr(new Pair(s22, 53), s25);
         dfa.intTr(new Pair(s22, 54), s26);
         dfa.intTr(new Pair(s22, 55), s27);
         dfa.intTr(new Pair(s22, 56), s28);
         dfa.intTr(new Pair(s22, 57), s29);
         dfa.intTr(new Pair(s22, 46), s54);
         dfa.intTr(new Pair(s21, 949), s21);
         dfa.intTr(new Pair(s21, 48), s20);
         dfa.intTr(new Pair(s21, 49), s21);
         dfa.intTr(new Pair(s21, 50), s22);
         dfa.intTr(new Pair(s21, 51), s23);
         dfa.intTr(new Pair(s21, 52), s24);
         dfa.intTr(new Pair(s21, 53), s25);
         dfa.intTr(new Pair(s21, 54), s26);
         dfa.intTr(new Pair(s21, 55), s27);
         dfa.intTr(new Pair(s21, 56), s28);
         dfa.intTr(new Pair(s21, 57), s29);
         dfa.intTr(new Pair(s21, 46), s54);
         dfa.intTr(new Pair(s20, 949), s20);
         dfa.intTr(new Pair(s20, 48), s20);
         dfa.intTr(new Pair(s20, 49), s21);
         dfa.intTr(new Pair(s20, 50), s22);
         dfa.intTr(new Pair(s20, 51), s23);
         dfa.intTr(new Pair(s20, 52), s24);
         dfa.intTr(new Pair(s20, 53), s25);
         dfa.intTr(new Pair(s20, 54), s26);
         dfa.intTr(new Pair(s20, 55), s27);
         dfa.intTr(new Pair(s20, 56), s28);
         dfa.intTr(new Pair(s20, 57), s29);
         dfa.intTr(new Pair(s20, 46), s54);
         dfa.intTr(new Pair(s10, 949), s10);
         dfa.intTr(new Pair(s10, 48), s20);
         dfa.intTr(new Pair(s10, 49), s21);
         dfa.intTr(new Pair(s10, 50), s22);
         dfa.intTr(new Pair(s10, 51), s23);
         dfa.intTr(new Pair(s10, 52), s24);
         dfa.intTr(new Pair(s10, 53), s25);
         dfa.intTr(new Pair(s10, 54), s26);
         dfa.intTr(new Pair(s10, 55), s27);
         dfa.intTr(new Pair(s10, 56), s28);
         dfa.intTr(new Pair(s10, 57), s29);
         dfa.intTr(new Pair(s10, 46), s54);
         dfa.intTr(new Pair(s9, 949), s9);
         dfa.intTr(new Pair(s9, 48), s20);
         dfa.intTr(new Pair(s9, 49), s21);
         dfa.intTr(new Pair(s9, 50), s22);
         dfa.intTr(new Pair(s9, 51), s23);
         dfa.intTr(new Pair(s9, 52), s24);
         dfa.intTr(new Pair(s9, 53), s25);
         dfa.intTr(new Pair(s9, 54), s26);
         dfa.intTr(new Pair(s9, 55), s27);
         dfa.intTr(new Pair(s9, 56), s28);
         dfa.intTr(new Pair(s9, 57), s29);
         dfa.intTr(new Pair(s9, 46), s54);
         dfa.intTr(new Pair(s8, 949), s8);
         dfa.intTr(new Pair(s8, 48), s20);
         dfa.intTr(new Pair(s8, 49), s21);
         dfa.intTr(new Pair(s8, 50), s22);
         dfa.intTr(new Pair(s8, 51), s23);
         dfa.intTr(new Pair(s8, 52), s24);
         dfa.intTr(new Pair(s8, 53), s25);
         dfa.intTr(new Pair(s8, 54), s26);
         dfa.intTr(new Pair(s8, 55), s27);
         dfa.intTr(new Pair(s8, 56), s28);
         dfa.intTr(new Pair(s8, 57), s29);
         dfa.intTr(new Pair(s8, 46), s54);
         dfa.intTr(new Pair(s7, 949), s7);
         dfa.intTr(new Pair(s7, 48), s20);
         dfa.intTr(new Pair(s7, 49), s21);
         dfa.intTr(new Pair(s7, 50), s22);
         dfa.intTr(new Pair(s7, 51), s23);
         dfa.intTr(new Pair(s7, 52), s24);
         dfa.intTr(new Pair(s7, 53), s25);
         dfa.intTr(new Pair(s7, 54), s26);
         dfa.intTr(new Pair(s7, 55), s27);
         dfa.intTr(new Pair(s7, 56), s28);
         dfa.intTr(new Pair(s7, 57), s29);
         dfa.intTr(new Pair(s7, 46), s54);
         dfa.intTr(new Pair(s6, 949), s6);
         dfa.intTr(new Pair(s6, 48), s20);
         dfa.intTr(new Pair(s6, 49), s21);
         dfa.intTr(new Pair(s6, 50), s22);
         dfa.intTr(new Pair(s6, 51), s23);
         dfa.intTr(new Pair(s6, 52), s24);
         dfa.intTr(new Pair(s6, 53), s25);
         dfa.intTr(new Pair(s6, 54), s26);
         dfa.intTr(new Pair(s6, 55), s27);
         dfa.intTr(new Pair(s6, 56), s28);
         dfa.intTr(new Pair(s6, 57), s29);
         dfa.intTr(new Pair(s6, 46), s54);
         dfa.intTr(new Pair(s5, 949), s5);
         dfa.intTr(new Pair(s5, 48), s20);
         dfa.intTr(new Pair(s5, 49), s21);
         dfa.intTr(new Pair(s5, 50), s22);
         dfa.intTr(new Pair(s5, 51), s23);
         dfa.intTr(new Pair(s5, 52), s24);
         dfa.intTr(new Pair(s5, 53), s25);
         dfa.intTr(new Pair(s5, 54), s26);
         dfa.intTr(new Pair(s5, 55), s27);
         dfa.intTr(new Pair(s5, 56), s28);
         dfa.intTr(new Pair(s5, 57), s29);
         dfa.intTr(new Pair(s5, 46), s54);
         dfa.intTr(new Pair(s4, 949), s4);
         dfa.intTr(new Pair(s4, 48), s20);
         dfa.intTr(new Pair(s4, 49), s21);
         dfa.intTr(new Pair(s4, 50), s22);
         dfa.intTr(new Pair(s4, 51), s23);
         dfa.intTr(new Pair(s4, 52), s24);
         dfa.intTr(new Pair(s4, 53), s25);
         dfa.intTr(new Pair(s4, 54), s26);
         dfa.intTr(new Pair(s4, 55), s27);
         dfa.intTr(new Pair(s4, 56), s28);
         dfa.intTr(new Pair(s4, 57), s29);
         dfa.intTr(new Pair(s4, 46), s54);
         dfa.intTr(new Pair(s3, 949), s3);
         dfa.intTr(new Pair(s3, 48), s20);
         dfa.intTr(new Pair(s3, 49), s21);
         dfa.intTr(new Pair(s3, 50), s22);
         dfa.intTr(new Pair(s3, 51), s23);
         dfa.intTr(new Pair(s3, 52), s24);
         dfa.intTr(new Pair(s3, 53), s25);
         dfa.intTr(new Pair(s3, 54), s26);
         dfa.intTr(new Pair(s3, 55), s27);
         dfa.intTr(new Pair(s3, 56), s28);
         dfa.intTr(new Pair(s3, 57), s29);
         dfa.intTr(new Pair(s3, 46), s54);
         dfa.intTr(new Pair(s2, 949), s2);
         dfa.intTr(new Pair(s2, 48), s20);
         dfa.intTr(new Pair(s2, 49), s21);
         dfa.intTr(new Pair(s2, 50), s22);
         dfa.intTr(new Pair(s2, 51), s23);
         dfa.intTr(new Pair(s2, 52), s24);
         dfa.intTr(new Pair(s2, 53), s25);
         dfa.intTr(new Pair(s2, 54), s26);
         dfa.intTr(new Pair(s2, 55), s27);
         dfa.intTr(new Pair(s2, 56), s28);
         dfa.intTr(new Pair(s2, 57), s29);
         dfa.intTr(new Pair(s2, 46), s54);
         dfa.intTr(new Pair(s53, 949), s53);
         dfa.intTr(new Pair(s53, 111), s50);
         dfa.intTr(new Pair(s50, 949), s50);
         dfa.intTr(new Pair(s52, 949), s52);
         dfa.intTr(new Pair(s52, 104), s55);
         dfa.intTr(new Pair(s55, 949), s55);
         dfa.intTr(new Pair(s55, 105), s56);
         dfa.intTr(new Pair(s56, 949), s56);
         dfa.intTr(new Pair(s56, 108), s57);
         dfa.intTr(new Pair(s57, 949), s57);
         dfa.intTr(new Pair(s57, 101), s51);
         dfa.intTr(new Pair(s51, 949), s51);
     }


     @Override
     public Token getToken() throws Exception {
         StringBuilder s = new StringBuilder();
         while((dfa.lastState() == null) || (!dfa.lastState().equals(DFA.deadState))) {
             consume();
             if (currentCharacter == (int) '\n') line++;
             if (currentCharacter == -1) return Token.EOF_TOKEN;
             s.append((char)currentCharacter);
             dfa.simulate(s.toString()+((char)nextCharacter));
         }
         if (dfa.lastState().equals(DFA.deadState)) {
             if (dfa.simulate(s.toString())) {
                 return new Token(dfa.lastState().attached(), s.toString());
             }
         }
         throw new Exception("Line : " + line + "Invalid lexeme");
     }

     private void consume() throws Exception {
         currentCharacter = nextCharacter;
         nextCharacter = reader.read();
     }
     public int line() {
         return line;
     }
}
