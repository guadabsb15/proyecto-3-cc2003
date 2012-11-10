/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import src.Regexer;
import struct.GenericTree;

/**
 * Parses a file based on a subset of the Coco/R specification
 * @author asus
 */
public class CocoFileParser {
    
    /**
     * Lexer that provides the token stream
     */
    private Scanner lexer;
    
    private GenericTree<Token> parseTree;
    
    /**
     * Current token being analyzed
     */
    private Token currentToken;
    
    /**
     * Lookahead to the next token
     */
    private Token lookAhead;
    
    /**
     * Compiler name as defined in the read file
     */
    private String compiler;
    
    /**
     * Holds the defined character sets
     */
    private Map<String, Set<Character>> charactersTable;
    
    /**
     * Holds the defined keywords
     */
    private Map<String, String> keywordsTable;
    
    /**
     * Holds the set of characters to ignore
     */
    private Map<String, Set<Character>> ignoreTable;
    
    /**
     * Holds the defined tokens
     */
    private Map<String, String> tokensTable;
    
    /**
     * Identifiers that have an EXCEPT KEYWORDS associated
     */
    private ArrayList<String> excepts;
    
    public CocoFileParser(String filename) throws Exception {
        lexer = new CocoFileScanner(filename);
        lookAhead = lexer.getToken();
        consume();
        parseTree = new GenericTree();
        charactersTable = new LinkedHashMap();
        keywordsTable = new LinkedHashMap();
        tokensTable = new LinkedHashMap();
        ignoreTable = new LinkedHashMap(1);
        excepts = new ArrayList();
    }
    
    public CocoFileParser(File file) throws Exception {
        lexer = new CocoFileScanner(file);
        lookAhead = lexer.getToken();
        consume();
        parseTree = new GenericTree();
        charactersTable = new LinkedHashMap();
        keywordsTable = new LinkedHashMap();
        tokensTable = new LinkedHashMap();
        ignoreTable = new LinkedHashMap(1);
        excepts = new ArrayList();
    }
    
    /**
     * Parses the file
     * @throws Exception 
     */
   public void parse() throws Exception { 
       compile();
       characters();
       keywords();
       tokens();
       ignore();
       productions();
       //return parseTree;
   }
   
   public ArrayList<String> excepts() {
       return excepts;
   }
   
   public Map<String, String> tokensTable() {
       return tokensTable;
   }
   
   public Map<String, String> keywordsTable() {
       return keywordsTable;
   }
   
   public Map<String, String> ignoreTable() {
            Set set = ignoreTable.get("IGNORE");
            if (!set.isEmpty()) {
                Iterator i = set.iterator();
                String s = "";
                while (i.hasNext()) {
                    s = s + (i.next()) + ((char)Regexer.OR);
                }
                Map<String, String> ignoreMap = new LinkedHashMap<String, String>(1);
                ignoreMap.put("IGNORE", s.substring(0, s.length()-1));
                return ignoreMap;
            }
            
       
       return null;
   }

    private void compile() throws Exception {
        matchValue("COMPILER");
        compiler = matchType(Token.IDENT);
    }
    
    private void characters() throws Exception {
        matchValue("CHARACTERS");
        while (!verifyValue("KEYWORDS")) {
            setDeclaration();
            matchType(Token.POINT);
            //matchType(Token.NEWLINE);
        }
    }
    
    private void keywords() throws Exception {
        matchValue("KEYWORDS");
        while (!verifyValue("TOKENS")) {
            keywDeclaration();
        }
    }
    
    private void tokens() throws Exception {
        matchValue("TOKENS");
        while (!verifyValue("PRODUCTIONS") && !(verifyValue("IGNORE") && !lookAhead.type().equals(Token.EQUAL))) {
            tokenDecl();
            matchType(Token.POINT);
            //matchType(Token.NEWLINE);
        } 
    }

