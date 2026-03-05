/**
 * Command for adding a todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Creates a TodoCommand object.
     *
     * @param description description of the task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds the todo task to the list and saves it.
     *
     * @param tasks current task list
     * @param ui ui object used to show output
     * @param storage storage object used to save data
     * @throws EldenException if saving fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EldenException {
        Task task = new Task(description);
        task.setToDos();
        tasks.add(task);

        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}