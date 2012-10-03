/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scanner;

import java.util.ArrayList;
import struct.GenericTree;

/**
 *
 * @author asus
 */
public class CocoFileParser {
    
    private ArrayList<Token> tokens;
    
    private GenericTree<Token> parseTree;
    
    public CocoFileParser(ArrayList<Token> tks) {
        tokens = tks;
        parseTree = new GenericTree();
    }
    
   public GenericTree<Token> parse() {
       
       return parseTree;
   }
    
}
