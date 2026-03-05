import java.util.List;
import java.util.Scanner;

/**
 * Handles the user input and all output shown to the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads one full command line from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the welcome message at the start of the program.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Elden");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Shows the goodbye message before the program exits.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Shows an error message.
     *
     * @param message error message to print
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Shows the message when loading from file fails.
     */
    public void showLoadingError() {
        System.out.println("Warning: failed to load save file. Starting empty.");
    }

    /**
     * Prints all tasks currently in the list.
     *
     * @param tasks current task list
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            tasks.get(i).printInformation();
        }
    }

    /**
     * Shows the message after adding a task.
     *
     * @param task task that was added
     * @param size current number of tasks
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        task.printInformation();
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Shows the message after deleting a task.
     *
     * @param task task that was deleted
     * @param size current number of tasks after deletion
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        task.printInformation();
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Shows the message after marking a task as done.
     *
     * @param task task that was marked
     */
    public void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        task.printInformation();
    }

    /**
     * Shows the message after unmarking a task.
     *
     * @param task task that was unmarked
     */
    public void showUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        task.printInformation();
    }

    /**
     * Shows all tasks whose description matches the keyword.
     *
     * @param matchingTasks list of matching tasks
     */
    public void showMatchingTasks(List<Task> matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.print((i + 1) + ".");
            matchingTasks.get(i).printInformation();
        }
    }
}