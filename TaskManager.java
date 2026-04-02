import java.time.LocalDate;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks;
    private FileManager fileManager;

    public TaskManager() {
        fileManager = new FileManager();
        tasks = fileManager.loadAll();
    }

    private int generateId() {
        if (tasks.isEmpty()) {
            return 1;
        }
        int maxId = 0;
        for (Task task : tasks) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }
        return maxId + 1;
    }

    public void listTask(String status) {
        if (tasks.isEmpty()) {
            System.out.println("The list is empty!");
            return;
        }

        // find the longest description
        int maxDesc = "Description".length();
        for (Task task : tasks) {
            if (task.getDescription().length() > maxDesc) {
                maxDesc = task.getDescription().length();
            }
        }

        int dateWidth = 10; // LocalDate format is always yyyy-MM-dd = 10 chars

        String format = "| %-3s | %-" + maxDesc + "s | %-11s | %-" + dateWidth + "s | %-" + dateWidth + "s |%n";
        String separator = "+-" + "-".repeat(3) + "-+-" + "-".repeat(maxDesc) + "-+-" + "-".repeat(11) + "-+-" + "-".repeat(dateWidth) + "-+-" + "-".repeat(dateWidth) + "-+";

        System.out.println(separator);
        System.out.printf(format, "ID", "Description", "Status", "Created At", "Updated At");
        System.out.println(separator);

        for (Task task : tasks) {
            if (status == null || task.getStatus().equals(status)) {
                System.out.printf(format, task.getId(), task.getDescription(), task.getStatus(), task.getCreatedAt(), task.getUpdatedAt());
            }
        }

        System.out.println(separator);
    }

    public void addTask(String description) {

        int newId = generateId();
        String status = "todo";
        Task task = new Task(newId, description, status, LocalDate.now(), LocalDate.now());
        tasks.add(task);
        fileManager.saveAll(tasks);
        System.out.println("Task added successfully!");

    }

    public void updateTask(int id, String description) {
        if (tasks.isEmpty()) {
            System.out.println("The list is empty!");
        }

        Task task;
        try {
            task = tasks.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task with ID number " + (id) + " doesn't exist.");
            return;
        }

        task.setDescription(description);
        task.setUpdatedAt(LocalDate.now());
        System.out.println("Task updated successfully!");
        fileManager.saveAll(tasks);

    }

    public void markInProgress(int id) {
        if (tasks.isEmpty()) {
            System.out.println("The list is empty!");
        }

        Task task;
        try {
            task = tasks.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task with ID number " + (id) + " doesn't exist.");
            return;
        }

        task.setStatus("in-progress");
        fileManager.saveAll(tasks);
        System.out.println("Task with Id number " + id + " marked in progress!");
    }

    public void markDone(int id) {

        if (tasks.isEmpty()) {
            System.out.println("The list is empty!");
        }

        Task task;
        try {
            task = tasks.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task with ID number " + (id) + " doesn't exist.");
            return;
        }

        task.setStatus("done");
        fileManager.saveAll(tasks);
        System.out.println("Task with ID number " + id + " marked as done!");
    }

    public void deleteTask(int id) {

        if (tasks.isEmpty()) {
            System.out.println("The list is empty!");
        }

        try {
            tasks.remove(id - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task with ID number " + (id) + " doesn't exist.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setId(i + 1);
        }

        fileManager.saveAll(tasks);
        System.out.println("Task with ID number " + id + " removed successfully!");
    }

}