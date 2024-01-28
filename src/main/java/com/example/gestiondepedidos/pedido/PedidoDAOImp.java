package com.example.gestiondepedidos.pedido;

import com.example.gestiondepedidos.domain.DAO;
import com.example.gestiondepedidos.domain.ObjectDBUtil;
import com.example.gestiondepedidos.item.Item;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz PedidoDAO para acceder y gestionar datos de pedidos en una base de datos.
 */
public class PedidoDAOImp implements DAO<Pedido> {
    @Override
    public ArrayList<Pedido> getAll() {
      return null;
    }

    @Override
    public Pedido get(Integer id) {
        EntityManager em =  ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Pedido p=null;
        try{
            p = em.find(Pedido.class,id);
        } finally {
            em.close();
        }
        return p;
    }

    @Override
    public Pedido save(Pedido data) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(data);
            em.flush();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return data;

    }

    @Override
    public Pedido update(Pedido data) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            data = em.merge(data);
            em.getTransaction().commit();

        }catch (Exception ex){

            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
        return data;
    }

    @Override
    public Boolean delete(Pedido data) {
        Boolean salida= false;
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Pedido p = em.find(Pedido.class,data.getId());
            salida = (p!=null);
            if(salida) {
                em.remove(p);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return salida;
    }

    public String getUltimoCodigo() {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        TypedQuery<String> query = em.createQuery("select max(p.codigo) from Pedido p", String.class);
        return query.getSingleResult();
    }

    public static double calcularTotalPedido(List<Item> items) {
        double totalPedido = 0.0;
        for (Item item : items) {
            if (item != null && item.getProducto() != null) {
                Double precioConEuro = item.getProducto().getPrecio();
                totalPedido += item.getCantidad() * precioConEuro;
            }
        }
        return totalPedido;
    }


}
