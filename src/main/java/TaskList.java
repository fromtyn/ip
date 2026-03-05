import java.util.ArrayList;
import java.util.List;

/**
 * Stores the current list of tasks and provides operations on it.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list using an existing ArrayList.
     *
     * @param tasks existing task list
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task into the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the task at the given index.
     *
     * @param index index of the task
     * @return task at that index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return current task list size
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Deletes a task using the user-facing task number.
     *
     * @param taskNumber task number entered by the user
     * @return the deleted task
     * @throws EldenException if the task number is invalid
     */
    public Task delete(int taskNumber) throws EldenException {
        checkTaskNumber(taskNumber);
        return tasks.remove(taskNumber - 1);
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber task number entered by the user
     * @return the updated task
     * @throws EldenException if the task number is invalid
     */
    public Task mark(int taskNumber) throws EldenException {
        checkTaskNumber(taskNumber);
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task as not done.
     *
     * @param taskNumber task number entered by the user
     * @return the updated task
     * @throws EldenException if the task number is invalid
     */
    public Task unmark(int taskNumber) throws EldenException {
        checkTaskNumber(taskNumber);
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        return task;
    }

    /**
     * Finds all tasks whose description contains the given keyword.
     *
     * @param keyword keyword to search for
     * @return list of matching tasks
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    /**
     * Returns the internal task list.
     *
     * @return task list as a List
     */
    public List<Task> asList() {
        return tasks;
    }

    /**
     * Checks whether the given task number is valid.
     *
     * @param taskNumber task number entered by the user
     * @throws EldenException if the task number is out of range
     */
    private void checkTaskNumber(int taskNumber) throws EldenException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new EldenException("Task number out of range.");
        }
    }
}