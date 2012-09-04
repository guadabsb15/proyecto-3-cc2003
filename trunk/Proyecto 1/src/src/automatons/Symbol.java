/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

/**
 *
 * @author asus
 */
public class Symbol {
    
    private char id;
    
    private State associated;
    
    private int type;
    
    public Symbol(Symbol sym) {
        this.id = sym.id();
    }
    
    public Symbol(String s) {
        id = s.charAt(0);
    }
    
    public Symbol(char c) {
        id = c;
    }
    
    public Symbol(String s, int t) {
        id = s.charAt(0);
        type = t;
    }
    
    public Symbol(char c, int t) {
        id = c;
        type = t;
    }
    
    public char id() {
        return id;
    }
    
    public int type() {
        return type;
    }
    
    public void setType(int t) {
        type = t;
    }
    
    public void setState(State s) {
        associated = s;
    }
    
    public State associated() {
        return associated;
    }

    
    @Override
    public String toString() {
        return Character.toString(id);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.id;
        return hash;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(this.getClass())) {
            Symbol other = (Symbol) o;
            return other.id() == (this.id);
        } else if (o.getClass().equals("String".getClass())) {
            String s = (String) o;
            return id == s.charAt(0);
        }
        return false;
    }
    
    
    
    
}
