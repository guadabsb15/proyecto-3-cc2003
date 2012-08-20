/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import src.automatons.Symbol;
import struct.BinaryTree;
import struct.BTPostOrderIterator;

/**
 * Evaluates an infix expression
 * @author asus
 */
public class Regexer {

    private Tokenizer tokenizer;
    
    private Set<Symbol> symbols;
    
    public final static Symbol EMPTY_STR = (new Symbol("ε"));
    
     /**
     * Symbols used as operators
     */
    private final String OPERATORS;
   
    /**
     * Symbols used to group expresions
     */
    private final String GROUPERS;
    
    /**
     * Symbols 
     */
    private final String OPERANDS;
   
    /**
     * Tokens of the expression to be evaluated
     */
    private ArrayList<String> tokens;
   
    /**
     * Operator stack
     */
    private Stack<Symbol> stack;

    /**
     * RPN evaluation stack
     */
    private Stack evalStack;
    
    /**
     * Output queue
     */
    private Stack<BinaryTree> queue;
    
    private BinaryTree abstractSyntaxTree;
    
    public Regexer(String operators, String groupers, String operands) {
        OPERATORS = operators;
        GROUPERS = groupers;
        OPERANDS = operands;
        
        tokenizer = new Tokenizer();
        stack = new Stack<Symbol>();
        evalStack = new Stack<String>();
        queue = new Stack<BinaryTree>();
        abstractSyntaxTree = null;
        
        symbols = makeSymbolSet();
        
    }
    
    /**
     * Class constructor
     
    public Regexer(Set<Symbol> alphabet) {
        symbols = alphabet;
        tokenizer = new Tokenizer();
        stack = new Stack<Symbol>();
        evalStack = new Stack<String>();
        queue = new Stack<BinaryTree>();
        abstractSyntaxTree = null;
    }*/
   
    /**
     * Evaluates the given expression
     * @param expression
     * @return 
     */
    public BinaryTree<Symbol> evaluate(String expression) throws Exception {
        getTokens(expression);     
        shunt();   
        return abstractSyntaxTree;
    }
    
    private void getTokens(String expr) {
        tokens = tokenizer.returnTokens(expr, OPERATORS + GROUPERS);
    }

    /**
     * Performs the shunting-yard algorithm. Infix tokens are taken as input. 
     * The output queue holds the RPN output.
     * @throws Exception 
     */
    private void shunt() throws Exception {
        for (int i = 0; i < tokens.size(); i++) {
            Symbol actual = new Symbol(tokens.get(i));
            if (isOperand(actual)) {
                actual.setType(Symbol.OPERAND);
                symbols.add(actual);
                queue.push(new BinaryTree(actual));
            }
            //else if (isFunction(actual)) stack.push(actual);
            else if (isSeparator(actual)) {
                
            }
            else if (isOperator(actual)) { 
                
                    while ( (!stack.isEmpty() && isOperator(stack.peek()))  && ( ( isLeftAssociative(actual) && ( getPrecedence(actual) <= getPrecedence(stack.peek())) ) || (getPrecedence(actual) < getPrecedence(stack.peek())) ) ) {
                        makeTree();
                    }   
                    stack.push(actual);
            }
            else if (isLeftParentheses(actual)) stack.push(actual);
            else if (isRightParentheses(actual)) {
                while (!isLeftParentheses(stack.peek())) {
                    makeTree();
                }
                stack.pop();
                if (!stack.isEmpty() && isFunction(stack.peek())) queue.push(new BinaryTree(stack.pop()));
            }
        }
        
        
        
        while (!stack.isEmpty()) {
            if (isLeftParentheses(stack.peek()) || isRightParentheses(stack.peek())) throw new Exception("Paréntesis mal formados");    
            makeTree();

        }
        
        abstractSyntaxTree = queue.pop();
              
            
    }
    
