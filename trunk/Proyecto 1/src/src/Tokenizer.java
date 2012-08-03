/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Separates an expression into its operators and operands.
 * @author asus
 */
public class Tokenizer {
      
    //private final String delimiters; 
    
    /**
     * Object that separates strings into tokens
     */
    private StringTokenizer tokenizer;
   
    /**
     * Lista con los tokens separados
     */
    private ArrayList tokens;
    
    /**
     * Constructor
     */
    public Tokenizer() {
        tokens = new ArrayList();
    }    
    
    /**
     * Returns a list with the strings' tokens, separated according to the delimiters provided
     * The delimiters are included..
     * @param expression String to be separated into tokens
     * @param delimiters Delimiters, included in a single string
     * @return ArrayList with the tokens
     */
    public ArrayList returnTokens(String expression, String delimiters) {
        expression = expression.replaceAll(" ", "");
        tokenizer = new StringTokenizer(expression, delimiters, true);
        tokens.clear();
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
        
    }
    
}
