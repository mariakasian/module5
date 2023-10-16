package com.maria;

import java.io.IOException;
import static com.maria.DatabaseInitService.initDb;
import static com.maria.DatabaseQueryService.*;
import static com.maria.populate.ClientPopulate.clientPopulate;
import static com.maria.populate.ProjectPopulate.projectPopulate;
import static com.maria.populate.ProjectWorkerPopulate.projectWorkerPopulate;
import static com.maria.populate.WorkerPopulate.workerPopulate;

public class App {
    public static void main(String[] args) throws IOException {
        Database db = Database.getInstance();

        initDb(db);
        workerPopulate(db);
        clientPopulate(db);
        projectPopulate(db);
        projectWorkerPopulate(db);

        findMaxSalaryWorker(db);
        findMaxProjectsClient(db);
        findLongestProject(db);
        findYoungestEldestWorkers(db);
        printProjectPrices(db);

        db.connectionClose();
    }
}