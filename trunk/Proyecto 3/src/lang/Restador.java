/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lang;

import java.util.ArrayList;
import java.util.Stack;


/**
 *
 * @author asus
 */
public class Restador implements Operador {
    private String[] argumentos;
    
    private Stack<Double> stack;
    
    public Restador() {
        stack = new Stack<Double>();
    }
    
    @Override
    public Object calcular(ArrayList<String> args) throws Exception {
        double resultado = 0;
        try {
            for (int i = args.size()-1; i >= 0; i--) {
                stack.push(Double.parseDouble(args.get(i)));
            }  
            while (stack.size() != 1) {
                double termino = stack.pop()-stack.pop();
                stack.push(termino);
            }
            return stack.pop();
        } catch (Exception e) {
            throw new Exception("Parámetro no es un número");
        }
        
    }
}
