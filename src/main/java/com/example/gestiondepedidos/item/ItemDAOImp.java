package com.example.gestiondepedidos.item;

import com.example.gestiondepedidos.domain.HibernateUtil;
import com.example.gestiondepedidos.domain.DAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Implementación de la interfaz ItemDAO para acceder y gestionar datos de ítems en una base de datos.
 */
public class ItemDAOImp implements DAO<Item> {

    @Override
    public ArrayList<Item> getAll() {
        var salida = new ArrayList<Item>(0);
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            Query<Item> query = sesion.createQuery("from Item", Item.class);
            salida = (ArrayList<Item>) query.getResultList();
        }
        return salida;
    }

    @Override
    public Item get(Integer id) {
        return null; // Devuelve un ítem por su ID, puede implementarse si se necesita.
    }

    @Override
    public Item save(Item data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                // Comienza la transacción.
                transaction = session.beginTransaction();

                // Guarda el nuevo ítem en la Base de Datos.
                session.save(data);

                // Commit de la transacción.
                transaction.commit();
            } catch (Exception e) {
                // Maneja cualquier excepción que pueda ocurrir durante la transacción.
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return data;
        }
    }

    @Override
    public void update(Item data) {
        // Actualiza la información del ítem en la base de datos, puede implementarse si se necesita.
    }

    @Override
    public void delete(Item data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Item item = session.get(Item.class, data.getId());
            session.remove(item);
        });
    }
}
