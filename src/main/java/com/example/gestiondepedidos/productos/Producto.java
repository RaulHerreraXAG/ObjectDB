package com.example.gestiondepedidos.productos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Clase que representa un producto en la aplicación de gestión de pedidos.
 * Esta clase almacena información sobre el producto, incluyendo su identificación, nombre, precio y cantidad disponible.
 */
@Data
@Entity
@Table (name = "Producto")
public class Producto implements Serializable {
    /**
     * Identificador único del producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del producto.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Precio del producto.
     */
    @Column(name = "precio")
    private Double precio;

    /**
     * Cantidad disponible del producto.
     */
    @Column(name = "cantidad_disponible")
    private Integer cantidad_disponible;

    @Override
    public String toString(){
        return nombre;
    }
}
