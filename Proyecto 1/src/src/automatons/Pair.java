/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.automatons;

/**
 *
 * @author asus
 */
public class Pair<E, F> {
    
    /**
     * First object of the pair
     */
    private E p0;
    
    /**
     * Second object of the pair
     */
    private F p1;
    
    /**
     * Class constructor
     * @param e
     * @param f 
     */
    public Pair(E e, F f) {
        p0 = e;
        p1 = f;
    }
    
    /**
     * Returns the first object
     * @return 
     */
    public E returnFirst() {
        return p0;
    }
    
    /**
     * Returns the second object
     * @return 
     */
    public F returnSecond() {
        return p1;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.p0 != null ? this.p0.hashCode() : 0);
        hash = 47 * hash + (this.p1 != null ? this.p1.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(this.getClass())) {
            Pair<E, F> other = (Pair<E, F>) o;
            return other.returnFirst().equals(p0) && other.returnSecond().equals(p1);
        } 
        return false;
        
    }
    

}
