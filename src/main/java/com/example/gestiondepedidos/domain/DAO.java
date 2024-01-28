package com.example.gestiondepedidos.domain;

import com.example.gestiondepedidos.item.Item;
import com.example.gestiondepedidos.pedido.Pedido;

import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD básicas (Crear, Leer, Actualizar, Eliminar) en la base de datos.
 *
 * @param <T> Tipo genérico para representar entidades específicas en la base de datos.
 */
public interface DAO<T> {

    /**
     * Obtiene todos los elementos del tipo específico de la base de datos.
     *
     * @return Una lista de todos los elementos del tipo T.
     */
    public List<T> getAll();

    /**
     * Obtiene un elemento específico del tipo T basado en su identificador.
     *
     * @param id Identificador del elemento a buscar.
     * @return El elemento del tipo T encontrado o nulo si no se encuentra.
     */
    public T get(Integer id);



    /**
     * Guarda un nuevo elemento del tipo T en la base de datos.
     *
     * @param data Elemento del tipo T a guardar en la base de datos.
     * @return El mismo elemento del tipo T guardado en la base de datos.
     */
    public T save(T data);

    /**
     * Actualiza un elemento existente del tipo T en la base de datos.
     *
     * @param data Elemento del tipo T a actualizar en la base de datos.
     * @return
     */
    public Pedido update(T data);

    /**
     * Elimina un elemento existente del tipo T de la base de datos.
     *
     * @param data Elemento del tipo T a eliminar de la base de datos.
     * @return
     */
    public Boolean delete(T data);
}
