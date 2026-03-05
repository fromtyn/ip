public class UnmarkCommand extends Command {
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EldenException {
        Task task = tasks.unmark(taskNumber);
        ui.showUnmarked(task);
        storage.save(tasks);
    }
}