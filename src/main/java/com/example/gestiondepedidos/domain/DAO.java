package com.example.gestiondepedidos.domain;

import com.example.gestiondepedidos.pedido.Pedido;

import java.util.ArrayList;

public interface DAO<T> {
    public ArrayList<T> getAll();
    public T get(Integer id);

    public T save(T data);
    public void update(T data);
    public void delete(T data);
}
