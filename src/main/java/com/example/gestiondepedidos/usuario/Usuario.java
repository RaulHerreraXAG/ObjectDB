package com.example.gestiondepedidos.usuario;

import com.example.gestiondepedidos.pedido.Pedido;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {
    /**
     * Identificación única del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del usuario.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Contraseña del usuario.
     */
    @Column(name = "contrasenya")
    private String contrasenya;

    /**
     * Dirección de correo electrónico del usuario.
     */
    @Column(name = "email")
    private String email;

    /**
     * ArrayList que almacena los pedidos asociados con el usuario.
     */
    @OneToMany(mappedBy = "usuario" , fetch = FetchType.EAGER)
    private List<Pedido> pedidos = new ArrayList<>();

    public Usuario(String nombre, String contrasenya, String email, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.contrasenya = contrasenya;
        this.email = email;
        this.pedidos = pedidos;
    }
}
