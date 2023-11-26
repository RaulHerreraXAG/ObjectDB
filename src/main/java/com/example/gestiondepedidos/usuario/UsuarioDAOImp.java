package com.example.gestiondepedidos.usuario;



import com.example.gestiondepedidos.domain.HibernateUtil;
import com.example.gestiondepedidos.domain.DAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;


/**
 * Implementación de la interfaz UsuarioDAO para acceder y gestionar datos de usuarios en una base de datos.
 */
public class UsuarioDAOImp implements DAO<Usuario> {


    @Override
    public ArrayList<Usuario> getAll() {
        var salida = new ArrayList<Usuario>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> q = s.createQuery("from Usuario", Usuario.class);
            salida = (ArrayList<Usuario>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Usuario get(Integer id) {
        var salida = new Usuario();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Usuario.class, id);
        }

        return salida;
    }

    @Override
    public Usuario save(Usuario data) {
        return null;
    }

    @Override
    public void update(Usuario data) {

    }

    @Override
    public void delete(Usuario data) {

    }

    public Usuario validateUser(String email, String password) {
        Usuario result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> q = session.createQuery("from Usuario where email=:u and contrasenya=:p", Usuario.class);
            q.setParameter("u", email);
            q.setParameter("p", password);

            try {
                result = q.getSingleResult();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}

