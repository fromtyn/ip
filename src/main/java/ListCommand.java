/**
 * Command for listing all tasks.
 */
public class ListCommand extends Command {
    /**
     * Shows all tasks in the current list.
     *
     * @param tasks current task list
     * @param ui ui object used to show output
     * @param storage storage object
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}