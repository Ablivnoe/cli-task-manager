import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_NAME = "tasks.json";

    public void saveAll(ArrayList<Task> tasks) {

        try (FileWriter writer = new FileWriter(FILE_NAME, false)) { // 'true' for append mode
            for (Task task : tasks) {
                writer.write(task.getId() + "|" + task.getDescription() + "|" + task.getStatus() + "|" + task.getCreatedAt() + "|" + task.getUpdatedAt() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadAll() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            // Read lines until the end of the file (readLine() returns null)
            while ((line = reader.readLine()) != null) {
                String parts[] = line.split("\\|");

                int id = Integer.parseInt(parts[0]);
                String description = parts[1];
                String status = parts[2];
                LocalDate createdAt = LocalDate.parse(parts[3]);
                LocalDate updatedAt = LocalDate.parse(parts[4]);

                tasks.add(new Task(id, description, status, createdAt, updatedAt));
            }
        } catch (IOException e) {
            // Handle I/O errors
            e.printStackTrace();
        }

        return tasks;
    }
}
