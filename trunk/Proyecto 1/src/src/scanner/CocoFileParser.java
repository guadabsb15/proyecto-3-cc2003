/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

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
 *
 * @author asus
 */
public class CocoFileParser {
    
    private CocoFileScanner lexer;
    
    private GenericTree<Token> parseTree;
    
    private Token currentToken;
    
    private Token lookAhead;
    
    private String compiler;
    
    private Map<String, Set<Character>> charactersTable;
    
    private Map<String, String> keywordsTable;
    
    private Map<String, Set<Character>> ignoreTable;
    
    private Map<String, String> tokensTable;
    
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
    
   public void parse() throws Exception { 
       compile();
       characters();
       keywords();
       tokens();
       ignore();
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
   
   public Map<String, Set<Character>> ignoreTable() {
       return ignoreTable;
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
    
    private void tokens() throws Exception {
        matchValue("TOKENS");
        while (!verifyValue("PRODUCTIONS") && !verifyValue("IGNORE")) {
            tokenDecl();
            matchType(Token.POINT);
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
        charactersTable.put(identifier, assigned);
    }

    private void addTo(Set<Character> assigned) throws Exception {
        if (currentToken.type() == Token.IDENT) {
            Set assignation = (Set) charactersTable.get(currentToken.value());
            if (assignation == null) {
                throw new Exception("Identifier " + currentToken.value() + " must be defined previously");
            } else {
                assigned.addAll(assignation);
            }
            consume();
        } else if (currentToken.type() == Token.RESERVED) {
            String s = currentToken.value();
            if (s.equals("CHR")) {
                matchValue("CHR");
                charAdd(assigned);
            } 
            //consume();
        } else if (currentToken.type() == Token.STRING) {
            String s = currentToken.value();
            for (int i = 0; i < s.length(); i++) {
                assigned.add(s.charAt(i));
            }
            consume();
        } else if (currentToken.type() == Token.CHAR) {
            charAdd(assigned);
            
        } 
        //consume();
        //return assigned;
    }
    
    private void charAdd(Set<Character> assigned) throws Exception {
        char lower = (char) 1;
        if (currentToken.type() == Token.CHAR) {
            lower = currentToken.value().charAt(0);
            assigned.add(lower);
            consume();
        } else if (currentToken.type() == Token.LPAREN) {
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
        
        if (lookAhead.type() == Token.POINT) {
            matchType(Token.POINT);
            
            matchType(Token.POINT);
            char upper = (char) 1;
            
            if (currentToken.type() == Token.CHAR) {
                upper = currentToken.value().charAt(0);
                consume();
            } else if (currentToken.type() == Token.RESERVED) {
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
        if (currentToken.type() == Token.CHAR) {
            lower = currentToken.value().charAt(0);
            assigned.remove(lower);
            consume();
        } else if (currentToken.type() == Token.LPAREN) {
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
        
        if (lookAhead.type() == Token.POINT) {
            matchType(Token.POINT);
            
            matchType(Token.POINT);
            char upper = (char) 1;
            
            if (currentToken.type() == Token.CHAR) {
                upper = currentToken.value().charAt(0);
                consume();
            } else if (currentToken.type() == Token.RESERVED) {
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
        if (currentToken.type() == Token.IDENT) {
            Set assignation = (Set) charactersTable.get(currentToken.value());
            if (assignation == null) {
                throw new Exception("Identifier " + currentToken.value() + " must be defined previously");
            } else {
                assigned.removeAll(assignation);
            }
            consume();
        } else if (currentToken.type() == Token.RESERVED) {
            String s = currentToken.value();
            if (s.equals("CHR")) {
                matchValue("CHR");
                charSub(assigned);
            } 
            
            //consume();
        } else if (currentToken.type() == Token.STRING) {
            String s = currentToken.value();
            for (int i = 0; i < s.length(); i++) {
                assigned.remove(s.charAt(i));
            }
            consume();
        } else if (currentToken.type() == Token.CHAR) {
            charSub(assigned);
        } 
        
        //return assigned;
    }

    private void keywDeclaration() throws Exception {
        String identifier = matchType(Token.IDENT);
        matchValue("=");
        String asignee = matchType(Token.STRING);
        matchValue(".");
        keywordsTable.put(identifier, asignee);
    }

    private void tokenDecl() throws Exception {
        String identifier = matchType(Token.IDENT);
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
        while (!verifyValue("EXCEPT KEYWORDS") && !verifyValue(".") && (currentToken.type()!= Token.RPAREN) && (currentToken.type()!= Token.SQRBRACKET) && (currentToken.type()!= Token.CURBRACKET)) {
            expr = expr + tokenFactor();
        }
        return expr;
    }

    private String tokenFactor() throws Exception {
        if (verifyValue("(")) {
            matchType(Token.LPAREN);
            String expr = Regexer.LPAREN + tokenExpr() + Regexer.RPAREN;
            matchType(Token.RPAREN);
            return expr;
        } else if (verifyValue("[")) {
            matchType(Token.SQLBRACKET);
            String expr = tokenExpr();
            matchType(Token.SQRBRACKET);
            return expr;
        } else if (verifyValue("{")) {
            matchType(Token.CULBRACKET);
            String expr = Regexer.LPAREN + tokenExpr() + Regexer.RPAREN + Regexer.KLEENE;
            matchType(Token.CURBRACKET);
            return expr;
        } else if (currentToken.type() == Token.IDENT) {
            Set<Character> s = (Set<Character>) charactersTable.get(matchType(Token.IDENT));
            if (s == null) {
                throw new Exception("Invalid identifier");
            } else {
                Iterator it = s.iterator();
                String expr = "";
                while (it.hasNext()) expr = expr + it.next().toString() + Regexer.OR;
                return expr.substring(0, expr.length()-1);    
            }
        } else if (currentToken.type() == Token.STRING) {
            return matchType(Token.STRING);
        } else if (currentToken.type() == Token.CHAR) {
            return matchType(Token.CHAR);
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
        if (currentToken.value().equals("EXCEPT KEYWORDS")) {
            excepts.add(id);
            matchValue("EXCEPT KEYWORDS");
        }
        
    }

    private void ignore() throws Exception {
        Set<Character> ignoreSet = new LinkedHashSet(); 
        while (currentToken.value().equals("IGNORE")) {
            matchValue("IGNORE");
            //matchValue("=");
            addTo(ignoreSet);
            while (!verifyValue(".")) {
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
