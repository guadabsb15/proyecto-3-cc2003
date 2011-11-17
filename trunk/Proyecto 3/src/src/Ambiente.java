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
    
    /**
     * Contiene los nombres y valores asociados de las variables del ambiente
     */
    private HashMap<String, String> vars;
    
    /**
     * Ambiente más global referenciado por el objeto
     */
    private Ambiente padre;
    
    /**
     * Constructor de la clase
     * @param p Ambiente padre
     * Post: Crea un objeto Ambiente con el padre ingresado como parámetro
     */
    public Ambiente(Ambiente p) {
        vars = new HashMap<String, String>();
        padre = p;
    }
    
    /**
     * Constructor de la clase
     * Post: Crea un objeto Ambiente sin padre
     */
    public Ambiente() {
        this(null);
    }
    
    /**
     * Devuelve verdadero si el ambiente contiene la variable ingresada como parámetro
     * @param nombre
     * @return true o false
     */
    public boolean contieneVariable(String nombre) {
        if (vars.containsKey(nombre)) {
            return true;
        }
        if (padre != null) return padre.contieneVariable(nombre);
        return false;
    }
    
    /**
     * Agrega la variable y su valor asociado al ambiente
     * @param nombre Nombre de la variable
     * @param val Valor asociado
     */
    public void agregarVariable(String nombre, String val) {
        vars.put(nombre, val);
    }
    
    /**
     * Devuelve el valor de la variable cuyo nombre se ingresó como parámetro
     * @param nombre
     * @return valor asociado a la variable
     * @throws Exception 
     */
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
