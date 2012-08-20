/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package struct;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author asus
 */
public class BTPostOrderIterator implements Iterator {
    protected BinaryTree root; // root of traversed subtree
    protected Queue<BinaryTree> todo; // stack of nodes
    
    public BTPostOrderIterator(BinaryTree tree) {
        root = tree;
        todo = new LinkedList<BinaryTree>();
        reset();
     }    
    
    public void reset()
// post: resets the iterator to retraverse
    {
        todo.clear();
        enqueuePostorder(root);
    }
    
    public Object next()
    // pre: hasNext();
    // post: returns current value, increments iterator
    {
        BinaryTree current = todo.poll();
        return current.value();
    }

    protected void enqueuePostorder(BinaryTree current)
// pre: current is non-null
// post: enqueue all values found in tree rooted at current
// in postorder
    {
        if (current.isEmpty()) return;
        enqueuePostorder(current.left());
        enqueuePostorder(current.right());
        todo.offer(current);
    }
    
    public boolean hasMoreElements() {
        return (todo.isEmpty());
    }

    @Override
    public boolean hasNext() {
        return (!todo.isEmpty());
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
