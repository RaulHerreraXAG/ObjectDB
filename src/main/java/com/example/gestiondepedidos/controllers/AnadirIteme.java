package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.Main;
import com.example.gestiondepedidos.Sesion;
import com.example.gestiondepedidos.item.Item;
import com.example.gestiondepedidos.item.ItemDAOImp;
import com.example.gestiondepedidos.pedido.Pedido;
import com.example.gestiondepedidos.productos.Producto;
import com.example.gestiondepedidos.productos.ProductoDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AnadirIteme
{
    @javafx.fxml.FXML
    private ComboBox<Producto> ComboBoxP;
    @javafx.fxml.FXML
    private Spinner<Integer> SpinnerC;
    @javafx.fxml.FXML
    private Button btnAceptar;

    private ObservableList<Producto> observableListProductos;
    @javafx.fxml.FXML
    private MenuItem menuItemSesion;
    @javafx.fxml.FXML
    private MenuItem menuItemVolver;
    @javafx.fxml.FXML
    private VBox VBoxAnadir;

    @javafx.fxml.FXML
    public void initialize() {
        //Observable que se usará para gestionar una lista de productos disponibles en un Combo Box.
        observableListProductos = FXCollections.observableArrayList();
        //Se crea un nuevo ProductoDAO.
        ProductoDAOImp productoDAO = new ProductoDAOImp();
        //Se rellena el Observable con todos los productos.
        observableListProductos.setAll(productoDAO.getAll());
        //Se carga el Combo Box con el Observable.
        ComboBoxP.setItems(observableListProductos);
        //Se selecciona el primer producto del Combo Box por defecto.
        ComboBoxP.getSelectionModel().selectFirst();
        //Se establece el Spinner para que solo pueda llegar hasta 100 con paso 1, teniendo como predeterminado el 1.
        SpinnerC.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1));
    }

    @javafx.fxml.FXML
    public void done(ActionEvent actionEvent) throws IOException {
        //Se crea una instancia de Pedido con el pedido actual de la sesión.
        Pedido pedido = Sesion.getPedido();

        //Si el pedido es distinto de nulo se crea un nuevo item para ese pedido  y se retorna a la ventana de DetallesPedidoController.
        if (pedido != null) {

            Item item = new Item();
            item.setCodigo(pedido);
            item.setCantidad(SpinnerC.getValue());
            item.setProducto(ComboBoxP.getSelectionModel().getSelectedItem());

            Sesion.setItem((new ItemDAOImp()).save(item));
            Sesion.setItem(item);

            Main.detalles("ventana-pedido.fxml","Detalles del Pedido");
        }
    }

    @javafx.fxml.FXML
    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        //Se settea el usuario actual a null y vuelve al LoginController.
        Sesion.setUsuario(null);
        Main.login("login.fxml","Login");
    }

    @javafx.fxml.FXML
    public void Volver(ActionEvent actionEvent) throws IOException {
        Main.detalles("ventana-pedido.fxml","Detalles del Pedido");
    }
}