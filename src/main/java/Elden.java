import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Elden {
    private static final String SAVE_PATH = "data/elden.txt";

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Elden");
        System.out.println("What can I do for you?");
        printLine();

        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = loadTasks();
        int taskCount = 0;


        while (true) {
            String input = in.nextLine();

            try {
                if (input.trim().isEmpty()) {
                    throw new EldenException("Input cannot be empty.");
                }

                if (input.equals("bye")) {
                    break;
                }

                if (input.equals("list")) {
                    printLine();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.print(i + 1 + ".");
                        tasks.get(i).printInformation();
                    }
                    printLine();

                    continue;
                }

                String[] parts = input.split(" ");

                if (parts[0].equals("mark") || parts[0].equals("unmark")) {
                    if (parts.length < 2) {
                        throw new EldenException("Missing task number. Usage: " + parts[0] + " <taskNumber>");
                    }
                    if (parts.length > 2) {
                        throw new EldenException("Too many arguments. Usage: " + parts[0] + " <taskNumber>");
                    }

                    int index;
                    try {
                        index = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        throw new EldenException("Task number must be a valid integer.");
                    }

                    if (index < 1 || index > taskCount) {
                        throw new EldenException("Task number out of range.");
                    }

                    if (parts[0].equals("mark")) {
                        tasks.get(index - 1).markAsDone();
                        printLine();
                        System.out.println("Nice! I've marked this task as done:");
                        tasks.get(index - 1).printInformation();
                        printLine();
                    } else {
                        tasks.get(index - 1).markAsNotDone();
                        printLine();
                        System.out.println("OK, I've marked this task as not done yet:");
                        tasks.get(index - 1).printInformation();
                        printLine();
                    }

                    saveTasks(tasks);
                    continue;
                }

                if (parts[0].equals("todo")) {
                    if (input.length() <= 4 || input.substring(4).trim().isEmpty()) {
                        throw new EldenException("The description of a todo cannot be empty.");
                    }

                    String task = input.substring(5);
                    Task newTask = new Task(task);
                    newTask.setToDos();
                    tasks.add(newTask);

                    printNewTask(taskCount, tasks);
                    taskCount++;

                    saveTasks(tasks);
                    continue;
                }

                if (parts[0].equals("deadline")) {
                    String task = input.substring(9);

                    String[] taskParts = task.split(" /by ");
                    if (taskParts.length < 2) {
                        throw new EldenException("Deadline format: deadline <description> /by <time>");
                    }

                    String description = taskParts[0];
                    String time = taskParts[1];
                    if (description.isEmpty()) {
                        throw new EldenException("The description of a deadline cannot be empty.");
                    }
                    if (time.isEmpty()) {
                        throw new EldenException("The /by time of a deadline cannot be empty.");
                    }
                    Task newTask = new Task(description);
                    newTask.setDeadline(time);
                    tasks.add(newTask);

                    printNewTask(taskCount, tasks);
                    taskCount++;

                    saveTasks(tasks);
                    continue;
                }

                if (parts[0].equals("event")) {
                    String task = input.substring(6);

                    String[] taskParts = task.split(" /from ");
                    if (taskParts.length < 2) {
                        throw new EldenException("Event format: event <description> /from <start> /to <end>");
                    }
                    String description = taskParts[0];
                    String[] timeParts = taskParts[1].split(" /to ");
                    if (timeParts.length < 2) {
                        throw new EldenException("Event format: event <description> /from <start> /to <end>");
                    }
                    String fromTime = timeParts[0];
                    String toTime = timeParts[1];
                    if (description.isEmpty()) {
                        throw new EldenException("The description of an event cannot be empty.");
                    }
                    if (fromTime.isEmpty()) {
                        throw new EldenException("The /from time of an event cannot be empty.");
                    }
                    if (toTime.isEmpty()) {
                        throw new EldenException("The /to time of an event cannot be empty.");
                    }
                    Task newTask = new Task(description);
                    newTask.setEvent(fromTime, toTime);
                    tasks.add(newTask);

                    printNewTask(taskCount, tasks);
                    taskCount++;

                    saveTasks(tasks);
                    continue;
                }

                if (parts[0].equals("delete")) {
                    if (parts.length < 2) {
                        throw new EldenException("Missing task number. Usage: " + parts[0] + " <taskNumber>");
                    }
                    if (parts.length > 2) {
                        throw new EldenException("Too many arguments. Usage: " + parts[0] + " <taskNumber>");
                    }

                    int index;
                    try {
                        index = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        throw new EldenException("Task number must be a valid integer.");
                    }

                    if (index < 1 || index > taskCount) {
                        throw new EldenException("Task number out of range.");
                    }

                    printLine();
                    System.out.println("Noted. I've removed this task:");
                    tasks.get(index - 1).printInformation();
                    tasks.remove(index - 1);
                    taskCount--;
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    printLine();

                    saveTasks(tasks);
                    continue;
                }

                throw new EldenException("OOPS!!! I'm sorry, but I don't know what that means :-(");

            } catch (EldenException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }

        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
    
    private static void printNewTask(int taskCount, ArrayList<Task> tasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        tasks.get(taskCount).printInformation();
        System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
        printLine();
    }

    private static ArrayList<Task> loadTasks() {
        Path path = Paths.get(SAVE_PATH);
        try {
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            if (!Files.exists(path)) {
                return new ArrayList<>();
            }

            List<String> lines = Files.readAllLines(path);
            ArrayList<Task> tasks = new ArrayList<>();

            for (String line : lines) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) continue;

                try {
                    tasks.add(parseTaskLine(trimmed));
                } catch (EldenException e) {
                    System.out.println("Warning: skipping corrupted line: " + trimmed);
                }
            }
            return tasks;

        } catch (IOException e) {
            System.out.println("Warning: failed to load save file. Starting empty.");
            return new ArrayList<>();
        }
    }

    private static void saveTasks(ArrayList<Task> tasks) throws EldenException {
        Path path = Paths.get(SAVE_PATH);
        try {
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            ArrayList<String> lines = new ArrayList<>();
            for (Task t : tasks) {
                String type = t.getSaveType();
                String done = t.isDone() ? "1" : "0";
                String desc = t.getDescription();

                if (type.equals("T")) {
                    lines.add("T | " + done + " | " + desc);
                } else if (type.equals("D")) {
                    lines.add("D | " + done + " | " + desc + " | " + t.getDeadlineTime());
                } else { // E
                    lines.add("E | " + done + " | " + desc + " | " + t.getEventFrom() + " | " + t.getEventTo());
                }
            }

            Files.write(path, lines);

        } catch (IOException e) {
            throw new EldenException("Failed to save tasks: " + e.getMessage());
        }
    }

    private static Task parseTaskLine(String line) throws EldenException {
        String[] p = line.split("\\s*\\|\\s*");
        if (p.length < 3) throw new EldenException("Corrupted line");

        String type = p[0].trim();
        String doneStr = p[1].trim();
        String desc = p[2].trim();

        if (!(doneStr.equals("0") || doneStr.equals("1"))) throw new EldenException("Corrupted line");
        if (desc.isEmpty()) throw new EldenException("Corrupted line");

        Task t = new Task(desc);
        if (doneStr.equals("1")) t.markAsDone();

        if (type.equals("T")) {
            t.setToDos();
            return t;
        }
        if (type.equals("D")) {
            if (p.length < 4 || p[3].trim().isEmpty()) throw new EldenException("Corrupted line");
            t.setDeadline(p[3].trim());
            return t;
        }
        if (type.equals("E")) {
            if (p.length < 5 || p[3].trim().isEmpty() || p[4].trim().isEmpty()) throw new EldenException("Corrupted line");
            t.setEvent(p[3].trim(), p[4].trim());
            return t;
        }

        throw new EldenException("Corrupted line");
    }

}
