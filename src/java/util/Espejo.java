
package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.pojos.GeneroCancion;

/**
 * Clase con metodos estaticos para utilizar la reflexion sobre objetos
 * @author alberto
 */
public class Espejo {

    /**
     * metodo que nos da todos los getters de una clase
     * @param clase
     * @return 
     */
    public static List<Method> obtenerGetters(Class clase) {
        List<Method> answer=new LinkedList<Method>();
        Method[] methods = clase.getDeclaredMethods();
        for(Method metodo:methods){
            if(metodo.getName().contains("get")){
                answer.add(metodo);
            }
        }
        return answer;
    }

    /**
     * metodo que nos da un metodo sobre la clase con el nombre establecido
     * @param clase
     * @param string
     * @return 
     */
    public static Method getMethod(Class clase , String string) {
        Method[] declaredMethods = clase.getDeclaredMethods();
        for(Method m:declaredMethods){
            if(m.getName().equals(string)){
                return m;
            }
        }
        return null;
    }

    /**
     * metodo que invoca un metodo set sobre un objeto pasando los parametros necesarios
     * @param setter
     * @param obj
     * @param param 
     */
    public static void invocaSet(Method setter, Object obj, Object param) {
        try {
            setter.invoke(obj, param);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Espejo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Espejo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Espejo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * metodo que invoca un get sobre un objeto cualquiera
     * @param object
     * @param metodo
     * @return 
     */
    public static Object invocaGet( Object object,Method metodo ){
        try {
            return metodo.invoke(object,null);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Espejo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Espejo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Espejo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
