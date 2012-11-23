package model.dao;


import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

/**
 *Data Abstract Object-
 * Clase que se encarga de las operaciones con la base de datos
 * @author alberto
 */
public class Dao {
    /**-
     * hibernate session
     */
    private static Session session;
    
    /**
     * Constructor
     */
    public Dao(){
        session= session==null ? HibernateUtil.getSessionFactory().openSession() : session;
    }
    
    /**
     * Inicia una transaccion de operaciones en la base de datos
     * @return la transaccion
     */
    public Transaction beginTransaction(){
        return session.beginTransaction();
    }
    
    /**
     *  Finaliza una transaccion  
     * @param transaction transaccion que se desea finalizar
     * @param correct correct=true indica que la transaccion fue correcta por lo tanto se da commit , en caso contrario rollback
     */
    public void endTransaction(Transaction transaction , boolean correct){
        if(correct){
            transaction.commit();
            return;
        }
        transaction.rollback();
    }
    
    /**
     * 
     * Guarda cualquier objeto (pojo) en la base de datos
     * @param object objeto a guardar
     */
    public void save(Object object){
        session.save(object);
    }
    
    /**
     * Hace update sobre cualquier objeto (pojo)
     * @param object objeto a guardar
     */
    public void update(Object object){
        session.update(object);
    }
    
    /**
     * Borra el objeto (pojo) de la base de datos
     * @param object objeto a borrar
     */
    public void delete(Object object){
        session.delete(object);
    }
    
    /**
     * Nos da las tuplas de la base de datos de acuerdo a la tabla pedida y los criterios de busqueda
     * @param table tabla que se desea seleccionar
     * @param criterion criterio para la busqueda
     * @return lista de las tuplas en base de datos
     */
    public List executeSelect(Class table , List<Criterion> criterion){
        if(criterion==null){
            return session.createCriteria(table).list();
        }
        Criteria createCriteria = session.createCriteria(table);
        for(Criterion crit:criterion){
            createCriteria.add(crit);
        }
        return createCriteria.list();
    }
    
    
    public List executeSelectOneCriterion(Class table , Criterion criterion){
        if(criterion==null){
            return session.createCriteria(table).list();
        }
        
        Criteria createCriteria = session.createCriteria(table);
            createCriteria.add(criterion);
        
        return createCriteria.list();
    }
    
    /**
     * Refresca el valor del objeto , ya que Hibernate mantiene un valor viejo 
     * de la tupla hasta que se utiliza este metodo
     * @param objeto objeto a refrescar
     */
    public void refreshObject(Object objeto){
        session.refresh(objeto);
    }

    public static void main(String[] args) {
        Dao dao=new Dao();
    }
}
