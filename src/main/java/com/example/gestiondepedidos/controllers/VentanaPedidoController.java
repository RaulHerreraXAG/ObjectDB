package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.Main;
import com.example.gestiondepedidos.Sesion;
import com.example.gestiondepedidos.item.Item;
import com.example.gestiondepedidos.item.ItemDAOImp;
import com.example.gestiondepedidos.pedido.Pedido;
import com.example.gestiondepedidos.pedido.PedidoDAOImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana de detalles de pedido en la aplicación de gestión de pedidos.
 * Esta clase gestiona la interfaz de usuario para mostrar los detalles de un pedido y permite al usuario cerrar sesión o volver atrás.
 */
public class VentanaPedidoController implements Initializable {
    @javafx.fxml.FXML
    private TableView<Item> tvPedido;
    @javafx.fxml.FXML
    private TableColumn<Item, String> colId;
    @javafx.fxml.FXML
    private TableColumn<Item, String> colCodPedido;
    @javafx.fxml.FXML
    private TableColumn<Item, String> colCantidad;
    @javafx.fxml.FXML
    private TableColumn<Item, String> colProducto;
    private ItemDAOImp itemDAO = new ItemDAOImp();
    private ObservableList<Item> observableList;

    @javafx.fxml.FXML
    private Button btnEliminar;
    @javafx.fxml.FXML
    private Button btnAnadir;
    @javafx.fxml.FXML
    private MenuItem menuItemVolver;
    @javafx.fxml.FXML
    private MenuItem menuItemSesion;
    @javafx.fxml.FXML
    private Button btnDescargar;

    /**
     * Constructor de la clase VentanaPedidoController.
     */
    public VentanaPedidoController(){}

    /**
     * Inicializa la interfaz de usuario y carga los detalles del pedido seleccionado.
     *
     * @param url             Ubicación relativa del archivo FXML.
     * @param resourceBundle  Recursos utilizados para la inicialización.
     */
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.colId.setCellValueFactory((fila) ->{
            String id = String.valueOf(fila.getValue().getId());
            return new SimpleStringProperty(id);
        });
        this.colCodPedido.setCellValueFactory((fila) -> {
            String cPedido = String.valueOf(fila.getValue().getCodigo().getCodigo());
            return new SimpleStringProperty(cPedido);
        });
        this.colCantidad.setCellValueFactory((fila) -> {
            String cCantidad = String.valueOf(fila.getValue().getCantidad());
            return new SimpleStringProperty(cCantidad);
        });
        this.colProducto.setCellValueFactory((fila) -> {
            String cProducto = String.valueOf(fila.getValue().getProducto().getNombre());
            return new SimpleStringProperty(cProducto);
        });

        observableList = FXCollections.observableArrayList();

        Sesion.setPedido((new PedidoDAOImp()).get(Sesion.getPedido().getId()));
        observableList.setAll(Sesion.getPedido().getItems());

        tvPedido.setItems(observableList);
    }

    /**
     * Elimina un item del pedido actual.
     *
     * @param actionEvent Evento de acción que desencadena la eliminación del ítem.
     */
    @javafx.fxml.FXML
    public void eliminar(ActionEvent actionEvent) {
        //Se coge el item seleccionado.
        Item itemSeleccionado = tvPedido.getSelectionModel().getSelectedItem();

        //Confirmación de eliminación mediante un diálogo de confirmación.
        if (itemSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿Deseas borrar el item: " + itemSeleccionado.getId() + ", que contiene el producto: " + itemSeleccionado.getProducto().getNombre() + "?");
            var result = alert.showAndWait().get();

            //Si se confirma la eliminación, se borra el ítem seleccionado de la lista y de la base de datos.
            if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                itemDAO.delete(itemSeleccionado);
                observableList.remove(itemSeleccionado);
            }
        } else {
            //Muestra un mensaje de error o advertencia al usuario si no se ha seleccionado ningún pedido para eliminar.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, selecciona un pedido para eliminar.");
            alert.showAndWait();
        }
    }

    /**
     * Permite añadir un nuevo item al pedido actual.
     *
     * @param actionEvent Evento de acción que desencadena la adición de un ítem.
     * @throws IOException Si hay un problema al cambiar a la ventana de añadir ítems.
     */
    public void anadir(ActionEvent actionEvent) throws IOException {
        // Crea un nuevo ítem.
        var item = new Item();

        // Establece el ítem recién creado en la sesión actual para su posterior uso.
        Sesion.setItem(item);

        //Lleva a la pantalla de AnhadirItemController.
        Main.anadir("anadir-pedido.fxml", "Añadir Item");
    }

    /**
     * Regresa a la ventana principal del usuario.
     *
     * @param actionEvent Evento de acción que desencadena el regreso a la ventana principal del usuario.
     * @throws IOException Si hay un problema al cambiar a la ventana principal del usuario.
     */
    @javafx.fxml.FXML
    public void Volver(ActionEvent actionEvent) throws IOException {
        Main.changeScene("ventana-usuario.fxml","Gestor de Pedidos");
    }

    /**
     * Cierra la sesión del usuario actual y redirige a la ventana de inicio de sesión.
     *
     * @param actionEvent Evento de acción que desencadena el cierre de sesión.
     */
    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        try {
            Main.changeScene("login.fxml","Inicio de Sesión");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @javafx.fxml.FXML
    public void descargar(ActionEvent actionEvent) throws SQLException, JRException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionpedidos", "root", "");
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("nombreEmpresa", "Pedidos Papaya.SL");
        hashMap.put("pedido",Sesion.getPedido().getCodigo());

        JasperPrint jasperPrint = JasperFillManager.fillReport("ReportJasperjrxml.jasper", hashMap, connection);

        // Mostrar el informe en una ventana
        JasperViewer.viewReport(jasperPrint, false);

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("gestor_pedido.pdf"));
        exp.setConfiguration(new SimplePdfExporterConfiguration());
        exp.exportReport();
    }
}
