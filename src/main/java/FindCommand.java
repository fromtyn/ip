/**
 * Command for finding tasks that match a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand object.
     *
     * @param keyword keyword used for searching
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Shows all tasks that match the keyword.
     *
     * @param tasks current task list
     * @param ui ui object used to show output
     * @param storage storage object
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMatchingTasks(tasks.find(keyword));
    }
}