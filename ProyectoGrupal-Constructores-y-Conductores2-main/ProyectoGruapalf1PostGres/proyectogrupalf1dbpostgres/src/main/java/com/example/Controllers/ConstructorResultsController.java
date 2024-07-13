package com.example.Controllers;



import javafx.scene.control.Button;
import com.example.Models.ConstructorResult;
import com.example.Repositories.ConstructorRepository;

public class ConstructorResultsController {

    public static Button createConstructorResultsButton() {
        Button constructorButton = new Button("Ver Resultados de Constructores");
        constructorButton.setOnAction(e -> showConstructorResults());
        return constructorButton;
    }

    private static void showConstructorResults() {
        // Código para mostrar resultados de constructores
    }
}