    private String matchType(String token) throws Exception {
        if (token.equals(currentToken.type())) {
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
        while (!currentToken.type().equals(Token.POINT)) {
            if (verifyValue("+")) {
                matchValue("+");
                addTo(assigned);
            } else {
                matchValue("-");
                substractTo(assigned);
            }
        }
        charactersTable.put(identifier, assigned);
    }

    private void addTo(Set<Character> assigned) throws Exception {
        if (currentToken.type().equals(Token.IDENT)) {
            Set assignation = (Set) charactersTable.get(currentToken.value());
            if (assignation == null) {
                throw new Exception("Identifier " + currentToken.value() + " must be defined previously");
            } else {
                assigned.addAll(assignation);
            }
            consume();
        } else if (currentToken.type().equals(Token.RESERVED)) {
            String s = currentToken.value();
            if (s.equals("CHR")) {
                matchValue("CHR");
                charAdd(assigned);
            } 
            //consume();
        } else if (currentToken.type().equals(Token.STRING)) {
            String s = currentToken.value();
            for (int i = 0; i < s.length(); i++) {
                assigned.add(s.charAt(i));
            }
            consume();
        } else if (currentToken.type().equals(Token.CHAR)) {
            charAdd(assigned);
            
        } 
        //consume();
        //return assigned;
    }
    
    private void charAdd(Set<Character> assigned) throws Exception {
        char lower = (char) 1;
        if (currentToken.type().equals(Token.CHAR)) {
            lower = currentToken.value().charAt(0);
            assigned.add(lower);
            consume();
        } else if (currentToken.type().equals(Token.LPAREN)) {
            matchType(Token.LPAREN);
            try {
                int code = Integer.parseInt(matchType(Token.NUMBER));
                lower = (char) code;
                assigned.add(lower);
                matchType(Token.RPAREN);
            } catch (Exception e) {
                throw new MismatchedTokenException("Expected a number");
            }
            
        }
        
        if (lookAhead.type().equals(Token.POINT)) {
            matchType(Token.POINT);
            
            matchType(Token.POINT);
            char upper = (char) 1;
            
            if (currentToken.type().equals(Token.CHAR)) {
                upper = currentToken.value().charAt(0);
                consume();
            } else if (currentToken.type().equals(Token.RESERVED)) {
                matchValue("CHR");
                matchValue("(");
                try {
                    upper = (char) Integer.parseInt(matchType(Token.NUMBER));
                    matchValue(")");
                } catch (Exception e) {
                    throw new MismatchedTokenException("Expected a number");
                }
            
            }
            if ((int)upper >= (int) lower) {
                for (int i = (int)lower; i <= (int)upper; i++) {
                    assigned.add((char)i);
                }
            } else {
                throw new MismatchedTokenException("Expected a higher character");
            }
            
      }
                  
    }
    
    private void charSub(Set<Character> assigned) throws Exception {
        char lower = (char) 1;
        if (currentToken.type().equals(Token.CHAR)) {
            lower = currentToken.value().charAt(0);
            assigned.remove(lower);
            consume();
        } else if (currentToken.type().equals(Token.LPAREN)) {
            matchType(Token.LPAREN);
            try {
                int code = Integer.parseInt(matchType(Token.NUMBER));
                lower = (char) code;
                assigned.remove(lower);
                matchType(Token.RPAREN);
            } catch (Exception e) {
                throw new MismatchedTokenException("Expected a number");
            }
            
        }
        
        if (lookAhead.type().equals(Token.POINT)) {
            matchType(Token.POINT);
            
            matchType(Token.POINT);
            char upper = (char) 1;
            
            if (currentToken.type().equals(Token.CHAR)) {
                upper = currentToken.value().charAt(0);
                consume();
            } else if (currentToken.type().equals(Token.RESERVED)) {
                matchValue("CHR");
                matchValue("(");
                try {
                    upper = (char) Integer.parseInt(matchType(Token.NUMBER));
                    matchValue(")");
                } catch (Exception e) {
                    throw new MismatchedTokenException("Expected a number");
                }
            
            }
            if ((int)upper >= (int) lower) {
                for (int i = (int)lower; i <= (int)upper; i++) {
                    assigned.remove((char)i);
                }
            } else {
                throw new MismatchedTokenException("Expected a higher character");
            }
            
      }
                  
    }
    
    
    
    private void substractTo(Set<Character> assigned) throws Exception {
        if (currentToken.type().equals(Token.IDENT)) {
            Set assignation = (Set) charactersTable.get(currentToken.value());
            if (assignation == null) {
                throw new Exception("Identifier " + currentToken.value() + " must be defined previously");
            } else {
                assigned.removeAll(assignation);
            }
            consume();
        } else if (currentToken.type().equals(Token.RESERVED)) {
            String s = currentToken.value();
            if (s.equals("CHR")) {
                matchValue("CHR");
                charSub(assigned);
            } 
            
            //consume();
        } else if (currentToken.type().equals(Token.STRING)) {
            String s = currentToken.value();
            for (int i = 0; i < s.length(); i++) {
                assigned.remove(s.charAt(i));
            }
            consume();
        } else if (currentToken.type().equals(Token.CHAR)) {
            charSub(assigned);
        } 
        
        //return assigned;
    }

    private void keywDeclaration() throws Exception {
        String identifier = matchType(Token.IDENT);
        matchValue("=");
        String asignee = matchType(Token.STRING);
        matchType(Token.POINT);
        keywordsTable.put(identifier, asignee);
    }

    private void tokenDecl() throws Exception {
        String identifier = "";
        if (currentToken.type().equals(Token.IDENT)) {
            identifier = matchType(Token.IDENT);
        } else if (currentToken.type().equals(Token.RESERVED)) {
            identifier = currentToken.value();
            consume();
        }
        String regex = "";
        if (verifyValue("=")) {
            matchType(Token.EQUAL);
            regex = tokenExpr();
            String k = "";
        }
        exceptKw(identifier);
        tokensTable.put(identifier, regex);
        
    }

    private String tokenExpr() throws Exception {
        String expr = "";
        expr = expr + tokenTerm();
        while (verifyValue("|")) {
            matchType(Token.BAR);
            expr = expr + Regexer.OR;
            expr = expr + tokenTerm();
        }
        return expr;
    }

    private String tokenTerm() throws Exception {
        String expr = "";
        expr = expr + Regexer.LPAREN +  tokenFactor() + Regexer.RPAREN;
        if (verifyValue("|")) return expr;
        while (!verifyValue("EXCEPT") && !(currentToken.type().equals(Token.POINT)) && (!currentToken.type().equals(Token.RPAREN)) && (!currentToken.type().equals(Token.SQRBRACKET)) && (!currentToken.type().equals(Token.CURBRACKET))) {
            expr = expr + tokenFactor();
        }
        return expr;
    }

    private String tokenFactor() throws Exception {
        if (currentToken.type().equals(Token.STRING)) {
            return Regexer.LPAREN + matchType(Token.STRING) + Regexer.RPAREN;
        } else if (verifyValue("(")) {
            matchType(Token.LPAREN);
            String expr = Regexer.LPAREN + tokenExpr() + Regexer.RPAREN;
            matchType(Token.RPAREN);
            return expr;
        } else if (verifyValue("[")) {
            matchType(Token.SQLBRACKET);
            String expr = Regexer.LPAREN + tokenExpr() + Regexer.RPAREN + Regexer.ZERONE;
            matchType(Token.SQRBRACKET);
            return expr;
        } else if (verifyValue("{")) {
            matchType(Token.CULBRACKET);
            String expr = Regexer.LPAREN + tokenExpr() + Regexer.RPAREN + Regexer.KLEENE;
            matchType(Token.CURBRACKET);
            return expr;
        } else if (currentToken.type().equals(Token.IDENT)) {
            Set<Character> s = (Set<Character>) charactersTable.get(matchType(Token.IDENT));
            if (s == null) {
                throw new Exception("Invalid identifier");
            } else {
                Iterator it = s.iterator();
                String expr = "";
                while (it.hasNext()) expr = expr + it.next().toString() + Regexer.OR;
                return Regexer.LPAREN + expr.substring(0, expr.length()-1) + Regexer.RPAREN;    
            }
        } else if (currentToken.type().equals(Token.CHAR)) {
            return Regexer.LPAREN + matchType(Token.CHAR) + Regexer.RPAREN;
        } else if (currentToken.type().equals(Token.RESERVED)) {
            //if (verifyValue("CHR")) {
                matchValue("CHR");
                matchType(Token.LPAREN);
                int code = Integer.parseInt(matchType(Token.NUMBER));
                char ch = (char) code;
                matchType(Token.RPAREN);
                return ("" + Regexer.LPAREN + ch + Regexer.RPAREN);
            //} 
            
        } else throw new Exception ("Invalid token declaration");
    }

    

    private void makeCharSet(Set<Character> assigned) {
        assigned.add(currentToken.value().charAt(0));
    }

    public void setCurrent(Token t) {
        currentToken = t;
    }
    
    public void setLA(Token t) {
        lookAhead = t;
    }

    private void exceptKw(String id) throws Exception {
        if (currentToken.value().equals("EXCEPT")) {
            matchValue("EXCEPT");
            excepts.add(id);
            matchValue("KEYWORDS");
        }
        
    }

    private void ignore() throws Exception {
        Set<Character> ignoreSet = new LinkedHashSet(); 
        while (currentToken.value().equals("IGNORE")) {
            matchValue("IGNORE");
            //matchValue("=");
            addTo(ignoreSet);
            while (!(currentToken.type().equals(Token.POINT))) {
                if (verifyValue("+")) {
                    matchValue("+");
                    addTo(ignoreSet);
                } else {
                    matchValue("-");
                    substractTo(ignoreSet);
                }
            }
            matchType(Token.POINT);
        }
        ignoreTable.put("IGNORE", ignoreSet);
    }

    private void productions() throws Exception {
        matchValue("PRODUCTIONS");
    }

    
   
   
   class ParserException extends Exception {
       public ParserException(String msg) {
           super("Line " + lexer.line() + " :" + msg);
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
