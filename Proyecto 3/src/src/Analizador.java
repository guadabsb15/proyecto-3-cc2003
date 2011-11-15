/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Analizador {
    
    public Analizador() {
    }
    
    /**
     * Valida una expresión
     * @param exp
     * @return
     * @throws Exception 
     */
    public static boolean validar(String exp) {
        boolean correcto = true;
        exp = exp.trim();
        int abiertos = 0;
        int cerrados = 0;
        
        if (exp.length() > 1) {
            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '(') abiertos++;
                if (exp.charAt(i) == ')') cerrados++;
                correcto = (abiertos == cerrados);
            }
        }
        return correcto;
    }    
    public static ArrayList separarParametros(String exp) throws Exception {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    /**
     * Separa las partes de una expresión aritmética
     * @param exp
     * @return ArrayList con las partes separadas
     */
    public static String[] separarAritmetica(String exp) throws Exception {
        exp = exp.trim();
        String primero = "";
        String segundo = "";
        
        if (exp.charAt(0) == '(') {
            primero = String.valueOf(exp.charAt(1));
            segundo = exp.substring(2, exp.length()-1);
        } else {
            primero = String.valueOf(exp.charAt(0)); 
            segundo = exp.substring(1, exp.length()-1);
        }
        String[] resultado = {primero.trim(), segundo.trim()};
        return resultado;
        
    }
    
    public String[] siguiente(String exp) {
        return exp.split("[() ]");
    } 
   
}
