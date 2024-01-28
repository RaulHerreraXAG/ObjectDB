package com.example.gestiondepedidos.usuario;

import com.example.gestiondepedidos.domain.DAO;
import com.example.gestiondepedidos.domain.ObjectDBUtil;
import com.example.gestiondepedidos.pedido.Pedido;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz UsuarioDAO para acceder y gestionar datos de usuarios en una base de datos.
 */
public class UsuarioDAOImp implements DAO<Usuario> {

    @Override
    public List<Usuario> getAll() {
        List<Usuario> salida;

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Usuario> query = em.createQuery("select u from Usuario u", Usuario.class);
            salida = query.getResultList();
        } finally {
            em.close();
        }
        return salida;
    }

    @Override
    public Usuario get(Integer id) {
        Usuario salida = null;
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            salida = em.find(Usuario.class, id);
        } finally {
            em.close();
        }
        return salida;
    }

    @Override
    public Usuario save(Usuario data) {
        return null;
    }

    @Override
    public Pedido update(Usuario data) {
        // Implementación para actualizar los datos del usuario en la base de datos.
        return null;
    }

    @Override
    public Boolean delete(Usuario data) {
        // Implementación para eliminar los datos del usuario de la base de datos.
        return null;
    }

    /**
     * Valida las credenciales de inicio de sesión del usuario.
     *
     * @param email  Email del usuario.
     * @param contrasenya Contraseña del usuario.
     * @return El objeto Usuario si las credenciales son válidas, de lo contrario, null.
     */
    public Usuario validateUser(String email, String contrasenya) {
        Usuario result = null;
        List<Usuario> lista = new ArrayList<>();
        EntityManager em = (EntityManager) ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {

            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :u AND u.contrasenya = :p", Usuario.class);
            query.setParameter("u", email);
            query.setParameter("p", contrasenya);
            lista = query.getResultList();
            try {
                result = lista.get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void saveAll(List<Usuario> usuarios) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            for(Usuario u : usuarios){
                em.persist(u);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }




}

