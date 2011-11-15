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
     * Separa la primera expresión encontrada del resto
     * @param exp Cadena a analizar
     * @return
     * @throws Exception Si los paréntesis están mal formados
     */
    public static String[] separarExpresion(String exp) throws Exception {
        throw new UnsupportedOperationException("Not yet implemented"); 
    } 
    
    public static ArrayList separarParametros(String exp) throws Exception {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public String[] siguiente(String exp) {
        return exp.split("[() ]");
    } 
   
}
