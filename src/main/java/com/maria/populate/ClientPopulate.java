package com.maria.populate;

import com.maria.Database;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClientPopulate {
    public static void clientPopulate (Database db) {
        List<String> clientNames;
        try {
            String sqlClient = "INSERT INTO client (cname) VALUES (?)";
            PreparedStatement queryStatement = db.getConnection().prepareStatement(sqlClient);
            clientNames = Files.readAllLines(Paths.get("datas/client.txt"));

            for(String name: clientNames) {
                queryStatement.setString(1, name);
                queryStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Can't create PrepareStatement. Reason: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
