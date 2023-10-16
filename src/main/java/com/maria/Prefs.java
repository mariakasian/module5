package com.maria;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Prefs {
    public static final String PREFS_FILENAME = "prefs.json";
    public static final String DB_URL = "dbUrl";
    public static final String INIT_DB_SQL_FILE_PATH = "initDbSql";
    public static final String FIND_MAX_SALARY_WORKER = "findMaxSalaryWorker";
    public static final String FIND_MAX_PROJECTS_CLIENT = "findMaxProjectsClient";
    public static final String FIND_LONGEST_PROJECT = "findLongestProject";
    public static final String FIND_YOUNGEST_ELDEST_WORKERS = "findYoungestEldestWorkers";
    public static final String PRINT_PROJECT_PRICES = "printProjectPrices";
    private final Map<String, Object> prefs;

    public Prefs() throws IOException {
        this(PREFS_FILENAME);
    }
    public Prefs(String filename) throws IOException {
        String json = String.join(
                "\n",
                Files.readAllLines(Paths.get(filename))
        );
        TypeToken<?> typeToken = TypeToken.getParameterized(Map.class, String.class, Object.class);
        prefs = new Gson().fromJson(json, typeToken.getType());
    }

    public Object getPref(String key) {
        return prefs.get(key);
    }
    public String getString(String key) {
        return getPref(key).toString();
    }
}
