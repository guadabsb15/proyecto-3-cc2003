/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author asus
 */
public class CocoFileScanner implements Scanner {
    
    /**
     * Object to read the file
     */
    private BufferedReader reader;
    
    /**
     * Current character being scanned
     */
    private int currentCharacter;
    
    /**
     * Lookahead to the next character in the file
     */
    private int nextCharacter;
    
    /**
     * Flag to process comments
     */
    private boolean insideComment;
    
    private ArrayList<Token> tokens;
    
    /**
     * Characters to ignore
     */
    private Set<Character> ignore;
    
    private int line;
    
    /**
     * Class constructor
     * @param filename
     * @throws Exception 
     */
    public CocoFileScanner(String filename) throws Exception {
        try {
            insideComment = false;
            line = 1;
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8")); 
            nextCharacter = reader.read();
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
    
    /**
     * Class constructor
     * @param file
     * @throws Exception 
     */
    public CocoFileScanner(File file) throws Exception {
        try {
            insideComment = false;
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")); 
            nextCharacter = reader.read();
            consume();
            tokens = new ArrayList();
            ignore = new LinkedHashSet();
            ignore.add(' ');
            ignore.add('\r');
            ignore.add('\n');
            ignore.add((char) 9);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Returns the current line being read
     * @return 
     */
    public int line() {
        return line;
    }
    
    /**
     * Returns the next token from the file
     * @return
     * @throws Exception 
     */
    @Override
    public Token getToken() throws Exception {
        try {
            
            while (ignore.contains((char)currentCharacter) || ((currentCharacter == (int) '(') && (nextCharacter == (int)'.'))) {
                if (currentCharacter == '\n') line++;
                if ((currentCharacter == (int) '(') && (nextCharacter == (int)'.')) insideComment = true;
                while (insideComment) {
                    if ((currentCharacter == (int) '.') && (nextCharacter == (int) ')')) {
                        insideComment = false;
                        consume();
                        //consume();
                    }
                    consume();
                }
                consume();
            }

            char current = (char) currentCharacter;
            
            if (Character.isDigit(current)) {
                return number();
            } else if (Character.isLetter(current)) {
                return identifier();
            } else if (current == '"') {
                return string();
            } else if (current == '\'')  {
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
        else if (str.equals("CHR")) return (new Token(Token.RESERVED, str));
        else if (str.equals("IGNORE")) return (new Token(Token.RESERVED, str));
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
        
        match('\'');
        
        if ( (char) currentCharacter != '\\') {
            s.append((char)currentCharacter);
            consume();
            match('\'');
            return (new Token(Token.CHAR, s.toString()));
        } else {
            match('\\');
            s.append((char)currentCharacter);
            match('\'');
            return (new Token(Token.CHAR, s.toString()));
        }

    }
    
    private Token terminal() throws Exception {
        char current = (char) currentCharacter;
        consume();
        if (current == '(') return (new Token(Token.LPAREN, Character.toString(current)));
        else if (current == ')') return (new Token(Token.RPAREN, Character.toString(current)));
        else if (current == '=') return (new Token(Token.EQUAL, Character.toString(current)));
        else if (current == '+') return (new Token(Token.PLUS, Character.toString(current)));
        else if (current == '-') return (new Token(Token.MINUS, Character.toString(current)));
        else if (current == '.') return (new Token(Token.POINT, Character.toString(current)));
        else if (current == '[') return (new Token(Token.SQLBRACKET, Character.toString(current)));
        else if (current == ']') return (new Token(Token.SQRBRACKET, Character.toString(current)));
        else if (current == '{') return (new Token(Token.CULBRACKET, Character.toString(current)));
        else if (current == '}') return (new Token(Token.CURBRACKET, Character.toString(current)));
        else if (current == '|') return (new Token(Token.BAR, Character.toString(current)));
        else if (current == '\n') return (new Token(Token.NEWLINE, Character.toString('\n')));
        return null;
    }
    
    

    private void consume() throws Exception {
        try {
            currentCharacter = nextCharacter;
            nextCharacter = reader.read(); 
           
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
    
    public ArrayList<Token> tokens() {
        return tokens;
    }
    
    
}
