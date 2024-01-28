package com.example.gestiondepedidos.item;

import com.example.gestiondepedidos.pedido.Pedido;
import com.example.gestiondepedidos.productos.Producto;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * Clase que representa un ítem en la aplicación de gestión de pedidos.
 * Esta clase almacena información sobre el ítem, incluyendo su identificación, código de pedido al que pertenece, producto asociado y cantidad.
 */
@Data
@Entity
@Table(name = "item")
public class Item implements Serializable {
    /**
     * Identificador único del ítem.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "codigo_pedido",referencedColumnName = "codigo")
    private Pedido codigo;

    @Column(name = "cantidad")
    private Integer cantidad;

    @OneToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", codigo_pedido='" + codigo.getCodigo() + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", producto=" +  +
                '}';
    }
}
