/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package customization;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.dao.Dao;
import model.pojos.Cancion;
import model.pojos.GeneroCancion;
import model.pojos.GeneroUsuario;
import model.pojos.Historia;
import model.pojos.Usuario;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.Espejo;

/**
 * Clase que se encarga de evaluar el historial de un usuario y cruzarlo con las
 * canciones de manera que se puedan obtener las preferencias del usuario de
 * acuerdo a su comportamiento
 *
 * @author alberto
 */
public class Customizador {

    /**
     * usuario que se va a evaluar
     */
    private Usuario usuario;

    private static final double radioPreferencia=0.3;
    
    /**
     * Constructor
     *
     * @param usuario
     */
    public Customizador(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * obtiene las preferencias del usuario , la llave del mapeo son los generos
     * y la lista de canciones corresponde a las canciones que tienen
     * preferencia en ese genero de acuerdo al historial del usuario
     *
     * @return el mapeo de las canciones
     */
    public Map<String, List<Cancion>> obtenerPreferencias() {
        Set<Historia> historial = usuario.getHistorias();
        List<GeneroCancion> vectoresCanciones = new LinkedList<GeneroCancion>();
        for (Historia hist : historial) {
            GeneroCancion generoCancion = hist.getCancion().getGeneroCancion();
            vectoresCanciones.add(generoCancion);
        }
        GeneroCancion vectoresSumados = sumarVectoresCanciones(vectoresCanciones);
        if (vectoresSumados == null) {
            return null;
        }
        GeneroUsuario genUsu = modificarVectorUsuario(vectoresSumados, vectoresCanciones.size());
        actualizarUsuario(genUsu);

        return cruzarCancionesUsuario(usuario.getGeneroUsuario());

    }

    /**
     * metodo de prueba
     *
     * @param args
     */
    public static void main(String[] args) {
        Dao dao = new Dao();
        Usuario usu = (Usuario) dao.executeSelectOneCriterion(Usuario.class, Restrictions.eq("idUsuario", 1)).get(0);
        Customizador customizador = new Customizador(usu);
        Map<String, List<Cancion>> obtenerPreferencias = customizador.obtenerPreferencias();
        ObjetoOrdenado obj=new ObjetoOrdenado(usu.getGeneroUsuario());
        System.out.println(obtenerPreferencias.get(obj.getListaGeneros().get(0).getX()));
    }

    /**
     * suma los vectores de las canciones que ha escuchado el usuario
     *
     * @param vectoresCanciones
     * @return
     */
    private GeneroCancion sumarVectoresCanciones(List<GeneroCancion> vectoresCanciones) {
        if (vectoresCanciones.isEmpty()) {
            return null;
        }
        if (vectoresCanciones.size() == 1) {
            return vectoresCanciones.get(0);
        }
        GeneroCancion suma = vectoresCanciones.get(0).sum(vectoresCanciones.get(1));
        for (int t = 2; t < vectoresCanciones.size(); t++) {
            suma = suma.sum(vectoresCanciones.get(t));
        }
        return suma;
    }

    /*
     * metodo que se encarga de modificar el vector de preferencias del usuario dependiendo de la suma 
     * del historial 
     */
    private GeneroUsuario modificarVectorUsuario(GeneroCancion vectoresSumados, int sizeHistorial) {
        GeneroUsuario vectorUsuario = usuario.getGeneroUsuario();
        List<Method> obtenerGetters = Espejo.obtenerGetters(GeneroCancion.class);
        for (Method getter : obtenerGetters) {
            if (getter.getReturnType().equals(Double.class)) {
                Method setter = Espejo.getMethod(GeneroUsuario.class, "set" + getter.getName().substring(3, getter.getName().length()));
                Espejo.invocaSet(setter, vectorUsuario, (Double) Espejo.invocaGet(vectoresSumados, getter) / (double) sizeHistorial);
            }
        }
        return vectorUsuario;
    }

    /**
     * metodo que actualiza el vector de preferencias del usuario en la base de
     * datos
     *
     * @param generoUsuario
     */
    private void actualizarUsuario(GeneroUsuario generoUsuario) {
        Dao dao = new Dao();
        Transaction trans = dao.beginTransaction();
        dao.update(generoUsuario);
        dao.endTransaction(trans, true);
        dao.refreshObject(usuario);
    }

    /**
     * metodo que se encarga de cruzar las canciones con el perfil del usuario
     *
     * @param generoUsuario
     * @return
     */
    private Map<String, List<Cancion>> cruzarCancionesUsuario(GeneroUsuario generoUsuario) {
        Map<String , List<Cancion>> cancionesFinales=new HashMap<String, List<Cancion>>();
        List<ObjetoOrdenado> cancionesOrdenadas=generarCancionesOrdenadas();
        ObjetoOrdenado usuarioOrdenado=new ObjetoOrdenado(generoUsuario);
        List<Vector2D> gustosUsuario = usuarioOrdenado.getListaGeneros();
        for(Vector2D vec:gustosUsuario){
            if((Double)vec.getY()!=0.0){
                
               cancionesFinales.put(vec.getX().toString(), obtenerSeleccionCanciones(vec.getX(),cancionesOrdenadas));
            }
        }
        return cancionesFinales;
    }

    /**
     * obtiene las canciones que estan mejor posicionadas de acuerdo al genero o llave dado
     * @param llave
     * @param cancionesOrdenadas
     * @return 
     */
    private List<Cancion> obtenerSeleccionCanciones(Object llave , List<ObjetoOrdenado> cancionesOrdenadas){
        List<Cancion> cancionesFinales=new LinkedList<Cancion>();
        for(ObjetoOrdenado cancion:cancionesOrdenadas){
            Vector2D vec = cancion.obtenerVector2D(llave);
            int pos = cancion.obtenerPosicionGenero(llave);
            if(pos<3 && ((Double)vec.getY())>0.0){
                cancionesFinales.add((Cancion)cancion.getObjeto());
            }
        }
        return cancionesFinales;
    }

    /**
     * genera la lista de todas las canciones ordenadamente de acuerdo al genero que mas se parecen
     * @return 
     */
    private List<ObjetoOrdenado> generarCancionesOrdenadas() {
        List<ObjetoOrdenado> cancionesOrdenadas=new LinkedList<ObjetoOrdenado>();
        Dao dao=new Dao();
        List<GeneroCancion> vectoresCanciones = dao.executeSelectOneCriterion(GeneroCancion.class, null);
        for(GeneroCancion genCan:vectoresCanciones){
            cancionesOrdenadas.add(new ObjetoOrdenado(genCan));
        }
        return cancionesOrdenadas;
    }

}
