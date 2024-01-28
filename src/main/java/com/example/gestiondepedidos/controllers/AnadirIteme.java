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

/**
 * Controlador para la funcionalidad de añadir items a un pedido.
 */
public class AnadirIteme {

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

    /**
     * Inicializa el controlador. Rellena el ComboBox con productos disponibles
     * y configura el Spinner para la cantidad de productos.
     */
    @javafx.fxml.FXML
    public void initialize() {
        observableListProductos = FXCollections.observableArrayList();
        ProductoDAOImp productoDAO = new ProductoDAOImp();
        observableListProductos.setAll(productoDAO.getAll());
        ComboBoxP.setItems(observableListProductos);
        ComboBoxP.getSelectionModel().selectFirst();
        SpinnerC.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1));
    }

    /**
     * Método invocado al presionar el botón "Aceptar". Añade un nuevo item al pedido actual.
     *
     * @param actionEvent Evento de acción al presionar el botón.
     * @throws IOException Si hay algún problema al redirigir a la ventana de Detalles del Pedido.
     */
    @javafx.fxml.FXML
    public void done(ActionEvent actionEvent) throws IOException {
        Pedido pedido = Sesion.getPedido();
        if (pedido != null) {
            Item item = new Item();
            item.setCodigo(pedido);
            item.setCantidad(SpinnerC.getValue());
            item.setProducto(ComboBoxP.getSelectionModel().getSelectedItem());
            Sesion.setItem((new ItemDAOImp()).save(item));
            Sesion.setItem(item);
            ProductoDAOImp productoDAO = new ProductoDAOImp();
            System.out.println(productoDAO.getAll());
            Main.detalles("ventana-pedido.fxml", "Detalles del Pedido");
        }
    }

    /**
     * Cierra la sesión actual y redirige a la ventana de inicio de sesión.
     *
     * @param actionEvent Evento de acción al presionar el botón de cerrar sesión.
     * @throws IOException Si hay algún problema al redirigir a la ventana de inicio de sesión.
     */
    @javafx.fxml.FXML
    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        Sesion.setUsuario(null);
        Main.login("login.fxml", "Login");
    }

    /**
     * Redirige a la ventana de Detalles del Pedido al presionar el botón "Volver".
     *
     * @param actionEvent Evento de acción al presionar el botón de volver.
     * @throws IOException Si hay algún problema al redirigir a la ventana de Detalles del Pedido.
     */
    @javafx.fxml.FXML
    public void Volver(ActionEvent actionEvent) throws IOException {
        Main.detalles("ventana-pedido.fxml", "Detalles del Pedido");
    }
}
