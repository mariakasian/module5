package com.maria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public static List<MaxSalaryWorker> findMaxSalaryWorker(Database db) throws IOException {

        List<MaxSalaryWorker> maxSalaryWorkersList = new ArrayList<>();

        String findMaxSalaryWorkerDbFilename = new Prefs().getString(Prefs.FIND_MAX_SALARY_WORKER);
        String sql = String.join("\n", Files.readAllLines(Paths.get(findMaxSalaryWorkerDbFilename)));

        try (ResultSet resultSet = db.executeResult(sql)) {
            while (resultSet.next()) {
                MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker();
                String name = resultSet.getString("wname");
                int salary = resultSet.getInt("salary");

                maxSalaryWorker.setName(name);
                maxSalaryWorker.setSalary(salary);
                maxSalaryWorkersList.add(maxSalaryWorker);
            }
        } catch (SQLException e) {
            System.out.printf("Exception reason: %s%n", e.getMessage());
            throw new RuntimeException("Can't run query");
        }

        System.out.println("Find workers with maximal salary.");
        System.out.println("+----------------------+----------------+");
        System.out.println("|        Name          |     Salary     |");
        System.out.println("+----------------------+----------------+");
        for (MaxSalaryWorker worker : maxSalaryWorkersList) {
            System.out.printf("| %-20s | %-14d |\n", worker.getName(), worker.getSalary());
        }
        System.out.println("+----------------------+----------------+");

        return maxSalaryWorkersList;
    }

    public static List<MaxProjectCountClient> findMaxProjectsClient(Database db) throws IOException {

        List<MaxProjectCountClient> maxProjectCountClientsList = new ArrayList<>();

        String findMaxProjectsClientDbFilename = new Prefs().getString(Prefs.FIND_MAX_PROJECTS_CLIENT);
        String sql = String.join("\n", Files.readAllLines(Paths.get(findMaxProjectsClientDbFilename)));

        try (ResultSet resultSet = db.executeResult(sql)) {
            while (resultSet.next()) {
                MaxProjectCountClient maxProjectCountClient = new MaxProjectCountClient();
                String name = resultSet.getString("cname");
                int projectCount = resultSet.getInt("project_count");

                maxProjectCountClient.setName(name);
                maxProjectCountClient.setProjectCount(projectCount);
                maxProjectCountClientsList.add(maxProjectCountClient);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Exception reason: %s", e.getMessage()));
            throw new RuntimeException("Can't run query");
        }

        System.out.println("Find clients with maximal quantity of projects.");
        System.out.println("+----------------------+----------------+");
        System.out.println("|        Name          | Project Count  |");
        System.out.println("+----------------------+----------------+");
        for (MaxProjectCountClient client : maxProjectCountClientsList) {
            System.out.printf("| %-20s | %-14d |\n", client.getName(), client.getProjectCount());
        }
        System.out.println("+----------------------+----------------+");

        return maxProjectCountClientsList;
    }

    public static List<LongestProject> findLongestProject(Database db) throws IOException {

        List<LongestProject> longestProjectsList = new ArrayList<>();

        String findLongestProjectDbFilename = new Prefs().getString(Prefs.FIND_LONGEST_PROJECT);
        String sql = String.join("\n", Files.readAllLines(Paths.get(findLongestProjectDbFilename)));

        try (ResultSet resultSet = db.executeResult(sql)) {
            while (resultSet.next()) {
                LongestProject longestProject = new LongestProject();
                String name = resultSet.getString("pname");
                int durationMonths = resultSet.getInt("duration_months");

                longestProject.setName(name);
                longestProject.setDurationMonths(durationMonths);
                longestProjectsList.add(longestProject);
            }
        } catch (SQLException e) {
            System.out.printf("Exception reason: %s%n", e.getMessage());
            throw new RuntimeException("Can't run query");
        }

        System.out.println("Find longest project.");
        System.out.println("+----------------------+------------------+");
        System.out.println("|        Name          | Duration, Months |");
        System.out.println("+----------------------+------------------+");
        for (LongestProject project : longestProjectsList) {
            System.out.printf("| %-20s | %-16d |\n", project.getName(), project.getDurationMonths());
        }
        System.out.println("+----------------------+------------------+");

        return longestProjectsList;
    }

    public static List<YoungestEldestWorkers> findYoungestEldestWorkers(Database db) throws IOException {

        List<YoungestEldestWorkers> youngestEldestWorkersList = new ArrayList<>();

        String findYoungestEldestWorkersDbFilename = new Prefs().getString(Prefs.FIND_YOUNGEST_ELDEST_WORKERS);
        String sql = String.join("\n", Files.readAllLines(Paths.get(findYoungestEldestWorkersDbFilename)));

        try (ResultSet resultSet = db.executeResult(sql)) {
            while (resultSet.next()) {
                YoungestEldestWorkers youngestEldestWorker = new YoungestEldestWorkers();
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                LocalDate birthday = LocalDate.parse(resultSet.getString("birthday"));

                youngestEldestWorker.setType(type);
                youngestEldestWorker.setName(name);
                youngestEldestWorker.setBirthday(birthday);
                youngestEldestWorkersList.add(youngestEldestWorker);
            }
        } catch (SQLException e) {
            System.out.printf("Exception reason: %s%n", e.getMessage());
            throw new RuntimeException("Can't run query");
        }

        System.out.println("Find youngest and eldest worker.");
        System.out.println("+----------------------+----------------------+------------------+");
        System.out.println("+         Type         |        Name          |     Birthday     |");
        System.out.println("+----------------------+----------------------+------------------+");
        for (YoungestEldestWorkers worker : youngestEldestWorkersList) {
            System.out.printf("| %-20s | %-20s | %-16tF |\n", worker.getType(), worker.getName(), worker.getBirthday());
        }
        System.out.println("+----------------------+----------------------+------------------+");

        return youngestEldestWorkersList;
    }

    public static List<ProjectPrices> printProjectPrices(Database db) throws IOException {

        List<ProjectPrices> projectPricesList = new ArrayList<>();

        String printProjectPricesDbFilename = new Prefs().getString(Prefs.PRINT_PROJECT_PRICES);
        String sql = String.join("\n", Files.readAllLines(Paths.get(printProjectPricesDbFilename)));

        try (ResultSet resultSet = db.executeResult(sql)) {
            while (resultSet.next()) {
                ProjectPrices projectPrices = new ProjectPrices();
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");

                projectPrices.setName(name);
                projectPrices.setPrice(price);
                projectPricesList.add(projectPrices);
            }
        } catch (SQLException e) {
            System.out.printf("Exception reason: %s%n", e.getMessage());
            throw new RuntimeException("Can't run query");
        }

        System.out.println("Print projects prices.");
        System.out.println("+----------------------+----------------+");
        System.out.println("|        Name          |      Price     |");
        System.out.println("+----------------------+----------------+");
        for (ProjectPrices project : projectPricesList) {
            System.out.printf("| %-20s | %-14d |\n", project.getName(), project.getPrice());
        }
        System.out.println("+----------------------+----------------+");

        return projectPricesList;
    }
}

class MaxSalaryWorker {
    private String name;
    private int salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

class MaxProjectCountClient {
    private String name;
    private int projectCount;

    public void setName(String name) {
        this.name = name;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    public String getName() {
        return name;
    }

    public int getProjectCount() {
        return projectCount;
    }
}

class LongestProject {
    private String name;
    private int durationMonths;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }
}

class YoungestEldestWorkers {
    private String type;
    private String name;
    private LocalDate birthday;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}

class ProjectPrices {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}