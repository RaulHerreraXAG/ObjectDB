package com.example.gestiondepedidos.domain;

import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase de utilidad para manejar la configuración de Hibernate y proporcionar la SessionFactory.
 */
@Log
public class HibernateUtil {

    /** Fábrica de sesiones de Hibernate */
    private static SessionFactory sf = null;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            sf = cfg.buildSessionFactory();
            log.info("SessionFactory creada con éxito!");
        } catch (Exception ex) {
            log.severe("Error al crear SessionFactory()");
            ex.printStackTrace();
        }
    }

    /**
     * Obtiene la SessionFactory de Hibernate.
     *
     * @return La SessionFactory para acceder a las sesiones de la base de datos.
     */
    public static SessionFactory getSessionFactory() {
        return sf;
    }
}
