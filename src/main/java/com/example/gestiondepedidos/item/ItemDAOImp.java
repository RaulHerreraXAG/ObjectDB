package com.example.gestiondepedidos.item;

import com.example.gestiondepedidos.domain.DAO;
import com.example.gestiondepedidos.domain.ObjectDBUtil;
import com.example.gestiondepedidos.pedido.Pedido;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz ItemDAO para acceder y gestionar datos de ítems en una base de datos.
 */
public class ItemDAOImp implements DAO<Item> {

    @Override
    public ArrayList<Item> getAll() {
        var salida = new ArrayList<Item>(0);
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Item> query = entityManager.createQuery("select i from Item i", Item.class);
            salida = (ArrayList<Item>) query.getResultList();
        } finally {
            entityManager.close();
        }
        return salida;
    }

    @Override
    public Item get(Integer id) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Item i=null;
        try{
            i = em.find(Item.class,id);
        } finally {
            em.close();
        }
        return i;
    }



    public List<Item> getItemsByPedido(Long pedidoId) {
        List<Item> items = new ArrayList<>();
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            TypedQuery<Item> query = entityManager.createQuery("SELECT i FROM Item i WHERE i.codigo.id = :codigo", Item.class);
            query.setParameter("codigo", pedidoId);
            items = query.getResultList();
        } finally {
            entityManager.close();
        }

        return items;
    }

    @Override
    public Item save(Item data) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(data);
            em.flush();
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return data;
    }

    @Override
    public Pedido update(Item data) {
        // Actualiza la información del ítem en la base de datos, puede implementarse si se necesita.
        return null;
    }

    @Override
    public Boolean delete(Item data) {
        Boolean salida= false;
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Item i = em.find(Item.class,data.getId());
            salida = (i!=null);
            if(salida) {
                em.remove(i);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return salida;
    }
}
