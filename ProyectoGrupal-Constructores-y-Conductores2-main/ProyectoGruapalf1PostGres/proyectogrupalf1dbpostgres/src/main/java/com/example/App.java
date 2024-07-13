package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.view.DriverResultsWindow;
import com.example.view.ConstructorResultsWindow;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Resultados: Conductores y Constructores");

        // Establecer el ícono de la ventana
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/imagen/OIG4.jpeg")));

        Button driverButton = new Button("Ver Resultados de Conductores");
        driverButton.setOnAction(e -> new DriverResultsWindow().show());
        driverButton.getStyleClass().add("button");

        Button constructorButton = new Button("Ver Resultados de Constructores");
        constructorButton.setOnAction(e -> new ConstructorResultsWindow().show());
        constructorButton.getStyleClass().add("button");

        HBox hbox = new HBox(20); // Espaciado de 20px entre botones
        hbox.getChildren().addAll(driverButton, constructorButton);
        hbox.setAlignment(Pos.TOP_CENTER); // Centra los botones horizontalmente y arriba
        hbox.getStyleClass().add("hbox");

        VBox vbox = new VBox(20, hbox); // Espaciado de 20px entre el HBox y el borde superior
        vbox.setAlignment(Pos.TOP_CENTER); // Centra los hijos verticalmente
        vbox.getStyleClass().add("vbox");

        StackPane root = new StackPane(vbox); // Utiliza StackPane para superponer VBox y fondo
        root.getStyleClass().add("root");

        Scene scene = new Scene(root, 800, 600); // Asegúrate de que la ventana sea lo suficientemente grande para la imagen
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
