/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package struct;

import java.util.Stack;

/**
 *
 * @author asus
 */
public class BTPostOrderIterator<E>  {
    protected BinaryTree<E> root; // root of traversed subtree
    protected Stack<BinaryTree<E>> todo; // stack of nodes
    
    // whose descendants are currently being visited
    public void reset()
    // post: resets the iterator to retraverse
    {
    todo.clear();
        // stack is empty; push on nodes from root to
    // leftmost descendant
        BinaryTree<E> current = root;
        while (!current.isEmpty()) {
        todo.push(current);
        if (!current.left().isEmpty())
        current = current.left();
        else current = current.right();
        }
    }

    public E next()
// pre: hasNext();
// post: returns current value, increments iterator
    {
    BinaryTree<E> current = todo.pop();
    E result = current.value();
    if (!todo.isEmpty())
    {
        BinaryTree<E> parent = todo.pop();
        if (current == parent.left()) {
        current = parent.right();
        while (!current.isEmpty())
    {
    todo.push(current);
    if (!current.left().isEmpty())
    current = current.left();
    else current = current.right();
    }
    }
    }
    return result;
}
}
