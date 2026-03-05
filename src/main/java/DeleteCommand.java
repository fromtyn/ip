/**
 * Command for deleting a task.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Creates a DeleteCommand object.
     *
     * @param taskNumber task number to delete
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the target task and saves the updated list.
     *
     * @param tasks current task list
     * @param ui ui object used to show output
     * @param storage storage object used to save data
     * @throws EldenException if task number is invalid or saving fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EldenException {
        Task deletedTask = tasks.delete(taskNumber);
        ui.showTaskDeleted(deletedTask, tasks.size());
        storage.save(tasks);
    }
}