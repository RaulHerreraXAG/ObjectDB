package com.example.gestiondepedidos.pedido;

import com.example.gestiondepedidos.item.Item;
import com.example.gestiondepedidos.usuario.Usuario;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un pedido en la aplicación de gestión de pedidos.
 * Esta clase almacena información sobre el pedido, incluyendo su identificación, código, fecha,
 * identificador de usuario, total
 * y una lista de ítems asociados con el pedido.
 */
@Data
@Entity
public class Pedido implements Serializable {
    /**
     * Identificador único del pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Código identificativo del pedido.
     */

    private String codigo;

    /**
     * Fecha en la que se realizó el pedido.
     */

    private String fecha;

    /**
     * Identificador del usuario que realizó el pedido.
     */
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario usuario;


    /**
     * Total del pedido.
     */

    private Double total;

    /**
     * Lista de ítems asociados con el pedido.
     */

    @OneToMany(mappedBy = "codigo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Item> items = new ArrayList<>();
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", total=" + total +
                ", usuario=" + usuario.getId() +
                '}';
    }

    public static void merge(Pedido origen, Pedido destino) {

        destino.setId(origen.getId());
        destino.setCodigo(origen.getCodigo());
        destino.setUsuario(origen.getUsuario());
        destino.setFecha(origen.getFecha());
        destino.setTotal(origen.getTotal());

    }
}
