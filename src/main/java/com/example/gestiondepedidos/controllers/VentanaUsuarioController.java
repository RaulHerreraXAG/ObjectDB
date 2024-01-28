package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.Main;
import com.example.gestiondepedidos.Sesion;
import com.example.gestiondepedidos.item.Item;
import com.example.gestiondepedidos.pedido.Pedido;
import com.example.gestiondepedidos.pedido.PedidoDAOImp;
import com.example.gestiondepedidos.usuario.UsuarioDAOImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.gestiondepedidos.pedido.PedidoDAOImp.calcularTotalPedido;

/**
 * Controlador de la ventana de usuario en la aplicación de gestión de pedidos.
 * Esta clase gestiona la interfaz de usuario para mostrar los pedidos del usuario y permite al usuario cerrar sesión.
 */
public class VentanaUsuarioController implements Initializable {
    @javafx.fxml.FXML
    private TableView<Pedido> tvPedidos;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> colIdPedido;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> colCodPedido;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> colFecha;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> colUsuario;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> colTotal;
    private ObservableList<Pedido> observableList;
    private PedidoDAOImp pedidoDAO = new PedidoDAOImp();

    private ObservableList<Pedido> pedidosObservable;
    @javafx.fxml.FXML
    private Button btnCerrarSesion1;
    @javafx.fxml.FXML
    private Button btnElminar;
    @javafx.fxml.FXML
    private MenuItem menuItemSesion;

    /**
     * Constructor de la clase VentanaUsuarioController.
     */
    public VentanaUsuarioController(){}

    /**
     * Inicializa la interfaz de usuario y carga los pedidos del usuario actual.
     *
     * @param url             Ubicación relativa del archivo FXML.
     * @param resourceBundle  Recursos utilizados para la inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        colCodPedido.setCellValueFactory( (fila)->{
            String cPedido = fila.getValue().getCodigo();
            return new SimpleStringProperty(cPedido);
        } );
        colUsuario.setCellValueFactory( (fila)->{
            String cUsuario = String.valueOf(fila.getValue().getUsuario().getNombre());
            return new SimpleStringProperty(cUsuario);
        } );
        colFecha.setCellValueFactory( (fila)->{
            String cFecha = fila.getValue().getFecha();
            return new SimpleStringProperty(cFecha);

        } );
        colTotal.setCellValueFactory( (fila)->{
            String cTotal = (fila.getValue().getTotal() + "€");
            return new SimpleStringProperty(cTotal);
        } );
        colIdPedido.setCellValueFactory( (fila)->{
            String id = String.valueOf(fila.getValue().getId());
            return new SimpleStringProperty(id);
        } );

        observableList = FXCollections.observableArrayList();

        Sesion.setUsuario((new UsuarioDAOImp()).get((Sesion.getUsuario().getId())));
        cargarLista();
        tvPedidos.getSelectionModel().selectedItemProperty().addListener((observableValue, pedido, t1) -> {
            Sesion.setPedido(t1);
        });

        tvPedidos.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Pedido selectedPedido = tvPedidos.getSelectionModel().getSelectedItem();
                if (selectedPedido != null) {
                    Sesion.setPedido(selectedPedido);
                    try {
                        Main.detalles("ventana-pedido.fxml","Detalles del Pedido");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


    }

    /**
     * Carga la lista observable con los pedidos del usuario actual y calcula sus totales.
     */
    private void cargarLista() {
        observableList.setAll(Sesion.getUsuario().getPedidos());
        for (Pedido pedido : observableList) {
            Double totalPedido = calcularTotalPedido(pedido);
            pedido.setTotal(totalPedido);

        }
        tvPedidos.setItems(observableList);
    }
    private Double calcularTotalPedido(Pedido pedido) {
        Double total  = 0.0;

        for (Item item : pedido.getItems()){
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        return total;
    }


    /**
     * Calcula el total de un pedido sumando los precios de los productos por la cantidad.
     *
     * @param pedido Pedido del cual se calculará el total.
     * @return El total del pedido.
     */



    /**
     * Método que añade un nuevo pedido.
     *
     * @param actionEvent Evento de acción que desencadena la adición de un nuevo pedido.
     * @throws IOException Si hay un error al intentar cargar la escena de detalles del pedido.
     */
    @javafx.fxml.FXML
    public void anadirpedido(ActionEvent actionEvent) throws IOException {
        Pedido pedidoNuevo = new Pedido();
        //añadimos la fecha de hoy al pedido
        pedidoNuevo.setFecha(LocalDate.now() + "");

        PedidoDAOImp pedidoDAO = new PedidoDAOImp();
        String ultimoCodigo = pedidoDAO.getUltimoCodigo();
        if (ultimoCodigo != null){
            int ultimoNum = Integer.parseInt(ultimoCodigo.substring(4));
            int nuevoNum = ultimoNum + 1;
            String nuevoCodigo = "PED-" + String.format("%03d", nuevoNum);
            //añadimos su codigo de pedido incrementado
            pedidoNuevo.setCodigo(nuevoCodigo);
        }else{
            pedidoNuevo.setCodigo("PED-001");
            System.out.println("codigo null");
        }



        //añadimos el usuario que ha creado ese pedido
        pedidoNuevo.setUsuario(Sesion.getUsuario());

        //añadimos el total a la tabla
        Double totalPedido = calcularTotalPedido(pedidoNuevo);
        pedidoNuevo.setTotal(totalPedido);

        //añadimos el pedido a la tabla
        tvPedidos.getItems().add(pedidoNuevo);
        //guardamos el pedido en la base de datos
        Sesion.setPedido((new PedidoDAOImp()).save(pedidoNuevo));

    }


    /**
     * Método para eliminar un pedido seleccionado.
     *
     * @param actionEvent Evento de acción que desencadena la eliminación del pedido.
     */
    @javafx.fxml.FXML
    public void DeletePedido(ActionEvent actionEvent) {
        //Se coge el pedido seleccionado.
        Pedido pedidoSeleccionado = tvPedidos.getSelectionModel().getSelectedItem();

        //Confirmación de eliminación mediante un diálogo de confirmación.
        if (pedidoSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿Deseas borrar el pedido: " + pedidoSeleccionado.getCodigo() + "?");
            var result = alert.showAndWait().get();

            //Si se confirma la eliminación, se borra el ítem seleccionado de la lista y de la base de datos.
            if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                pedidoDAO.delete(pedidoSeleccionado);
                observableList.remove(pedidoSeleccionado);
            }
        } else {
            //Muestra un mensaje de error o advierte al usuario si no se ha indicado ningún pedido para eliminarlo.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, selecciona un pedido para eliminar.");
            alert.showAndWait();
        }
    }


    /**
     * Método para cerrar la sesión actual y volver a la pantalla de inicio de sesión.
     *
     * @param actionEvent Evento de acción que desencadena el cierre de sesión.
     */
    @javafx.fxml.FXML
    public void cerrarSesion(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        try {
            Main.login("login.fxml","Login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}