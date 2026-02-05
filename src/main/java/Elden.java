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
            if ((parts[0].equals("mark") || parts[0].equals("unmark")) && parts.length == 2) {
                try {
                    int index = Integer.parseInt(parts[1]);
                    if (parts[0].equals("mark")) {
                        tasks[index - 1].markAsDone();
                        printLine();
                        System.out.println(" Nice! I've marked this task as done:");
                        tasks[index - 1].printInformation();
                        printLine();
                        continue;
                    } else {
                        tasks[index - 1].markAsNotDone();
                        printLine();
                        System.out.println(" OK, I've marked this task as not done yet:");
                        tasks[index - 1].printInformation();
                        printLine();
                        continue;
                    }
                } catch (Exception e) {

                }

            }

            if (parts[0].equals("todo")) {
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
                String description = taskParts[0];
                String time = taskParts[1];
                tasks[taskCount] = new Task(description);
                tasks[taskCount].setDeadline(time);

                printNewTask(taskCount, tasks);
                taskCount++;
                continue;
            }

            if (parts[0].equals("event")) {
                String task = input.substring(6);

                String[] taskParts = task.split(" /from ");
                String description = taskParts[0];
                String[] timeParts = taskParts[1].split(" /to ");
                String fromTime = timeParts[0];
                String toTime = timeParts[1];
                tasks[taskCount] = new Task(description);
                tasks[taskCount].setEvent(fromTime, toTime);

                printNewTask(taskCount, tasks);
                taskCount++;
                continue;
            }


            printLine();
            System.out.println("added: " + input);
            printLine();
            tasks[taskCount] = new Task(input);
            taskCount++;
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
