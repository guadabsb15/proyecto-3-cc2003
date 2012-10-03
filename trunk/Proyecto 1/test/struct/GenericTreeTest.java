/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package struct;

import java.util.ArrayList;
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
public class GenericTreeTest {
    
    public GenericTreeTest() {
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
     * Test of setParent method, of class GenericTree.
     */
    @Test
    public void testTree() {
        
        GenericTree<String> p = new GenericTree();
        p.setValue("id");
        ArrayList<GenericTree<String>> children = new ArrayList<GenericTree<String>>();
        children.add(new GenericTree<String>("("));
        children.add(new GenericTree<String>("valor"));
        children.add(new GenericTree<String>(")"));
        p.setChildren(children);
        System.out.println("Tree");
    }

   
}
