/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author asus
 */
public class State {
    
    private String id;
    
    private int type;
    
    private Set<State> set = null;
    
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
    
    public State(String n) {
        id = n;

    }
    
    public State(Set<State> s) {
        set = s;
    }
    
    public String id() {
        return id;
    }
    
    public int type() {
        return type;
    }
    
    public Set<State> set() {
        return set;
    }
    
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
                return other.id().equals(id) && other.type() == type;
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
        hash = 17 * hash + this.type;
        return hash;
    }
    
    @Override
    public String toString() {
        return id;
    }
    
    public void changeType(int t) {
        type = t;
    }
}
