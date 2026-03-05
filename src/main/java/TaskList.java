import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public Task delete(int taskNumber) throws EldenException {
        checkTaskNumber(taskNumber);
        return tasks.remove(taskNumber - 1);
    }

    public Task mark(int taskNumber) throws EldenException {
        checkTaskNumber(taskNumber);
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    public Task unmark(int taskNumber) throws EldenException {
        checkTaskNumber(taskNumber);
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        return task;
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    public List<Task> asList() {
        return tasks;
    }

    private void checkTaskNumber(int taskNumber) throws EldenException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new EldenException("Task number out of range.");
        }
    }
}