/**
 * Command for marking a task as done.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Creates a MarkCommand object.
     *
     * @param taskNumber task number to mark
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task as done and saves the updated list.
     *
     * @param tasks current task list
     * @param ui ui object used to show output
     * @param storage storage object used to save data
     * @throws EldenException if task number is invalid or saving fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EldenException {
        Task task = tasks.mark(taskNumber);
        ui.showMarked(task);
        storage.save(tasks);
    }
}