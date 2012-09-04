/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import src.automatons.DFABuilder;
import src.automatons.NFABuilder;
import src.automatons.Symbol;

/**
 *
 * @author asus
 */
public class Proyecto1 {
    
    public final static Symbol EMPTY_STR = (new Symbol("ε"));
    
    private NFABuilder nfabuilder;
    
    private DFABuilder dfabuilder;
    
    private Regexer regexer;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
