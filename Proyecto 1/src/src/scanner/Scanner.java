/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

/**
 *
 * @author asus
 */
public interface Scanner {
    
    /**
     * Returns the next token from the analyzed file
     * @return
     * @throws Exception 
     */
    public Token getToken() throws Exception ;
    
    public int line();
}
