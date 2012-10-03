/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

/**
 *
 * @author asus
 */
public interface TokenTypes {
    
    public static final int IDENT = 0;
    
    public static final int NUMBER = 1;
    
    public static final int STRING = 2;
    
    public static final int CHAR = 3;
    
    public static final int RESERVED = 4;
    
    public static final int LPAREN = 5;
    
    public static final int RPAREN = 6;
    
    public static final int EQUAL = 7;
    
    public static final int EOF = 8;
    
    public static final int IGNORE = 9;
    
    public static final Token EOF_TOKEN = new Token(EOF, "-1");
    
}
