package com.maria.populate;

import com.maria.Database;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class WorkerPopulate {
    public static void workerPopulate (Database db) {
        List<String> workerDatas;
        try {
            String sqlWorker = "INSERT INTO worker (wname, birthday, wlevel, salary) VALUES (?, ?, ?, ?)";
            PreparedStatement queryStatement = db.getConnection().prepareStatement(sqlWorker);
            workerDatas = Files.readAllLines(Paths.get("datas/worker.txt"));

            for (String workerData : workerDatas) {
                String[] data = workerData.split(", ");
                if (data.length == 4) {
                    String name = data[0];
                    LocalDate birthday = LocalDate.parse(data[1]);
                    String level = data[2];
                    int salary = Integer.parseInt(data[3]);

                    queryStatement.setString(1, name);
                    queryStatement.setDate(2, Date.valueOf(birthday));
                    queryStatement.setString(3, level);
                    queryStatement.setInt(4, salary);

                    queryStatement.executeUpdate();
                } else {
                    System.out.println("Неправильний формат даних для працівника: " + workerData);
                }
            }
        } catch (SQLException e) {
            System.out.println("Can't create PrepareStatement. Reason: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
