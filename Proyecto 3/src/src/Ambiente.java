/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.HashMap;

/**
 *
 * @author asus
 */
public class Ambiente {
    
    private HashMap<String, String> vars;
    
    private Ambiente padre;
    
    public Ambiente(Ambiente p) {
        vars = new HashMap<String, String>();
        padre = p;
    }
    
    public Ambiente() {
        this(null);
    }
    
    public void agregarVariable(String nombre, String val) {
        vars.put(nombre, val);
    }
    
    public String obtenerValorVariable(String nombre) throws Exception {
        if (vars.containsKey(nombre)) {
            return vars.get(nombre);
        }
        try {
            return padre.obtenerValorVariable(nombre);
        } catch (Exception e) {
            throw new Exception("La variable no existe");
        }
        
        
    }
    
}
