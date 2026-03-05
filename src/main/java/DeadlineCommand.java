public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EldenException {
        Task task = new Task(description);
        task.setDeadline(by);
        tasks.add(task);

        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}