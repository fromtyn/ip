public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EldenException {
        Task task = new Task(description);
        task.setEvent(from, to);
        tasks.add(task);

        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}