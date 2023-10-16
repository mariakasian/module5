package com.maria.populate;

import com.maria.Database;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ProjectPopulate {
    public static void projectPopulate (Database db) {
        List<String> projectDatas;
        try {
            String sqlProject = "INSERT INTO project (pname, client_id, start_date, finish_date) VALUES (?, ?, ?, ?)";
            PreparedStatement queryStatement = db.getConnection().prepareStatement(sqlProject);
            projectDatas = Files.readAllLines(Paths.get("datas/project.txt"));

            for (String projectData : projectDatas) {
                String[] data = projectData.split(", ");
                if (data.length == 4) {
                    String name = data[0];
                    int id = Integer.parseInt(data[1]);
                    LocalDate start = LocalDate.parse(data[2]);
                    LocalDate finish = LocalDate.parse(data[3]);

                    queryStatement.setString(1, name);
                    queryStatement.setInt(2, id);
                    queryStatement.setDate(3, java.sql.Date.valueOf(start));
                    queryStatement.setDate(4, java.sql.Date.valueOf(finish));

                    queryStatement.executeUpdate();
                } else {
                    System.out.println("Неправильний формат даних для працівника: " + projectData);
                }
            }
        } catch (SQLException e) {
            System.out.println("Can't create PrepareStatement. Reason: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
