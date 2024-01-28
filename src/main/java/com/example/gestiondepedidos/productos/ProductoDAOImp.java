package com.example.gestiondepedidos.productos;

import com.example.gestiondepedidos.domain.DAO;
import com.example.gestiondepedidos.domain.ObjectDBUtil;
import com.example.gestiondepedidos.pedido.Pedido;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementación de la interfaz ProductoDAO para acceder y gestionar datos de productos en una base de datos.
 */
public class ProductoDAOImp implements DAO<Producto> {

    @Override
    public List<Producto> getAll() {
        List<Producto> salida;

        EntityManager em =  ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Producto> query = em.createQuery("select p from Producto p", Producto.class);
            salida = query.getResultList();
        } finally {
            em.close();
        }
        return salida;
    }

    @Override
    public Producto get(Integer id) {
        return null;
    }

    @Override
    public Producto save(Producto data) {
        return null;
    }

    @Override
    public Pedido update(Producto data) {
        // Implementación para actualizar los datos del producto en la base de datos.
        return null;
    }

    @Override
    public Boolean delete(Producto data) {
        // Implementación para eliminar los datos del producto de la base de datos.
        return null;
    }

    public void saveAll(List<Producto> productos) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            for(Producto p : productos){
                em.persist(p);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}

