public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EldenException {
        Task deletedTask = tasks.delete(taskNumber);
        ui.showTaskDeleted(deletedTask, tasks.size());
        storage.save(tasks);
    }
}