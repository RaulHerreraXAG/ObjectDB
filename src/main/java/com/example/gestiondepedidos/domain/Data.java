package com.example.gestiondepedidos.domain;

import com.example.gestiondepedidos.productos.Producto;
import com.example.gestiondepedidos.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Usuario> generarUsuario(){
        List<Usuario> listaUsuario = new ArrayList<>();
        listaUsuario.add(new Usuario("Raul","12345","raul@gmail.com",new ArrayList<>()));
        
        return listaUsuario;
    }

    public static List<Producto> generarProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("Smartphone", 299.00, 50));
        listaProductos.add(new Producto("Portátil", 799.00, 30));
        listaProductos.add(new Producto("Auriculares Inalámbricos", 79.00, 100));
        listaProductos.add(new Producto("Televisor LED", 599.00, 20));
        listaProductos.add(new Producto("Tableta", 199.00, 40));
        listaProductos.add(new Producto("Altavoz", 19.00,50));

        return listaProductos;

    }
}
