package com.maria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseInitService {
    public static void initDb(Database db) throws IOException {
        String initDbFilename = new Prefs().getString(Prefs.INIT_DB_SQL_FILE_PATH);
        String sql = String.join("\n", Files.readAllLines(Paths.get(initDbFilename)));
        System.out.println("Initiation done: " + db.executeUpdate(sql));
    }
}