    private void makeTree() {
        if (getArity(stack.peek()) == 1) {
            if (stack.peek().equals("?")) {
                BinaryTree b = new BinaryTree(new Symbol("."), queue.pop(), new BinaryTree(EMPTY_STR));
                queue.push(b);
                stack.pop();
            } else if (stack.peek().equals("+")) {
                BinaryTree op = queue.pop();
                BinaryTree b = new BinaryTree(new Symbol("|"), op, new BinaryTree(new Symbol("*"), op, null));
                queue.push(b);
                stack.pop();
            } else if (stack.peek().equals("*")) {
                BinaryTree op = queue.pop();
                BinaryTree b = new BinaryTree(stack.pop(), op, null);
                queue.push(b);
            }
            return;             
       }   else if (getArity(stack.peek()) == 2)  {
           if ((stack.peek().equals("|"))) {
               BinaryTree op2 = queue.pop();
               BinaryTree op1 = queue.pop();
               BinaryTree b = new BinaryTree(stack.pop(), op1, op2);
               queue.push(b);
           } else {
               BinaryTree op2 = queue.pop();
               BinaryTree op1 = queue.pop();
               BinaryTree b = new BinaryTree(stack.pop(), op1, op2);
               queue.push(b);
           }
            
        }  
    }
    
    public int getArity(Object operator) {
        if (operator.toString().equals("?") || operator.toString().equals("+") || operator.toString().equals("*")) {
            return 1;
        } else {
            return 2;
        }
    }
    
   
    
    /**
     * Returns the precedence of an operator, as an integer. The lowest precedence
     * is one.
     * @param operator
     * @return 
     */
    public int getPrecedence(Object operator) {
        if (operator.toString().equals("|")) {
            return 1;
        } else if ( operator.toString().equals("+") ){
            return 2;
        } else {
            return 3;
        }
            
    }
    
    /**
     * Returns true if an operator is left-associative
     * @param operator
     * @return 
     */
    public boolean isLeftAssociative(Object operator) {
        return true;
    }
    
    /**
     * Returns true if the operand is a number
     * @param s
     * @return 
     */
    public boolean isNumber(Object s) {
        try {
            Double.parseDouble(s.toString());
            return true;
        } catch (Exception e) {
            return false;  
        }
    }
    
    /**
     * Returns true if the token is a function
     * @param s
     * @return 
     */
    public boolean isFunction(Object s) {
        return false;
    }
    
    /**
     * Returns true if the token is an argument separator
     * @param s
     * @return 
     */
    public boolean isSeparator(Object s) {
        return false;
    }
    
    /**
     * Returns true if the token is an operator symbol
     * @param s
     * @return 
     */
    public boolean isOperator(Object s) {
        return OPERATORS.contains(s.toString());
    }
    
    /**
     * Returns true if the token is a left parenthesis
     * @param s
     * @return 
     */
    public boolean isLeftParentheses(Object s) {
        return s.toString().equals("(");
    }
    
    /**
     * Returns true if the token is a right parentheses
     * @param s
     * @return 
     */
    public boolean isRightParentheses(Object s) {
        return s.toString().equals(")");
    }
    
    public boolean isOperand(Symbol s) {
        //return s.type() == Symbol.OPERAND;
        return !isOperator(s) && !isLeftParentheses(s) && !isRightParentheses(s);
        
    }

      
    
    /**
     * Returns the value of a token
     * @param s
     * @return 
     */
    public Double getValue(String s) {
        if (isNumber(s)) {
            return Double.parseDouble(s);
        }
        return null;
    }
    
    public BinaryTree returnAST() {
        return abstractSyntaxTree;
    }
    
    public Set symbols() {
        return symbols;
    }

    private Set<Symbol> makeSymbolSet() {
        Set set = new LinkedHashSet();
        
        ArrayList<String> ops = new ArrayList();
        ops = tokenizer.returnTokens(OPERATORS, OPERATORS);
        for (int i = 0; i < ops.size(); i++) {
            String current = ops.get(i);
            set.add(new Symbol(current, Symbol.OPERATOR));
        }
        
        ArrayList<String> ope = new ArrayList();
        ope = tokenizer.returnTokens(OPERANDS, OPERANDS);
        for (int i = 0; i < ope.size(); i++) {
            String current = ope.get(i);
            set.add(new Symbol(current, Symbol.OPERAND));
        }
        
        ArrayList<String> gro = new ArrayList();
        gro = tokenizer.returnTokens(GROUPERS, GROUPERS);
        for (int i = 0; i < gro.size(); i++) {
            String current = gro.get(i);
            set.add(new Symbol(current, Symbol.GROUPER));
        }
        
        return set;
    }
}
