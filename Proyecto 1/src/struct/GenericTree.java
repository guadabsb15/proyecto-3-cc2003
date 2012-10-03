/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package struct;

import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class GenericTree<E> {
    
    protected E value;
    
    protected GenericTree<E> parent;
    
    protected ArrayList<GenericTree<E>> children;
    
    public GenericTree() {
        value = null;
        children = new ArrayList();
    }
    
    public GenericTree(E val) {
        value = val;
        children = new ArrayList();
    }
    
    public GenericTree(E val, ArrayList<GenericTree<E>> treeChildren) {
        value = val;
        children = treeChildren;
        for (int i = 0; i < children.size(); i++) {
            children.get(i).setParent(this);
        }
    }

    public void setParent(GenericTree<E> p) {
        parent = p;
    }
    
    public void setValue(E v) {
        value = v;
    }
    
    public void setChildren(ArrayList<GenericTree<E>> c) {
        children = c;
    }
    
    public void add(GenericTree<E> node) {
        children.add(node);
    }
    
    public void add(GenericTree<E> node, int pos) {
        children.add(pos, node);
    }
    
    public ArrayList<GenericTree<E>> children() {
        return children;
    }
    
    public E value() {
        return value();
    }
    
    public GenericTree<E> parent() {
        return parent;
    }
    
}
