package com.example.Controllers;



import javafx.scene.control.Button;
import com.example.Models.DriverResult;
import com.example.Repositories.DriverResultRepository;

public class DriverResultsController {

    public static Button createDriverResultsButton() {
        Button driverButton = new Button("Ver Resultados de Conductores");
        driverButton.setOnAction(e -> showDriverResults());
        return driverButton;
    }

    private static void showDriverResults() {
        // Código para mostrar resultados de conductores
    }
}
