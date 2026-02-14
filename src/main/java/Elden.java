import java.util.Scanner;

public class Elden {
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Elden");
        System.out.println("What can I do for you?");
        printLine();

        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
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
                        tasks[i].printInformation();
                    }
                    printLine();

                    continue;
                }

                String[] parts = input.split(" ");
                String command = parts[0];

                if (command.equals("mark") || command.equals("unmark")) {
                    if (parts.length < 2) {
                        throw new EldenException("Missing task number. Usage: " + command + " <taskNumber>");
                    }
                    if (parts.length > 2) {
                        throw new EldenException("Too many arguments. Usage: " + command + " <taskNumber>");
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

                    if (command.equals("mark")) {
                        tasks[index - 1].markAsDone();
                        printLine();
                        System.out.println("Nice! I've marked this task as done:");
                        tasks[index - 1].printInformation();
                        printLine();
                    } else {
                        tasks[index - 1].markAsNotDone();
                        printLine();
                        System.out.println("OK, I've marked this task as not done yet:");
                        tasks[index - 1].printInformation();
                        printLine();
                    }
                    continue;
                }

                if (parts[0].equals("todo")) {
                    if (input.length() <= 4 || input.substring(4).trim().isEmpty()) {
                        throw new EldenException("The description of a todo cannot be empty.");
                    }

                    String task = input.substring(5);
                    tasks[taskCount] = new Task(task);
                    tasks[taskCount].setToDos();

                    printNewTask(taskCount, tasks);
                    taskCount++;
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
                    tasks[taskCount] = new Task(description);
                    tasks[taskCount].setDeadline(time);

                    printNewTask(taskCount, tasks);
                    taskCount++;
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
                    tasks[taskCount] = new Task(description);
                    tasks[taskCount].setEvent(fromTime, toTime);

                    printNewTask(taskCount, tasks);
                    taskCount++;
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
    
    private static void printNewTask(int taskCount, Task[] tasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        tasks[taskCount].printInformation();
        System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
        printLine();
    }
}
