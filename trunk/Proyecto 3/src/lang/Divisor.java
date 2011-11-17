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
public class Divisor implements Operador {
     private String[] argumentos;
    
    private Stack<Double> stack;
    
    public Divisor() {
        stack = new Stack<Double>();
    }
    
    /**
     * Calcula la división de los parámetros ingresados
     * @param args
     * @return
     * @throws Exception 
     */
    @Override
    public Object calcular(ArrayList<String> args) throws Exception {
        double resultado = 0;
        try {
            for (int i = args.size()-1; i >= 0; i--) {
                stack.push(Double.parseDouble(args.get(i)));
            }  
            while (stack.size() != 1) {
                double numerador = stack.pop();
                double denominador = stack.pop();
                if (denominador != 0) {
                    stack.push(numerador/denominador);
                } else {
                    throw new Exception("Error: División entre cero");
                }
            }
            return stack.pop();
        } catch (Exception e) {
            if (e.getMessage().equals("Error: División entre cero")) throw e;
            throw new Exception("Parámetro no es un número");
        }
        
    }
}
