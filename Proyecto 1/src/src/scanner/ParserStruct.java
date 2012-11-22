/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.scanner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author asus
 */
public class ParserStruct {
    
    private int action;
    
    public static final int KLEENE = 1;
    
    public static final int OR = 2;
    
    public static final int OPTIONAL = 3;
    
    private String attribute;
    
    private String semAction;
    
    private String id;
    
    private String tokenName;
    
    private List<ParserStruct> subStruct;
    
    public ParserStruct() {
     
    }
    
    public void semAction(String sem) {
        semAction = sem;
    }
    
    public void attribute(String att) {
        attribute = att;
    }
    
    public void action(int act) {
        action = act;
    }
    
    public void id(String identifier) {
        id = identifier;
    }
    
    public void tokenName(String n) {
        tokenName = n;
    }
    
    public void subStruct(List l) {
        subStruct = l;
    }
    
    public String semAction() {
        return semAction ;
    }
    
    public String  attribute() {
        return attribute;
    }
    
    public int  action() {
        return action;
    }
    
    public String  id() {
        return id;
    }
    
    public String tokenName() {
        return tokenName;
    }
    
    public List subStruct() {
        return subStruct;
    }
    
}
