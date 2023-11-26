package com.example.gestiondepedidos.controllers;

import com.example.gestiondepedidos.Main;
import com.example.gestiondepedidos.Sesion;
import com.example.gestiondepedidos.usuario.Usuario;
import com.example.gestiondepedidos.usuario.UsuarioDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana de inicio de sesión en la aplicación de gestión de pedidos.
 * Esta clase gestiona la interfaz de usuario para permitir a los usuarios iniciar sesión y acceder a la aplicación.
 */
public class LoginController implements Serializable {

    @FXML
    private TextField txtUser;
    @FXML
    private Button btnIniciar;
    @FXML
    private Label info;
    @FXML
    private PasswordField txtpassword;

    /**
     * Constructor de la clase LoginController.
     */
    public LoginController() {
    }

    /**
     * Inicializa la interfaz de usuario de inicio de sesión.
     *
     * @param url            Ubicación relativa del archivo FXML.
     * @param resourceBundle Recursos utilizados para la inicialización.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Maneja el proceso de inicio de sesión cuando el usuario presiona el botón "Iniciar sesión".
     *
     * @param actionEvent Evento de acción que desencadena el inicio de sesión.
     */
    @FXML
    public void login(ActionEvent actionEvent) {
        String email = txtUser.getText();
        String password = txtpassword.getText();
        if (email.length() < 4 || password.length() < 4) {
            info.setText("Introduce los datos");
            info.setStyle("-fx-background-color:red; -fx-text-fill: white;");

        } else {
            Usuario u = (new UsuarioDAOImp()).validateUser(email, password);
            Sesion.setUsuario(u);
            if (u == null) {
                info.setText("Usuario no encontrado");
                info.setStyle("-fx-background-color:red; -fx-text-fill: white;");
            } else {
                info.setText("Usuario " + email + "(" + password + ") correcto");
                info.setStyle("-fx-background-color:green; -fx-text-fill: white;");
                try {
                    Main.changeScene("ventana-usuario.fxml", "Gestor de Pedidos");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}