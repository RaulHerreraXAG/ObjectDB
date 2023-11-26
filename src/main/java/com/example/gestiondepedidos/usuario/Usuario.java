package com.example.gestiondepedidos.usuario;

import com.example.gestiondepedidos.pedido.Pedido;
import jakarta.persistence.*;
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


}
