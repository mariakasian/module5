package com.maria.populate;

import com.maria.Database;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProjectWorkerPopulate {
    public static void projectWorkerPopulate (Database db) {
        List<String> projectWorkerDatas;
        try {
            String sqlProjectWorker = "INSERT INTO project_worker (project_id, worker_id) VALUES (?, ?)";
            PreparedStatement queryStatement = db.getConnection().prepareStatement(sqlProjectWorker);
            projectWorkerDatas = Files.readAllLines(Paths.get("datas/project_worker.txt"));

            for (String projectWorkerData : projectWorkerDatas) {
                String[] data = projectWorkerData.split(", ");
                if (data.length == 2) {
                    int project_id = Integer.parseInt(data[0]);
                    int worker_id = Integer.parseInt(data[1]);

                    queryStatement.setInt(1, project_id);
                    queryStatement.setInt(2, worker_id);

                    queryStatement.executeUpdate();
                } else {
                    System.out.println("Неправильний формат даних для працівника: " + projectWorkerData);
                }
            }
        } catch (SQLException e) {
            System.out.println("Can't create PrepareStatement. Reason: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
