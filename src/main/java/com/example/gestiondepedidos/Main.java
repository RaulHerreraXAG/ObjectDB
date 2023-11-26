package com.example.gestiondepedidos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación de gestión de pedidos.
 * Esta clase inicia y gestiona la interfaz gráfica de la aplicación, cargando diferentes pantallas FXML.
 */
public class Main extends Application {
    /**
     * Representa la ventana principal de la aplicación.
     */
    private static Stage myStage;

    /**
     * Método principal de inicio de la aplicación.
     *
     * @param stage Objeto Stage que representa la ventana principal de la aplicación.
     * @throws IOException Si se produce un error al cargar el archivo FXML de inicio de sesión.
     */
    @Override
    public void start(Stage stage) throws IOException {
        myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cambia la escena actual a una nueva especificada por el archivo FXML y el título.
     *
     * @param fxml  Nombre del archivo FXML para la nueva escena.
     * @param title Título de la ventana para la nueva escena.
     * @throws IOException Si ocurre un error al cargar el archivo FXML para la nueva escena.
     */
    public static void changeScene(String fxml, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/views/" + fxml));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        myStage.setTitle(title);
        myStage.setScene(scene);
        myStage.show();
    }

    /**
     * Muestra los detalles de la escena actual especificada por el archivo FXML y el título.
     *
     * @param fxml  Nombre del archivo FXML para mostrar los detalles.
     * @param title Título de la ventana para mostrar los detalles.
     * @throws IOException Si ocurre un error al cargar el archivo FXML para mostrar los detalles.
     */
    public static void detalles(String fxml, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/views/" + fxml));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        myStage.setTitle(title);
        myStage.setScene(scene);
        myStage.show();
    }

    /**
     * Cambia a la pantalla de inicio de sesión especificada por el archivo FXML y el título.
     *
     * @param fxml  Nombre del archivo FXML para la pantalla de inicio de sesión.
     * @param title Título de la ventana para la pantalla de inicio de sesión.
     * @throws IOException Si ocurre un error al cargar el archivo FXML para la pantalla de inicio de sesión.
     */
    public static void login(String fxml, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/views/" + fxml));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        myStage.setTitle(title);
        myStage.setScene(scene);
        myStage.show();
    }

    /**
     * Añade una nueva escena con los detalles especificados por el archivo FXML y el título.
     *
     * @param fxml  Nombre del archivo FXML para la nueva escena.
     * @param title Título de la ventana para la nueva escena.
     * @throws IOException Si ocurre un error al cargar el archivo FXML para la nueva escena.
     */
    public static void anadir(String fxml, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/views/" + fxml));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        myStage.setTitle(title);
        myStage.setScene(scene);
        myStage.show();
    }

    /**
     * Método principal de ejecución del programa.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
