/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lang;

import java.util.ArrayList;

/**
 * Interfaz para la definición de los operadores polimórficos
 * @author asus
 */
public interface Operador {
    
    /**
     * Define el método que un operador debe implementar para realizar su función a la hora de evaluar una expresión
     * @param args Argumentos que toma la operación
     * @return
     * @throws Exception 
     */
    public Object calcular(ArrayList<String> args) throws Exception;
    
}
