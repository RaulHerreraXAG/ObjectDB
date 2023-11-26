package com.example.gestiondepedidos.productos;

import com.example.gestiondepedidos.domain.HibernateUtil;
import com.example.gestiondepedidos.domain.DAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Implementación de la interfaz ProductoDAO para acceder y gestionar datos de productos en una base de datos.
 */
public class ProductoDAOImp implements DAO<Producto> {

    @Override
    public ArrayList<Producto> getAll() {
        var salida = new ArrayList<Producto>(0);
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            Query<Producto> query = sesion.createQuery("from Producto", Producto.class);
            salida = (ArrayList<Producto>) query.getResultList();
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
    public void update(Producto data) {
        // Implementación para actualizar los datos del producto en la base de datos.
    }

    @Override
    public void delete(Producto data) {
        // Implementación para eliminar los datos del producto de la base de datos.
    }
}

