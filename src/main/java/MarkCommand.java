public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EldenException {
        Task task = tasks.mark(taskNumber);
        ui.showMarked(task);
        storage.save(tasks);
    }
}