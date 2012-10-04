/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import struct.GenericTree;

/**
 *
 * @author asus
 */
public class CocoFileParser {
    
    private CocoFileScanner lexer;
    
    private GenericTree<Token> parseTree;
    
    private Token currentToken;
    
    private Token lookAhead;
    
    private String compiler;
    
    private LinkedHashMap characters;
    
    private LinkedHashMap keywords;
    
    private LinkedHashMap tokens;
    
    public CocoFileParser(String filename) throws Exception {
        lexer = new CocoFileScanner(filename);
        lookAhead = lexer.getToken();
        consume();
        parseTree = new GenericTree();
    }
    
   public GenericTree<Token> parse() throws Exception { 
       compile();
       characters();
       return parseTree;
   }

    private void compile() throws Exception {
        matchValue(new Token(Token.RESERVED, "COMPILER"));
        compiler = matchType(Token.IDENT);
    }

    private String matchType(int token) throws Exception {
        if (token == currentToken.type()) {
            String val = currentToken.value();
            consume();
            return val;
        }
        else throw new MismatchedTokenException("Expected token type " + token + " , but found type " + currentToken.type() + " instead");
    }
    
    private void matchValue(Token token) throws Exception {
        if (token.value().equals(currentToken.value())) consume();
        else throw new MismatchedTokenException("Expected token value " + token.value() + " , but found value " + currentToken.value() + " instead");
    }

    private void consume() throws Exception {
        currentToken = lookAhead;
        lookAhead = lexer.getToken();
       
    }

    private void characters() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
   
   
   class ParserException extends Exception {
       public ParserException(String msg) {
           super(msg);
       }
   }
   
   class MismatchedTokenException extends ParserException {
       public MismatchedTokenException(String msg) {
           super(msg);
       }
   }
   
   class NoViableAltException extends ParserException {
       public NoViableAltException(String msg) {
           super(msg);
       }
   }
    
}
