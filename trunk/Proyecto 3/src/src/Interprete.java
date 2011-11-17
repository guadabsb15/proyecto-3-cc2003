/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.HashMap;
import lang.Divisor;
import lang.Multiplicador;
import lang.Operador;
import lang.Restador;
import lang.Sumador;

/**
 * Clase que representa al intérprete y contiene los métodos para dirigir la interpretación de una expresión
 * @author asus
 */
public class Interprete {
    
    /**
     * Contiene los operadores definidos en el intérprete
     */
    private HashMap<String, Operador> primitivos;
    
    /**
     * Ambiente global que contiene variables y bindings globales
     */
    private Ambiente global;
      
    /**
     * Constructor de la clase. 
     * Post: Se crea un nuevo objeto de intérprete, con las operaciones aritméticas definidas como funciones primitivas
     */
    public Interprete() {
        primitivos = new HashMap<String, Operador>();
        primitivos.put("+", new Sumador());
        primitivos.put("*", new Multiplicador());
        primitivos.put("-", new Restador());
        primitivos.put("/", new Divisor());
        
        global = new Ambiente();
    }
    
    /**
     * Método utilizado para pedir la evaluación de una expresión externamente
     * @param exp String de la expresión
     * @return String del resultado de la evaluación
     * @throws Exception 
     */
    public String evalGlobal(String exp) throws Exception {
        return eval(exp, global);
    }
    
    /**
     * Método que evalúa una expresión recursivamente, hasta obtener una expresión autoevaluativa
      * @param exp String de la expresión
     * @return String del resultado de la evaluación 
     * @param amb Ambiente del scope de las variables que se deben buscar
     * @return
     * @throws Exception 
     */
    public String eval(String exp, Ambiente amb) throws Exception {
        if (Analizador.validar(exp)) {
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
                    return Double.toString((Double) op.calcular(parametros));
                }    
            }
        
            if (esVariable(exp, amb)) return buscarValor(exp, amb);
        
            if (esDefinicion(exp)) return definir(exp, amb);
        }
        throw new Exception("Expresión mal formada");
        
    }
    
    
    public String aplicar(Operador op, String args) throws Exception {
        return null;
    }

    /**
     * Determina si una expresión es una operación aritmética
     * @param str Expresión
     * @return true si la expresión se identifica como una operación 
     */
    private boolean esOperacion(String str) {
        if (primitivos.containsKey(str)) return true;
        return false;
    }

    /**
     * Determina si una expresión es autoevaluativa, el caso base para el procedimiento recursivo eval
     * @param exp
     * @return true o false
     */
    private boolean esAutoevaluativa(String exp) {
        try {
            Double.parseDouble(exp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Determina si una expresión es una variable dentro de un scope dado
     * @param exp
     * @param a
     * @return true o false
     */
    private boolean esVariable(String exp, Ambiente a) {
         return a.contieneVariable(exp);
    }

    /**
     * Devuelve el valor de la variable cuyo nombre se provee como parámetro
     * @param exp
     * @param a
     * @return valor asociado a la variable
     * @throws Exception 
     */
    private String buscarValor(String exp, Ambiente a) throws Exception {
        return a.obtenerValorVariable(exp);  
    }

    /**
     * Analiza si la expresión es una definición para una variable
     * @param exp
     * @return true o false
     */
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

    /**
     * Indica si la expresión corresponde a una aplicación de una función
     * @param exp
     * @return true o false
     */
    private boolean esAplicacion(String exp) {
        return esAritmetica(exp);
    }

    private String aplicar(String string, String string0) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Indica si la expresión es una operación aritmética que se debe evaluar
     * @param exp
     * @return true o false
     */
    private boolean esAritmetica(String exp) {
        int i = 0;
        while (i < exp.length() && exp.charAt(i) == '(') {
            i++;
        }
        if ((i+1) < exp.length() && (exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '*' || exp.charAt(i) == '/') && (exp.charAt(i+1) == ' ' || exp.charAt(i+1) == ')')) return true;
        return false;
    }

    /**
     * Define la variable, con el nombre ingresado como parámetro, en el ambiente parámetro
     * @param exp
     * @param af
     * @return Valor asignado a la variable
     * @throws Exception 
     */
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

    /**
     * Indica si la expresión es una asignación de valor a una variable
     * @param exp
     * @return true o false
     * @throws Exception 
     */
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
