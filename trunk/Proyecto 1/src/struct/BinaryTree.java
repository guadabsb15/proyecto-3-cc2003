/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package struct;

public class BinaryTree<E> {
    protected E val; // value associated with node
    protected BinaryTree<E> parent; // parent of node
    protected BinaryTree<E> left, right; // children of node

    public BinaryTree()
// post: constructor that generates an empty node
    {
        val = null;
        parent = null; left = right = this;
    }
    
    public BinaryTree(E value)
// post: returns a tree referencing value and two empty subtrees
    {
    //Assert.pre(value != null, "Tree values must be non-null.");
        val = value;
        right = left = new BinaryTree<E>();
        setLeft(left);
        setRight(right);
    }
    
    public BinaryTree(E value, BinaryTree<E> left, BinaryTree<E> right)
// post: returns a tree referencing value and two subtrees
    {
        //Assert.pre(value != null, "Tree values must be non-null.");

        val = value;
        if (left == null) { left = new BinaryTree<E>(); }
        setLeft(left);
        if (right == null) { right = new BinaryTree<E>(); }
        setRight(right);
    }
    
    public void setLeft(BinaryTree<E> newLeft)
// post: sets left subtree to newLeft
// re-parents newLeft if not null
    {
        //if (isEmpty()) return;
        if (left != null && left.parent() == this) left.setParent(null);
        left = newLeft;
        left.setParent(this);
    }
    
     public void setRight(BinaryTree<E> newRight)
// post: sets left subtree to newLeft
// re-parents newLeft if not null
    {
        //if (isEmpty()) return;
        if (right != null && right.parent() == this) right.setParent(null);
        right = newRight;
        right.setParent(this);
    }
    
    protected void setParent(BinaryTree<E> newParent)
    // post: re-parents this node to parent reference, or null
    {
        if (!isEmpty()) {
        parent = newParent;
        }
    }

    public BinaryTree<E> left() {
        return left;
    }
    
    public BinaryTree<E> right() {
        return right;
    }
    
    public E value() {
        return val;
    }
    
    public BinaryTree<E> parent() {
        return parent;
    }
    
    public void setValue(E value) {
        val = value;
    }

    public boolean isEmpty() {
        return ((val == null) && (parent == null) && (left == this) && (right == this));
    }
    
    @Override
    public String toString() {
        return val.toString();
    }
    
}
