public class Main {

    public static void main(String[] args) {
        TaskManager tm = new TaskManager();

        if (args.length == 0) {
            System.out.println("No command provided.");
            return;
        }

        String command = args[0];
        int id;

        switch (command) {
            case "add":
                String description = args[1];
                tm.addTask(description);
                break;
            case "update":
                id = Integer.parseInt(args[1]);
                String newDescription = args[2];
                tm.updateTask(id, newDescription);
                break;
            case "delete":
                id = Integer.parseInt(args[1]);
                tm.deleteTask(id);
                break;
            case "mark-in-progress":
                id = Integer.parseInt(args[1]);
                tm.markInProgress(id);
                break;
            case "mark-done":
                id = Integer.parseInt(args[1]);
                tm.markDone(id);
                break;
            case "list":
                if (args.length == 1) {
                    tm.listTask(null);
                } else {
                    tm.listTask(args[1]);
                }
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }
}