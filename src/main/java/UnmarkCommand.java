/**
 * Command for marking a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Creates an UnmarkCommand object.
     *
     * @param taskNumber task number to unmark
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Unmarks the task and saves the updated list.
     *
     * @param tasks current task list
     * @param ui ui object used to show output
     * @param storage storage object used to save data
     * @throws EldenException if task number is invalid or saving fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EldenException {
        Task task = tasks.unmark(taskNumber);
        ui.showUnmarked(task);
        storage.save(tasks);
    }
}