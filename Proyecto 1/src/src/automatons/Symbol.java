/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

/**
 * Represents a one-character symbol
 * @author asus
 */
public class Symbol {
    /**
     * Identifier
     */
    private char id;
    
    /**
     * Associated label
     */
    private State associated;
    
    /**
     * Class constructor from another symbol
     * @param sym 
     */
    public Symbol(Symbol sym) {
        this.id = sym.id();
    }
    
    /**
     * Class constructor from a String
     * @param s 
     */
    public Symbol(String s) {
        id = s.charAt(0);
    }
    
    /**
     * Class constructor from a char
     * @param c 
     */
    public Symbol(char c) {
        id = c;
    }
    
    /**
     * Returns the symbol's id
     * @return 
     */
    public char id() {
        return id;
    }
    
    /**
     * Set a state label
     * @param s 
     */
    public void setState(State s) {
        associated = s;
    }
    
    /**
     * Return the associated label
     * @return 
     */
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
