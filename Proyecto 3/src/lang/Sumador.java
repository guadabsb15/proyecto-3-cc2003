/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lang;

import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Sumador implements Operador {
    
    private String[] argumentos;
    
    public Sumador() {
        
    }
    
     /**
     * Calcula la suma de los parámetros ingresados
     * @param args
     * @return
     * @throws Exception 
     */
    @Override
    public Object calcular(ArrayList<String> args) throws Exception {
        double resultado = 0;
        try {
            for (int i = 0; i < args.size(); i++) {
                resultado += Double.parseDouble(args.get(i));
            }  
            return resultado;
        } catch (Exception e) {
            throw new Exception("Parámetro no es un número");
        }
        
    }

}
