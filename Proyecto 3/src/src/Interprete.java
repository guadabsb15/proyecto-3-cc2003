/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import lang.Divisor;
import lang.Multiplicador;
import lang.Operador;
import lang.Restador;
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
        primitivos.put("-", new Restador());
        primitivos.put("/", new Divisor());
        
        global = new Ambiente();
    }
    
    public static void main(String[] args) {
    }
    
    public String eval(String exp, Ambiente amb) throws Exception {
        if (esAutoevaluativa(exp)) return exp;
        
        if (esAplicacion(exp)) {
            if (esAritmetica(exp)) {
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
        }
        
        if (esVariable(exp, amb)) return buscarValor(exp, amb);
        
        if (esDefinicion(exp)) return definir(exp, amb);
        
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

    private boolean esVariable(String exp, Ambiente a) {
         return a.contieneVariable(exp);
    }

    private String buscarValor(String exp, Ambiente a) throws Exception {
        return a.obtenerValorVariable(exp);  
    }

    public boolean esDefinicion(String exp) {
        try {
            if (exp.charAt(0) == '(') {
                exp = Analizador.strip(exp);
                return esDefinicion(exp);
            }
            ArrayList args = Analizador.separarParametros(exp);
            if (args.get(0).equals("define")) return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private String definir() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private boolean esAplicacion(String exp) {
        return esAritmetica(exp);
        
    }

    private String aplicar(String string, String string0) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void listaParametros() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private boolean esAritmetica(String exp) {
        int i = 0;
        while (i < exp.length() && exp.charAt(i) == '(') {
            i++;
        }
        if ((i+1) < exp.length() && (exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '*' || exp.charAt(i) == '/') && (exp.charAt(i+1) == ' ' || exp.charAt(i+1) == ')')) return true;
        return false;
    }

    public String definir(String exp, Ambiente af) throws Exception {
        if (esAsignacion(exp)) {
            if (exp.charAt(0) == '(') {
                exp = Analizador.strip(exp);
                return definir(exp, af);
            }
            ArrayList<String> partes = Analizador.separarParametros(exp);
            String nombre = (String) partes.get(1);
            af.agregarVariable(nombre, eval(partes.get(2), af));
            return eval(partes.get(2), af);
        }
        return null;
    }

    public boolean esAsignacion(String exp) throws Exception {
        if (exp.charAt(0) == '(') {
            exp = Analizador.strip(exp);
            return esAsignacion(exp);
        }
        ArrayList partes = Analizador.separarParametros(exp);
        String nombre = (String) partes.get(1);
        if (!esAplicacion(nombre) && !esAutoevaluativa(nombre) && partes.size() == 3) {
            return true;
        } else {
            throw new Exception("Asignación mal formada");
        }    
    }

    
}
