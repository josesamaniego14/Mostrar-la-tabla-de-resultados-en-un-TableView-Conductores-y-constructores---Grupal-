package com.example.Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.Models.Season;

public class SeasonRepository {

    String jdbcURL = "jdbc:postgresql://localhost:5432/Tally";
    String username = "postgres";
    String password = "Admi1234";

    public List<Season> getSeasons() {
        List<Season> seasons = new ArrayList<>();

        try {
            // Establecer la conexión
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);

            // Crear una sentencia
            Statement statement = conn.createStatement();

            // Ejecutar la consulta
            String sql = "SELECT * FROM seasons ORDER BY year;";
            ResultSet rs = statement.executeQuery(sql);

            // Procesar los resultados
            while (rs.next()) {
                int year = rs.getInt("year");

                Season season = new Season(year);
                seasons.add(season);
            }

            // Agregar años faltantes hasta 2023 si no están en la base de datos
            List<Season> additionalSeasons = new ArrayList<>();
            for (int i = 2020; i <= 2023; i++) {
                final int year = i; // Hacer la variable efectivamente final
                boolean yearExists = seasons.stream().anyMatch(season -> season.getYear() == year);
                if (!yearExists) {
                    additionalSeasons.add(new Season(year));
                }
            }
            seasons.addAll(additionalSeasons);

            rs.close();
            statement.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return seasons;
    }
}
