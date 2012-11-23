package control.beans.session;

import java.util.LinkedList;
import java.util.List;
import model.dao.Dao;
import model.pojos.Usuario;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author alberto
 */
public class SessionBean {

    private Dao dao;
    private Usuario usuario;
    /**
     * Creates a new instance of SessionBean
     */
    public SessionBean() {
        dao=new Dao();
        this.usuario=new Usuario();
    }
    
    public String validarUsuario(){
        List<Criterion> criterios=new LinkedList<Criterion>();
        criterios.add(Restrictions.eq("usuario", usuario.getUsuario()));
        criterios.add(Restrictions.eq("password",usuario.getPassword()));
        List executeSelect = dao.executeSelect(Usuario.class, criterios);
        if(executeSelect.isEmpty()){
            return "index";
        }
        this.usuario=(Usuario) executeSelect.get(0);
        return "principal";
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
}
