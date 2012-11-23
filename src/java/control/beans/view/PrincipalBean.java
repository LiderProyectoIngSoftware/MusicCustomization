package control.beans.view;

import control.beans.session.SessionBean;
import customization.Customizador;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.context.FacesContext;
import model.dao.Dao;
import model.pojos.Cancion;
import model.pojos.Historia;
import model.pojos.Usuario;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.Espejo;

/**
 *
 * @author alberto
 */
public class PrincipalBean {

    private List<Cancion> canciones;
    private int idCancionElegida;
    private Dao dao;
    private List<Cancion> rock;
    private List<Cancion> pop;
    private List<Cancion> reggae;
    private List<Cancion> reggaeton;
    private List<Cancion> merengue;
    private List<Cancion> salsa;
    private List<Cancion> cumbia;
    private List<Cancion> clasica;
    private List<Cancion> jazz;
    private List<Cancion> metal;
    private List<Cancion> samba;
    private List<Cancion> disco;
    private Usuario usuario;
    private List<Cancion> cancionesHistorial;

    /**
     * Creates a new instance of PrincipalBean
     */
    public PrincipalBean() {
        dao = new Dao();
        usuario = obtenerUsuario();
        idCancionElegida = -1;
        llenarCanciones();
        Collections.sort(canciones);
        cancionesRecomendadas();
        llenarHistorial();
    }

    private void llenarCanciones() {
        canciones = dao.executeSelect(Cancion.class, null);
    }

    private void cancionesRecomendadas() {
        String[] generos = new String[]{"Rock", "Pop", "Reggae", "Reggaeton", "Merengue", "Salsa", "Cumbia", "Clasica",
            "Jazz", "Metal", "Samba", "Disco"};
        dao.refreshObject(usuario);
        Customizador custom = new Customizador(usuario);
        Map<String, List<Cancion>> preferencias = custom.obtenerPreferencias();
        for (String gen : generos) {
            if (preferencias != null) {

                List<Cancion> cancionesPref = preferencias.get(gen);
                if (cancionesPref == null) {
                    cancionesPref = new LinkedList<Cancion>();
                }
                System.out.println(gen);
                System.out.println(cancionesPref);
                Method metodoSet = Espejo.getMethod(PrincipalBean.class, "set" + gen);
                Espejo.invocaSet(metodoSet, this, cancionesPref);
            }
        }
    }

    private Usuario obtenerUsuario() {
        SessionBean sb = (SessionBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionBean");
        return sb.getUsuario();
    }

    /**
     * @return the canciones
     */
    public List<Cancion> getCanciones() {
        return canciones;
    }

    /**
     * @param canciones the canciones to set
     */
    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    /**
     * @return the dao
     */
    public Dao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(Dao dao) {
        this.dao = dao;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String escucharCancion() {
        Cancion can = null;
        try {
            can = (Cancion) dao.executeSelectOneCriterion(Cancion.class, Restrictions.eq("idCancion", idCancionElegida)).get(0);


            if (can != null) {

                Historia hist = new Historia(usuario, can, Calendar.getInstance().getTime());
                Transaction tran = dao.beginTransaction();
                dao.save(hist);
                dao.endTransaction(tran, true);
                cancionesRecomendadas();
                llenarHistorial();
                return "principal";
            }
        } catch (Exception e) {
        } finally {
            return null;
        }
    }

    /**
     * @return the rock
     */
    public List<Cancion> getRock() {
        return rock;
    }

    /**
     * @param rock the rock to set
     */
    public void setRock(List<Cancion> rock) {
        this.rock = rock;
    }

    /**
     * @return the pop
     */
    public List<Cancion> getPop() {
        return pop;
    }

    /**
     * @param pop the pop to set
     */
    public void setPop(List<Cancion> pop) {
        this.pop = pop;
    }

    /**
     * @return the reggae
     */
    public List<Cancion> getReggae() {
        return reggae;
    }

    /**
     * @param reggae the reggae to set
     */
    public void setReggae(List<Cancion> reggae) {
        this.reggae = reggae;
    }

    /**
     * @return the reggaeton
     */
    public List<Cancion> getReggaeton() {
        return reggaeton;
    }

    /**
     * @param reggaeton the reggaeton to set
     */
    public void setReggaeton(List<Cancion> reggaeton) {
        this.reggaeton = reggaeton;
    }

    /**
     * @return the merengue
     */
    public List<Cancion> getMerengue() {
        return merengue;
    }

    /**
     * @param merengue the merengue to set
     */
    public void setMerengue(List<Cancion> merengue) {
        this.merengue = merengue;
    }

    /**
     * @return the salsa
     */
    public List<Cancion> getSalsa() {
        return salsa;
    }

    /**
     * @param salsa the salsa to set
     */
    public void setSalsa(List<Cancion> salsa) {
        this.salsa = salsa;
    }

    /**
     * @return the cumbia
     */
    public List<Cancion> getCumbia() {
        return cumbia;
    }

    /**
     * @param cumbia the cumbia to set
     */
    public void setCumbia(List<Cancion> cumbia) {
        this.cumbia = cumbia;
    }

    /**
     * @return the clasica
     */
    public List<Cancion> getClasica() {
        return clasica;
    }

    /**
     * @param clasica the clasica to set
     */
    public void setClasica(List<Cancion> clasica) {
        this.clasica = clasica;
    }

    /**
     * @return the jazz
     */
    public List<Cancion> getJazz() {
        return jazz;
    }

    /**
     * @param jazz the jazz to set
     */
    public void setJazz(List<Cancion> jazz) {
        this.jazz = jazz;
    }

    /**
     * @return the metal
     */
    public List<Cancion> getMetal() {
        return metal;
    }

    /**
     * @param metal the metal to set
     */
    public void setMetal(List<Cancion> metal) {
        this.metal = metal;
    }

    /**
     * @return the samba
     */
    public List<Cancion> getSamba() {
        return samba;
    }

    /**
     * @param samba the samba to set
     */
    public void setSamba(List<Cancion> samba) {
        this.samba = samba;
    }

    /**
     * @return the disco
     */
    public List<Cancion> getDisco() {
        return disco;
    }

    /**
     * @param disco the disco to set
     */
    public void setDisco(List<Cancion> disco) {
        this.disco = disco;
    }

    /**
     * @return the cancionesHistorial
     */
    public List<Cancion> getCancionesHistorial() {
        return cancionesHistorial;
    }

    /**
     * @param cancionesHistorial the cancionesHistorial to set
     */
    public void setCancionesHistorial(List<Cancion> cancionesHistorial) {
        this.cancionesHistorial = cancionesHistorial;
    }

    private void llenarHistorial() {
        dao.refreshObject(usuario);
        cancionesHistorial = new LinkedList<Cancion>();
        Set<Historia> historiasSet = usuario.getHistorias();
        List<Historia> historias = new LinkedList<Historia>(historiasSet);
        Collections.sort(historias);
        for (Historia hist : historias) {
            cancionesHistorial.add(hist.getCancion());
        }
    }

    /**
     * @return the idCancionElegida
     */
    public int getIdCancionElegida() {
        return idCancionElegida;
    }

    /**
     * @param idCancionElegida the idCancionElegida to set
     */
    public void setIdCancionElegida(int idCancionElegida) {
        this.idCancionElegida = idCancionElegida;
    }
}
