package com.example.gestiondepedidos.pedido;

import com.example.gestiondepedidos.domain.HibernateUtil;
import com.example.gestiondepedidos.domain.DAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz PedidoDAO para acceder y gestionar datos de pedidos en una base de datos.
 */
public class PedidoDAOImp implements DAO<Pedido> {
    @Override
    public ArrayList<Pedido> getAll() {
        var salida = new ArrayList<Pedido>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<Pedido> query = sesion.createQuery("from Pedido", Pedido.class);
            salida = (ArrayList<Pedido>) query.getResultList();
        }
        return salida;
    }


    @Override
    public Pedido get(Integer id) {
        var salida = new Pedido();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            salida = session.get(Pedido.class, id);
        }
        return salida;
    }
    @Override
    public Pedido save(Pedido data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                //Comienza la transacción.
                transaction = session.beginTransaction();

                //Guarda el nuevo ítem en la Base de Datos.
                session.save(data);

                //Commit de la transacción.
                transaction.commit();
            } catch (Exception e) {
                //Maneja cualquier excepción que pueda ocurrir durante la transacción.
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return data;
        }
    }

    @Override
    public void update(Pedido data) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            //Comienza la transacción.
            transaction = session.beginTransaction();

            //Actualiza el pedido en la Base de Datos.
            session.update(data);

            //Commit de la transacción.
            transaction.commit();
        } catch (Exception e) {
            //Maneja cualquier excepción que pueda ocurrir durante la transacción.
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public void delete(Pedido data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Pedido pedido = session.get(Pedido.class, data.getId());
            session.remove(pedido);
        });
    }
  /*  public List<String> getCodigo(){
        ArrayList<String> results = new ArrayList<>(0);

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<String> q = s.createQuery("select distinct(p.codigo) from Pedido p", String.class);
            results = (ArrayList<String>) q.getResultList();
        }
        return results;
    }

*/

}