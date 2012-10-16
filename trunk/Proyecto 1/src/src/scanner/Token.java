/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

/**
 *
 * @author asus
 */
public class Token implements TokenTypes {
    
    private String type;
    
    private String value;
    
    public Token(String nm, String val) {
        type = nm;
        value = val;
    }
    
    public void changeName(String n) {
        type = n;
    }
    
    public void changeValue(String v) {
        value = v;
    }
    
    public String type() {
        return type;
    }
    
    public String value() {
        return value;
    }
    
    public boolean equals(Token other) {
        return (type.equals(other.type())) && (value.equals(other.value()));
    }
    
    @Override
    public String toString() {
        return type + ", " + value;
    }
    
}
