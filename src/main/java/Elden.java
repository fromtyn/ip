import java.util.Scanner;

public class Elden {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Elden");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while(true) {
            String input = in.nextLine();

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.print(i + 1 + ".");
                    System.out.print("[" + tasks[i].getStatusIcon() + "] ");
                    System.out.println(tasks[i].getDescription());
                }
                System.out.println("____________________________________________________________");

                continue;
            }

            String[] parts = input.split(" ");
            if ((parts[0].equals("mark") || parts[0].equals("unmark")) && parts.length == 2) {
                try {
                    int index = Integer.parseInt(parts[1]);
                    if (parts[0].equals("mark")) {
                        tasks[index - 1].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("[X] " + tasks[index - 1].getDescription());
                        System.out.println("____________________________________________________________");
                        continue;
                    }

                    else {
                        tasks[index - 1].markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("[ ] " + tasks[index - 1].getDescription());
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                } catch (Exception e) {

                }

            }


            System.out.println("____________________________________________________________");
            System.out.println("added: " + input);
            System.out.println("____________________________________________________________");
            tasks[taskCount] = new Task(input);
            taskCount++;
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
