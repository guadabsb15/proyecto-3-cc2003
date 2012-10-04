/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
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
    
    private LinkedHashMap symbolTable;
    
    public CocoFileParser(String filename) throws Exception {
        lexer = new CocoFileScanner(filename);
        lookAhead = lexer.getToken();
        consume();
        parseTree = new GenericTree();
        symbolTable = new LinkedHashMap();
    }
    
   public GenericTree<Token> parse() throws Exception { 
       compile();
       characters();
       keywords();
       return parseTree;
   }

    private void compile() throws Exception {
        matchValue("COMPILER");
        compiler = matchType(Token.IDENT);
    }
    
    private void characters() throws Exception {
        matchValue("CHARACTERS");
        while (!verifyValue("KEYWORDS")) {
            setDeclaration();
            matchValue(".");
        }
    }
    
    private void keywords() throws Exception {
        matchValue("KEYWORDS");
        while (!verifyValue("TOKENS")) {
            keywDeclaration();
        }
    }

    private String matchType(int token) throws Exception {
        if (token == currentToken.type()) {
            String val = currentToken.value();
            consume();
            return val;
        }
        else throw new MismatchedTokenException("Expected token type " + token + " , but found type " + currentToken.type() + " instead");
    }
    
    private void matchValue(String val) throws Exception {
        if (verifyValue(val)) consume();
        else throw new MismatchedTokenException("Expected token value " + val + " , but found value " + currentToken.value() + " instead");
    }

    private void consume() throws Exception {
        currentToken = lookAhead;
        lookAhead = lexer.getToken();
       
    }

    
    
    private boolean verifyValue(String val) {
        return(val.equals(currentToken.value()));
    }

    private void setDeclaration() throws Exception {
        String identifier = matchType(Token.IDENT);
        matchValue("=");
        Set<Character> assigned = new LinkedHashSet();
        addTo(assigned);
        while (!verifyValue(".")) {
            if (verifyValue("+")) {
                matchValue("+");
                addTo(assigned);
            } else {
                matchValue("-");
                substractTo(assigned);
            }
        }
        symbolTable.put(identifier, assigned);
        
    }

    private void addTo(Set<Character> assigned) throws Exception {
        if (currentToken.type() == Token.IDENT) {
            Set assignation = (Set) symbolTable.get(currentToken.value());
            if (assignation == null) {
                throw new Exception("Identifier " + currentToken.value() + " must be defined previously");
            } else {
                assigned.addAll(assignation);
            }
        } else if (currentToken.type() == Token.STRING) {
            String s = currentToken.value();
            for (int i = 0; i < s.length(); i++) {
                assigned.add(s.charAt(i));
            }
        } else if (currentToken.type() == Token.CHAR) {
            assigned.add(currentToken.value().charAt(0));
        } 
        consume();
        //return assigned;
    }
    
    private void substractTo(Set<Character> assigned) throws Exception {
        if (currentToken.type() == Token.IDENT) {
            Set assignation = (Set) symbolTable.get(currentToken.value());
            if (assignation == null) {
                throw new Exception("Identifier " + currentToken.value() + " must be defined previously");
            } else {
                assigned.removeAll(assignation);
            }
        } else if (currentToken.type() == Token.STRING) {
            String s = currentToken.value();
            for (int i = 0; i < s.length(); i++) {
                assigned.remove(s.charAt(i));
            }
        } else if (currentToken.type() == Token.CHAR) {
            assigned.remove(currentToken.value().charAt(0));
        } 
        consume();
        //return assigned;
    }

    private void keywDeclaration() throws Exception {
        String identifier = matchType(Token.IDENT);
        matchValue("=");
        String asignee = matchType(Token.STRING);
        matchValue(".");
        symbolTable.put(identifier, asignee);
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
