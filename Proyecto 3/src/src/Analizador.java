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
    
    /**
     * Valida una expresión en base al número de paréntesis abiertos y cerrados
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
    
    /**
     * Separa los parámetros de una expresión, en tokens más simples que pueden ser valores u otras operaciones
     * @param exp
     * @return ArrayList con los tokens separados
     * @throws Exception 
     */
    public static ArrayList separarParametros(String exp) throws Exception {
        String parametro = "";
        ArrayList parametros = new ArrayList();
        for (int i = 0; i < exp.length(); i++) {
            int inicio = i;
            if (exp.charAt(i) == '(') {
                int abiertos = 1;
                int cerrados = 0;
                i++;
                while (cerrados != abiertos && i < exp.length()) {
                    if (exp.charAt(i) == '(') abiertos++;
                    if (exp.charAt(i) == ')') cerrados++;
                    i++;
                }
                parametro = exp.substring(inicio, i);
                parametros.add(parametro);
            } else if (exp.charAt(i) != ' ') {
                i++;
                if (i < exp.length() && exp.charAt(i) == ' ') {
                    parametro = String.valueOf(exp.charAt(i-1));
                    parametros.add(parametro);
                } else {
                    while (i < exp.length() && exp.charAt(i) != ' ') {
                        i++;
                    }
                    parametro = exp.substring(inicio, i); 
                    parametros.add(parametro);
                }
            }
        }
        
        return parametros;
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
        
        int i = 0;
        while (i < exp.length() && exp.charAt(i) == '(') {
            i++;
        }
        primero = String.valueOf(exp.charAt(i));
        segundo = exp.substring(i+1, exp.length()-1);

        String[] resultado = {primero.trim(), segundo.trim()};
        return resultado;
    }
    
    /**
     * Quita los paréntesis externos de una expresión
     * @param exp
     * @return 
     */
    public static String strip(String exp) {
        return exp.substring(1, exp.length()-1);
    }
   
}
