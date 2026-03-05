/**
 * Represents a command that can be executed by the program.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks current task list
     * @param ui ui object used to show output
     * @param storage storage object used for saving if needed
     * @throws EldenException if the command fails
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EldenException;

    /**
     * Returns whether this command should end the program.
     *
     * @return true if the program should exit, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}