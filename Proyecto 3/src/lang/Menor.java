/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lang;

import java.util.ArrayList;

/**
 *
 * @author BarriosSazo
 */
public class Menor implements Operador{
     private String[] argumentos;
    
    public Menor() {
        
    }
    
    /**
     * Calcula la suma de los parámetros ingresados
     * @param args
     * @return
     * @throws Exception 
     */
    
    @Override
    public Object calcular(ArrayList<String> args) throws Exception {
        boolean resultado = false;
        try {
             if(Double.parseDouble(args.get(0))< Double.parseDouble(args.get(1))){
                 resultado= true;
             }
             
            return resultado;
        } catch (Exception e) {
            throw new Exception("Parámetro no es un número");
        }
        
    }
    
    
}
