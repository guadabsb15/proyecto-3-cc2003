/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import lang.Multiplicador;
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
        primitivos.put("*", new Multiplicador());
        
        global = new Ambiente();
        
    }
    
    public static void main(String[] args) {
    }
    
    public String eval(String exp, Ambiente amb) throws Exception {
        if (esAutoevaluativa(exp)) return exp;
        if (esAplicacion(exp)) {
            String[] partes = Analizador.separarAritmetica(exp);
            String operacion = partes[0];
            ArrayList<String> parametros = Analizador.separarParametros(partes[1]);
            for (int i = 0; i < parametros.size(); i++) {
                String actual = parametros.get(i);
                parametros.remove(i);
                String nuevo = eval(actual, amb);
                parametros.add(i, nuevo);
            }
            Operador op = primitivos.get(operacion);
            return Double.toString(op.calcular(parametros));
        }
        /*
        if (esVariable()) return buscarValor();
        if (esDefinicion()) return definir();
        */
        return null;
    }
    
    public String aplicar(Operador op, String args) throws Exception {
        return null;
    }

    private boolean esOperacion(String str) {
        if (primitivos.containsKey(str)) return true;
        return false;
    }

    private boolean esAutoevaluativa(String exp) {
        try {
            Double.parseDouble(exp);
            return true;
        } catch (Exception e) {
            return false;
        }
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

    private boolean esAplicacion(String exp) {
        int i = 0;
        while (i < exp.length() && exp.charAt(i) == '(') {
            i++;
        }
        if ((i+1) < exp.length() && (exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '*' || exp.charAt(i) == '/') && (exp.charAt(i+1) == ' ' || exp.charAt(i+1) == ')')) return true;
        return false;
    }

    private String aplicar(String string, String string0) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void listaParametros() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    
}
