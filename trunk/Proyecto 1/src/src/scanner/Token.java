/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

/**
 *
 * @author asus
 */
class Token implements TokenTypes {
    
    private int type;
    
    private String value;
    
    public Token(int nm, String val) {
        type = nm;
        value = val;
    }
    
    public void changeName(int n) {
        type = n;
    }
    
    public void changeValue(String v) {
        value = v;
    }
    
    public int type() {
        return type;
    }
    
    public String value() {
        return value;
    }
    
    public boolean equals(Token other) {
        return (type == other.type()) && (value.equals(other.value()));
    }
    
    public String toString() {
        return type + ", " + value;
    }
    
}
