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
public interface Operador {
    
    public Object calcular(ArrayList<String> args) throws Exception;
    
}
