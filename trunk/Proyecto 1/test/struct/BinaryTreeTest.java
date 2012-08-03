/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package struct;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author asus
 */
public class BinaryTreeTest {
    
    public BinaryTreeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setLeft method, of class BinaryTree.
     */
    @Test
    public void testSet() {
        System.out.println("setLeft");
        BinaryTree<String> instance = new BinaryTree();
        BinaryTree<String> tree = new BinaryTree();
        BinaryTree<String> pee = new BinaryTree("Pee");
        tree.setValue("Left");
        instance.setLeft(tree);
        instance.setParent(pee);
        String result = instance.left().value();
        String r2 = instance.parent().value();
        System.out.println(result);
        System.out.println(r2);
        // TODO review the generated test code and remove the default call to fail.
    }

   
}
