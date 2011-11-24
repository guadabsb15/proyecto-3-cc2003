/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.HashMap;
import lang.Divisor;
import lang.Igual;
import lang.Mayor;
import lang.Menor;
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
     * Contiene los procedimientos definidos en el intérprete
     */
    private HashMap<String, ArrayList> procedimientos;
    
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
        primitivos.put(">", new Mayor());
        primitivos.put("<", new Menor()); 
        primitivos.put("=", new Igual()); 
        
        procedimientos = new HashMap<String, ArrayList>();
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
                } else if(esPredicado(exp)){
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
                    return Boolean.toString((Boolean) op.calcular(parametros));
                }else if(esCompuesta(exp)){
                    String[] partes = Analizador.separarProcedimiento(exp);
                    String funcion = partes[0];
                    ArrayList<String> parametros = Analizador.separarParametros(partes[1]);
                    for (int i = 0; i < parametros.size(); i++) {
                        String actual = parametros.get(i);
                        parametros.remove(i);
                        String nuevo = eval(actual, amb);
                        parametros.add(i, nuevo);
                         }
                    ArrayList op = procedimientos.get(funcion);
                    ArrayList parametrosEsperados= (ArrayList)op.get(0);
                    ArrayList instrucciones = (ArrayList)op.get(1);
                    for (int i= 0; i< instrucciones.size(); i++){
                        String actual = (String)instrucciones.get(i);
                        String actual1=actual.valueOf(actual);
                        for (int j=0; j< parametrosEsperados.size(); j++){
                            if (actual.indexOf(" "+(String)parametrosEsperados.get(j))!=-1){             
                                 actual1= actual.replaceAll(" "+(String)parametrosEsperados.get(j)," "+(String)parametros.get(j));
                            } 
                        }
                        instrucciones.remove(i);
                        String nuevo = eval(actual1, amb);
                        instrucciones.add(i, nuevo);
                    }
                    return instrucciones.get(0).toString();// MODIFICAR LO QUE RETORNA
                }    
            }
            
            if (esCondicional(exp)){
                if (esIf(exp)){
                    String[] partes = Analizador.separarProcedimiento(exp);
                    ArrayList partes2=Analizador.separarParametros(partes[1]);
                    String predicado = (String)partes2.get(0);
                    if (eval(predicado, amb).equals("true")){
                        return (eval ((String)partes2.get(1),amb));
                    }else{
                        if(partes2.size()> 2) return (eval ((String)partes2.get(2), amb));
                    } 
                   
                    return "  ";
                }else if(esCond(exp)){
                    return "soy un cond";
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
     * Determina si una expresión es una funcion o un procedimiento creado por el usuario
     * @param str Expresión
     * @return true si la expresión se identifica como una funcion  
     */
    private boolean esProcedimiento(String str) {
        if (procedimientos.containsKey(str)) return true;
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
    
      /**
     * Analiza si la expresión es una condicional
     * @param exp
     * @return true o false
     */
     public boolean esCondicional(String exp) {
   
       return (esIf(exp) || esCond(exp));
    }
    
      /**
     * Analiza si la expresión es un if
     * @param exp
     * @return true o false
     */
     public boolean esIf(String exp) {
        try {
            if (exp.charAt(0) == '(') {
                exp = Analizador.strip(exp);
                return esIf(exp);
            }
            ArrayList args = Analizador.separarParametros(exp);
            if (args.get(0).equals("if")) return true;
            
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Analiza si la expresión es un cond (switch)
     * @param exp
     * @return true o false
     */
     public boolean esCond(String exp) {
        try {
            if (exp.charAt(0) == '(') {
                exp = Analizador.strip(exp);
                return esCond(exp);
            }
            ArrayList args = Analizador.separarParametros(exp);
            if (args.get(0).equals("cond")) return true;
            
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
        boolean temporal=false;
        if (esAritmetica(exp)||esCompuesta(exp)||esPredicado(exp))
            temporal =true;
        return temporal;
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
    
    private boolean esPredicado(String exp){
        int i=0;
        while (i < exp.length() && exp.charAt(i) == '(') {
            i++;
        }
        if ((i+1) < exp.length() && (exp.charAt(i) == '<' || exp.charAt(i) == '>' || exp.charAt(i) == '=' ) && (exp.charAt(i+1) == ' ' || exp.charAt(i+1) == ')')) return true;
        return false;
    }
    
    /**
     * Indica si la expresion es una funcion creada por el usuario
     * @param exp
     * @return true o false
     */
    private boolean esCompuesta(String exp){
        
      
        int i = 0;
        while (i < exp.length() && exp.charAt(i) == '(') {
            i++;
        }
        
        String expresion = exp.substring(i, exp.length());
        
        String[] nombre= expresion.split(" ");
        if (esProcedimiento(nombre[0])) return true;
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
        
        if(esDefDeProcedimiento(exp)){
             if (exp.charAt(0) == '(') {
                exp = Analizador.strip(exp);
                return definir(exp, af);
            }
            //System.out.println("Identificada!");
            ArrayList<String> partes = Analizador.separarParametros(exp);
            String encabezado = (String) partes.get(1);
            
            encabezado= Analizador.strip(encabezado);
            ArrayList partesEncabezado = Analizador.separarParametros(encabezado);
            String nombre =(String) partesEncabezado.get(0); 
            
            ArrayList parametros = new ArrayList();
            ArrayList procedimiento = new ArrayList();
            ArrayList informacion= new ArrayList();
            
            parametros= Analizador.separarParametros(encabezado);
            parametros.remove(0);
            
            procedimiento=Analizador.separarParametros(exp);
            procedimiento.remove(0);
            procedimiento.remove(0);
            
            informacion.add(parametros);
            informacion.add(procedimiento);
            procedimientos.put(nombre, informacion); 
           
           // return eval("1", af);// COMPONER ESTO!!!!
            return nombre;
        }
        else if (esAsignacion(exp)) {
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

    /**
     * Indica si la expresión es una definicion de un procedimiento
     * @param exp
     * @return true o false
     * @throws Exception 
     */
    public boolean esDefDeProcedimiento(String exp) throws Exception{
        
        if (exp.charAt(0) == '(') {
            exp = Analizador.strip(exp);
            return esDefDeProcedimiento(exp);
        }
        
        ArrayList partes = Analizador.separarParametros(exp); 
        String encabezado = (String) partes.get(1);
        
        if (encabezado.charAt(0)=='('){
            encabezado= Analizador.strip(encabezado);
            ArrayList partesEncabezado = Analizador.separarParametros(encabezado);
            String nombre =(String) partesEncabezado.get(0); 
       
            if (!esAplicacion(nombre) && !esAutoevaluativa(nombre) && partes.size() >=2) {
               return true;
            }
            else return false;
        }
        else {
            return false;
        }    
    }
    
}
