/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author asus
 */
public class CocoFileScanner {
    
    private BufferedReader reader;
    
    private int currentCharacter;
    
    private ArrayList<Token> tokens;
    
    private Set<Character> ignore;
    
    public CocoFileScanner(String filename) throws Exception {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8")); 
            consume();
            tokens = new ArrayList();
            ignore = new LinkedHashSet();
            ignore.add(' ');
            ignore.add('\r');
            ignore.add('\n');
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    
    public Token getToken() throws Exception {
        try {
            
            while (ignore.contains((char)currentCharacter)) consume();
            char current = (char) currentCharacter;
            if (Character.isDigit(current)) {
                return number();
            } else if (Character.isLetter(current)) {
                return identifier();
            } else if (current == '"') {
                return string();
            } else if (current == '\\')  {
                return character();
            } else {
                if (currentCharacter == -1) return (new Token(Token.EOF, "-1"));
                return terminal();
            }
            
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    private Token number() throws Exception {
        StringBuilder s = new StringBuilder();
        
        while (Character.isDigit((char) currentCharacter)) {
            s.append((char)currentCharacter);
            consume();
        }
        
        return (new Token(Token.NUMBER, s.toString()));
        
    }

    private Token identifier() throws Exception {
        StringBuilder s = new StringBuilder();
        
        while (Character.isLetter((char)currentCharacter) || Character.isDigit((char)currentCharacter)) {
            s.append((char)currentCharacter);
            consume();
        }
        String str = s.toString();
        if (str.equals("CHARACTERS")) return (new Token(Token.RESERVED, str));
        else if (str.equals("COMPILER")) return (new Token(Token.RESERVED, str));
        else if (str.equals("KEYWORDS")) return (new Token(Token.RESERVED, str));
        else if (str.equals("TOKENS")) return (new Token(Token.RESERVED, str));
        else return (new Token(Token.IDENT, s.toString()));

    }

    private Token string() throws Exception {
        StringBuilder s = new StringBuilder();
        match('"');
        
        while ((char)currentCharacter != '"' ) {
            s.append((char)currentCharacter);
            consume();
        }
        
        match('"');
        
        return (new Token(Token.STRING, s.toString()));
    }

    private Token character() throws Exception {
        StringBuilder s = new StringBuilder();
        
        match('\\');
        
        s.append((char)currentCharacter);
        
        consume();
        
        match('\\');
        
        return (new Token(Token.CHAR, s.toString()));
        
    }
    
    private Token terminal() throws Exception {
        char current = (char) currentCharacter;
        consume();
        if (current == '(') return (new Token(Token.LPAREN, Character.toString(current))); 
        else if (current == ')') return (new Token(Token.RPAREN, Character.toString(current)));
        else if (current == '=') return (new Token(Token.EQUAL, Character.toString(current)));
        
        return null;
    }
    
    

    private void consume() throws Exception {
        try {
            currentCharacter = reader.read(); 
            //if (currentCharacter == '\r') currentCharacter = reader.read(); 
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void match(char c) throws Exception {
        if  (((char) currentCharacter) == c) consume();
        else {
            throw new Exception("Expected " + c + ", found " + ((char)currentCharacter) + " instead");
        }
    }
    
    
    /**
    public void read() throws Exception {
        try {
            while (currentCharacter != -1) {
                char current = (char) currentCharacter;
                if (Character.isDigit(current)) {
                    number();
                } else if (Character.isLetter(current)) {
                    identifier();
                } else if (current == '"') {
                    string();
                } else if (current == '\\')  {
                    character();
                } else {
                    terminal();
                }
            }
            tokens.add(new Token(Token.EOF, "-1"));
        } catch (Exception e) {
            throw e;
        }
    }

    private void number() throws Exception {
        StringBuilder s = new StringBuilder();
        
        while (Character.isDigit((char) currentCharacter)) {
            s.append((char)currentCharacter);
            consume();
        }
        
        tokens.add(new Token(Token.NUMBER, s.toString()));
        
    }

    private void identifier() throws Exception {
        StringBuilder s = new StringBuilder();
        
        while (Character.isLetter((char)currentCharacter) || Character.isDigit((char)currentCharacter)) {
            s.append((char)currentCharacter);
            consume();
        }
        String str = s.toString();
        if (str.equals("CHARACTERS")) tokens.add(new Token(Token.RESERVED, str));
        else if (str.equals("KEYWORDS")) tokens.add(new Token(Token.RESERVED, str));
        else if (str.equals("TOKENS")) tokens.add(new Token(Token.RESERVED, str));
        else tokens.add(new Token(Token.IDENT, s.toString()));

    }

    private void string() throws Exception {
        StringBuilder s = new StringBuilder();
        match('"');
        
        while ((char)currentCharacter != '"' ) {
            s.append((char)currentCharacter);
            consume();
        }
        
        match('"');
        
        tokens.add(new Token(Token.STRING, s.toString()));
    }

    private void character() throws Exception {
        StringBuilder s = new StringBuilder();
        
        match('\\');
        
        s.append((char)currentCharacter);
        tokens.add(new Token(Token.CHAR, s.toString()));
        consume();
        
        match('\\');
        
    }

    

    private void terminal() throws Exception {
        char current = (char) currentCharacter;
        if (current == '(') tokens.add(new Token(Token.LPAREN, Character.toString(current))); 
        else if (current == ')') tokens.add(new Token(Token.RPAREN, Character.toString(current)));
        else if (current == '=') tokens.add(new Token(Token.EQUAL, Character.toString(current)));
        consume();
    }
    */
    public ArrayList<Token> tokens() {
        return tokens;
    }
    
    
}
