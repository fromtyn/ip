/**
 * Exit command of the program.
 */
public class ByeCommand extends Command {
    /**
     * Shows the goodbye message.
     *
     * @param tasks current task list
     * @param ui ui object used to show output
     * @param storage storage object
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Returns true because this command ends the program.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}