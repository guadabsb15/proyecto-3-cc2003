/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.lang.Double;

/**
 * Evaluates an infix expression
 * @author asus
 */
public class Evaluator {

    private Tokenizer tokenizer;
    
     /**
     * Symbols used as operators
     */
    private final String OPERATORS = "+-*/";
   
    /**
     * Symbols used to group expresions
     */
    private final String GROUPERS = "()";
   
    /**
     * Tokens of the expression to be evaluated
     */
    private ArrayList<String> tokens;
   
    /**
     * Operator stack
     */
    private Stack<String> stack;

    /**
     * RPN evaluation stack
     */
    private Stack<String> evalStack;
    
    /**
     * Output queue
     */
    private LinkedList<String> queue;
    
    /**
     * Class constructor
     */
    public Evaluator() {
        tokenizer = new Tokenizer();
        stack = new Stack<String>();
        evalStack = new Stack<String>();
        queue = new LinkedList<String>();
    }
    
    /**
     * Evaluates the given expression
     * @param expression
     * @return 
     */
    public String evaluate(String expression) {
        
        try {
            // Separates the expression into tokens
            getTokens(expression);
            
            // Performs the shunting-yard algorithm to tranfsform infix notation to RPN
            shunt();
            
            // Evaluates the RPN output from the previous step
            solve();
        } catch (Exception e) {
            
        }
        
        return "";
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
            String actual = tokens.get(i);
            if (isNumber(actual)) queue.add(actual);
            else if (isFunction(actual)) stack.push(actual);
            else if (isSeparator(actual)) {
                
            }
            else if (isOperator(actual)) {           
                    while ( (!stack.isEmpty() && isOperator(stack.peek()))  && ( ( isLeftAssociative(actual) && ( getPrecedence(actual) <= getPrecedence(stack.peek())) ) || (getPrecedence(actual) < getPrecedence(stack.peek())) ) ) {
                        queue.add(stack.pop());
                    }   
                    stack.push(actual);
            }
            else if (isLeftParentheses(actual)) stack.push(actual);
            else if (isRightParentheses(actual)) {
                while (!isLeftParentheses(stack.peek())) {
                    queue.add(stack.pop());
                }
                stack.pop();
                if (!stack.isEmpty() && isFunction(stack.peek())) queue.add(stack.pop());
            }
        }
        
        while (!stack.isEmpty()) {
            if (isLeftParentheses(stack.peek()) || isRightParentheses(stack.peek())) throw new Exception("Paréntesis mal formados");
            queue.add(stack.pop());
        }
            
    }
    
    /**
     * Returns the precedence of an operator, as an integer. The lowest precedence
     * is one.
     * @param operator
     * @return 
     */
    private int getPrecedence(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        }
            return 2;
    }
    
    /**
     * Returns true if an operator is left-associative
     * @param operator
     * @return 
     */
    private boolean isLeftAssociative(String operator) {
        return true;
    }
    
    /**
     * Returns true if the operand is a number
     * @param s
     * @return 
     */
    private boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
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
    private boolean isFunction(String s) {
        return false;
    }
    
    /**
     * Returns true if the token is an argument separator
     * @param s
     * @return 
     */
    private boolean isSeparator(String s) {
        return false;
    }
    
    /**
     * Returns true if the token is an operator symbol
     * @param s
     * @return 
     */
    private boolean isOperator(String s) {
        return OPERATORS.contains(s);
    }
    
    /**
     * Returns true if the token is a left parenthesis
     * @param s
     * @return 
     */
    private boolean isLeftParentheses(String s) {
        return s.equals("(");
    }
    
    /**
     * Returns true if the token is a right parentheses
     * @param s
     * @return 
     */
    private boolean isRightParentheses(String s) {
        return s.equals(")");
    }

    /**
     * Evaluates the RPN expression
     */
    private void solve() {
        while (queue.size() > 0) {
            String actual = queue.poll();
            if (isNumber(actual)) evalStack.push(actual);
            else if (isOperator(actual)) {
                if (actual.equals("+")) {
                    Double op1 = getValue(evalStack.pop());
                    Double op2 = getValue(evalStack.pop());
                    evalStack.push(Double.toString(op1+op2));
                    
                } else if (actual.equals("-")) {
                    Double op1 = getValue(evalStack.pop());
                    Double op2 = getValue(evalStack.pop());
                    evalStack.push(Double.toString(op2-op1));
                    
                } else if (actual.equals("*")) {
                    Double op1 = getValue(evalStack.pop());
                    Double op2 = getValue(evalStack.pop());
                    evalStack.push(Double.toString(op1*op2));
                    
                } else if (actual.equals("/")) {
                    Double op1 = getValue(evalStack.pop());
                    Double op2 = getValue(evalStack.pop());
                    evalStack.push(Double.toString(op1/op2));
                    
                }
            }
        }
        System.out.println(evalStack.pop());
    }
    
    /**
     * Returns the value of a token
     * @param s
     * @return 
     */
    private Double getValue(String s) {
        if (isNumber(s)) {
            return Double.parseDouble(s);
        }
        return null;
    }
}
