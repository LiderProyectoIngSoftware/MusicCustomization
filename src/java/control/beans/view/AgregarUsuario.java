package control.beans.view;

import model.dao.Dao;
import model.pojos.GeneroUsuario;
import model.pojos.Usuario;
import org.hibernate.Transaction;

/**
 *
 * @author alberto
 */
public class AgregarUsuario {

    private Usuario usuario;
    private Dao dao;
    /**
     * Creates a new instance of AgregarUsuario
     */
    public AgregarUsuario() {
        dao=new Dao();
        usuario=new Usuario();
    }
    
    public String guardarUsuario(){
        Transaction beginTransaction = dao.beginTransaction();
        dao.save(usuario);
        GeneroUsuario genUsu=new GeneroUsuario(usuario.getIdUsuario(), usuario, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0, 0.0, 0.0, 0.0, 0.0);
        dao.save(genUsu);
        dao.endTransaction(beginTransaction, true);
        return "index";
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
