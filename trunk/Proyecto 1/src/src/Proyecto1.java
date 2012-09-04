/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Scanner;
import src.automatons.Automaton;
import src.automatons.DFABuilder;
import src.automatons.NFABuilder;
import src.automatons.Symbol;

/**
 *
 * @author asus
 */
public class Proyecto1 {
    
    public final static Symbol EMPTY_STR = (new Symbol("ε"));
    
    private Regexer regexer;
    
    private NFABuilder nfaBuilder;
    
    private DFABuilder dfaBuilder;
    
    private Automaton nfa;
    
    private Automaton nfaToDfa;
    
    private Automaton dfa;
    
    public Proyecto1() {
        regexer = new Regexer();
        nfaBuilder = new NFABuilder(regexer);
        dfaBuilder = new DFABuilder(regexer);
    }
    
    public void build(String rgx) throws Exception {
        nfa = nfaBuilder.build(rgx);
        nfa.writeFile("thomson.txt");
        nfaToDfa = nfa.toDfa();
        nfaToDfa.writeFile("subset.txt");
        dfa = dfaBuilder.build(rgx);
        nfa.writeFile("direct.txt");
    }
    
    public void simulate(String input) {
        System.out.println("Simulación:");
        long init = System.nanoTime();
        if (nfa.simulate(input)) {
            System.out.println("Tiempo: " + (System.nanoTime()-init));
            System.out.println("Thomson: SÍ");
        }
        else {
            System.out.println("Tiempo: " + (System.nanoTime()-init));
            System.out.println("Thomson: NO");
        }
        
        init = System.nanoTime();
        if (nfaToDfa.simulate(input)) {
            System.out.println("Tiempo: " + (System.nanoTime()-init));
            System.out.println("Subconjuntos: SÍ");
        }
        else {
            System.out.println("Tiempo: " + (System.nanoTime()-init));
            System.out.println("Subconjuntos: NO");
        }
        
        init = System.nanoTime();
        if (dfa.simulate(input)) {
            System.out.println("Tiempo: " + (System.nanoTime()-init));
            System.out.println("DFA directo: SÍ");
        }
        else {
            System.out.println("DFA directo: NO");
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("----------------------------------------");{
        Scanner in = new Scanner(System.in);
        Proyecto1 p = new Proyecto1();
        
        System.out.println("Ingrese la expresión regular: ");
        String regex = in.nextLine();
        try {
           p.build(regex); 
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        //System.out.println("Ingrese -1 para terminar de simular");
        System.out.println("Ingrese la cadena a validar (-1 para terminar): ");
        String input = in.nextLine();
        
        while (!input.equals("-1")) {
            p.simulate(input);
            System.out.println();
            System.out.println();
            System.out.println("Ingrese la cadena a validar (-1 para terminar): ");
            input = in.nextLine();
            
        }
        
    }
    }
}
