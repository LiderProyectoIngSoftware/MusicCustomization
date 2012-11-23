
package customization;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.pojos.Cancion;
import model.pojos.GeneroCancion;
import model.pojos.GeneroUsuario;
import util.Espejo;

/**
 *
 * @author alberto
 */
public class ObjetoOrdenado {
    
    private Object objeto;
    private List<Vector2D> listaGeneros;
    

    public ObjetoOrdenado(GeneroCancion genCan) {
        this.objeto=genCan.getCancion();
        generarListaGeneros(genCan);
        Collections.sort(listaGeneros);
    }
    
    public ObjetoOrdenado(GeneroUsuario genUsu){
        this.objeto=genUsu.getUsuario();
        generarListaGeneros(genUsu);
        Collections.sort(listaGeneros);
    }

    private void generarListaGeneros(Object genCan) {
        listaGeneros=new LinkedList<Vector2D>();
        List<Method> getters = Espejo.obtenerGetters(genCan.getClass());
        for(Method getter:getters){
            if(getter.getReturnType().equals(Double.class)){
                String nombreGenero=getter.getName().substring(3, getter.getName().length());
                Vector2D vec=new Vector2D(nombreGenero, (Double)Espejo.invocaGet(genCan, getter));
                listaGeneros.add(vec);
            }
        }
    }
    
    public String toString(){
        return objeto.toString()+listaGeneros.toString();
    }
    
    public int obtenerPosicionGenero(Object nombreGenero){
        int indice=0;
        for(Vector2D vec:listaGeneros){
            if(vec.getX().equals(nombreGenero)){
                return indice; 
            }
            indice++;
        }
        return -1;
    }

    /**
     * @return the objeto
     */
    public Object getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the listaGeneros
     */
    public List<Vector2D> getListaGeneros() {
        return listaGeneros;
    }

    /**
     * @param listaGeneros the listaGeneros to set
     */
    public void setListaGeneros(List<Vector2D> listaGeneros) {
        this.listaGeneros = listaGeneros;
    }

    public Vector2D obtenerVector2D(Object llave) {
        for(Vector2D vec:listaGeneros){
            if(vec.getX().equals(llave)){
                return vec;
            }
        }
        return null;
    }

}
