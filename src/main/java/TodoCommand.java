public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EldenException {
        Task task = new Task(description);
        task.setToDos();
        tasks.add(task);

        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}