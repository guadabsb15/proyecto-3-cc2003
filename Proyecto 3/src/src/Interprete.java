/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import lang.Operador;
import lang.Sumador;

/**
 *
 * @author asus
 */
public class Interprete {
    
    private HashMap<String, Operador> primitivos;
    
    private Ambiente global;
       
    public Interprete() {
        primitivos = new HashMap<String, Operador>();
        primitivos.put("+", new Sumador());
        
        global = new Ambiente();
        
    }
    
    public static void main(String[] args) {
    }
    
    public String eval(String exp, Ambiente amb) throws Exception {
        //String[] partes = Analizador.separarExpresion(exp);
        if (esAutoevaluativa()) return exp;
        if (esVariable()) return buscarValor();
        if (esDefinicion()) return definir();
        if (esAplicacion()) {
        }
        return null;
    }
    
    public String aplicar(Operador op, String args) throws Exception {
        return null;
    }

    private boolean esOperacion(String str) {
        if (primitivos.containsKey(str)) return true;
        return false;
    }

    private boolean esAutoevaluativa() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private boolean esVariable() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private String buscarValor() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private boolean esDefinicion() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private String definir() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private boolean esAplicacion() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private String aplicar(String string, String string0) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void listaParametros() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    
}
