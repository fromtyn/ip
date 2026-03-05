/**
 * Main class of the program.
 * It sets up the main objects and starts the command loop.
 */
public class Elden {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an Elden object and loads the saved tasks.
     *
     * @param filePath path of the save file
     */
    public Elden(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (EldenException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program until the user enters the exit command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();

                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (EldenException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point of the program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Elden("data/elden.txt").run();
    }
}