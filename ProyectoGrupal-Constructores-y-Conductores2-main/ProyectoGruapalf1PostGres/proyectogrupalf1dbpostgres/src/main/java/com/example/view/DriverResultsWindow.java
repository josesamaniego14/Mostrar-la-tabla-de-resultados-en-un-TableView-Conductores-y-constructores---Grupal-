package com.example.view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import com.example.Models.DriverResult;
import com.example.Models.Season;
import com.example.Repositories.DriverResultRepository;
import com.example.Repositories.SeasonRepository;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class DriverResultsWindow extends Stage {

    private TableView<DriverResult> table;
    private ComboBox<Season> yearComboBox;
    private DriverResultRepository driverResultRepository;
    private SeasonRepository seasonRepository;

    public DriverResultsWindow() {
        setTitle("Resultados Conductores");

        driverResultRepository = new DriverResultRepository();
        seasonRepository = new SeasonRepository();

        // Crear columnas
        TableColumn<DriverResult, String> driverNameColumn = new TableColumn<>("DriverName");
        driverNameColumn.setMinWidth(200);
        driverNameColumn.setCellValueFactory(new PropertyValueFactory<>("driverName"));

        TableColumn<DriverResult, Integer> winsColumn = new TableColumn<>("Wins");
        winsColumn.setMinWidth(100);
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));

        TableColumn<DriverResult, Integer> totalPointsColumn = new TableColumn<>("TotalPoints");
        totalPointsColumn.setMinWidth(100);
        totalPointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));

        TableColumn<DriverResult, Integer> rankColumn = new TableColumn<>("Rank");
        rankColumn.setMinWidth(100);
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));

        // Crear TableView
        table = new TableView<>();
        table.getColumns().addAll(driverNameColumn, winsColumn, totalPointsColumn, rankColumn);
        table.getStyleClass().add("table-view");

        // Crear ComboBox
        yearComboBox = new ComboBox<>();
        List<Season> seasons = seasonRepository.getSeasons();
        ObservableList<Season> observableSeasons = FXCollections.observableArrayList(seasons);
        yearComboBox.setItems(observableSeasons);
        yearComboBox.setConverter(new StringConverter<Season>() {
            @Override
            public String toString(Season season) {
                return season != null ? String.valueOf(season.getYear()) : "";
            }

            @Override
            public Season fromString(String string) {
                return null;
            }
        });
        yearComboBox.setOnAction(e -> {
            if (yearComboBox.getValue() != null) {
                updateTable(yearComboBox.getValue().getYear());
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(yearComboBox, table);
        vbox.getStyleClass().add("vbox");

        Scene scene = new Scene(vbox);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        setScene(scene);
    }

    private void updateTable(int year) {
        List<DriverResult> results = driverResultRepository.getResultByYear(year);
        ObservableList<DriverResult> observableResults = FXCollections.observableArrayList(results);
        table.setItems(observableResults);
    }
}
