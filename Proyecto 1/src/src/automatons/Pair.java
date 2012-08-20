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
    
    private E p0;
    
    private F p1;
    
    public Pair(E e, F f) {
        p0 = e;
        p1 = f;
    }
    
    public E returnFirst() {
        return p0;
    }
    
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
