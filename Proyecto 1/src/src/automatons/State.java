/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author asus
 */
public class State {
    
    /**
     * Identifier of the state
     */
    private String id;
   
    /**
     * Holds the state that compose this particular state, if applicable
     */
    private Set<State> set = null;
    
    private String attached;
    
     /**
     * Represents an initial state
     */
    public final static int INITIAL_STATE = 1;
    
    /**
     * Represents a state that is not initial or accepting
     */
    public final static int NORMAL_STATE = 2;
    
    /**
     * Represents an accepting state
     */
    public final static int ACCEPTING_STATE = 3;
    
    /**
     * Class constructor from an id
     * @param n 
     */
    public State(String n) {
        id = n;
    }
    
    /**
     * Class constructor from a set of states
     * @param s 
     */
    public State(Set<State> s) {
        set = s;
        //attached = s.attached();
    }
    
    /**
     * 
     * @return 
     */
    public String attached() {
        return attached;
    }
    
    /**
     * 
     * @param s 
     */
    public void setAttached(String s) {
        attached = s;
    }
    
    /**
     * Returns the identifier
     * @return 
     */
    public String id() {
        return id;
    }
    
    /**
     * Returns the set of states that compose this particular state
     * @return 
     */
    public Set<State> set() {
        return set;
    }
    
    
    
    /**
     * Returns the state wrapped in a set
     * @return 
     */
    public LinkedHashSet<State> toSet() {
        LinkedHashSet set = new LinkedHashSet();
        set.add(this);
        return set;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(this.getClass())) {
            State other = (State) o;
            if (set == null) {
                return other.id().equals(id);
            } else {
                return other.set().equals(set);
            }
            
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 17 * hash;
        return hash;
    }
    
    @Override
    public String toString() {
        return id;
    }
    
}
