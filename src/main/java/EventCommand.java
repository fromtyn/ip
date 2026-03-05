/**
 * Command for adding an event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Creates an EventCommand object.
     *
     * @param description description of the task
     * @param from start time of the event
     * @param to end time of the event
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds the event task to the list and saves it.
     *
     * @param tasks current task list
     * @param ui ui object used to show output
     * @param storage storage object used to save data
     * @throws EldenException if saving fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EldenException {
        Task task = new Task(description);
        task.setEvent(from, to);
        tasks.add(task);

        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}