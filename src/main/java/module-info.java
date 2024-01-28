module com.example.gestiondepedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.naming;
    requires java.sql;
    requires jasperreports;
    requires javax.persistence;



    opens com.example.gestiondepedidos.usuario;
    opens com.example.gestiondepedidos.pedido;
    opens com.example.gestiondepedidos.productos;
    opens com.example.gestiondepedidos.item;

    opens com.example.gestiondepedidos to javafx.fxml;
    opens com.example.gestiondepedidos.controllers to javafx.fxml;

    exports com.example.gestiondepedidos;
    exports com.example.gestiondepedidos.controllers;


}