import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading tasks from file and saving tasks back to file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates a Storage object using the given file path.
     *
     * @param filePath path of the save file
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads all tasks from the save file.
     *
     * @return list of tasks loaded from file
     * @throws EldenException if there is an error while reading the file
     */
    public ArrayList<Task> load() throws EldenException {
        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            if (!Files.exists(filePath)) {
                return new ArrayList<>();
            }

            List<String> lines = Files.readAllLines(filePath);
            ArrayList<Task> tasks = new ArrayList<>();

            for (String line : lines) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }

                try {
                    tasks.add(parseTaskLine(trimmed));
                } catch (EldenException e) {
                    // skip bad lines so the whole app does not crash
                }
            }

            return tasks;

        } catch (IOException e) {
            throw new EldenException("Failed to load tasks.");
        }
    }

    /**
     * Saves all tasks to the save file.
     *
     * @param tasks current task list
     * @throws EldenException if there is an error while writing the file
     */
    public void save(TaskList tasks) throws EldenException {
        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            ArrayList<String> lines = new ArrayList<>();
            for (Task t : tasks.asList()) {
                lines.add(convertTaskToLine(t));
            }

            Files.write(filePath, lines);

        } catch (IOException e) {
            throw new EldenException("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Converts a task object into one line for saving.
     *
     * @param t task to be converted
     * @return one line string in save file format
     */
    private String convertTaskToLine(Task t) {
        String type = t.getSaveType();
        String done = t.isDone() ? "1" : "0";
        String desc = t.getDescription();

        if (type.equals("T")) {
            return "T | " + done + " | " + desc;
        } else if (type.equals("D")) {
            return "D | " + done + " | " + desc + " | " + t.getDeadlineTime();
        } else {
            return "E | " + done + " | " + desc + " | " + t.getEventFrom() + " | " + t.getEventTo();
        }
    }

    /**
     * Parses one line from the save file and turns it into a task object.
     *
     * @param line one line read from the save file
     * @return the task created from that line
     * @throws EldenException if the saved line format is corrupted
     */
    private Task parseTaskLine(String line) throws EldenException {
        String[] p = line.split("\\s*\\|\\s*");
        if (p.length < 3) {
            throw new EldenException("Corrupted line");
        }

        String type = p[0].trim();
        String doneStr = p[1].trim();
        String desc = p[2].trim();

        if (!(doneStr.equals("0") || doneStr.equals("1"))) {
            throw new EldenException("Corrupted line");
        }

        if (desc.isEmpty()) {
            throw new EldenException("Corrupted line");
        }

        Task t = new Task(desc);

        if (type.equals("T")) {
            t.setToDos();
        } else if (type.equals("D")) {
            if (p.length < 4 || p[3].trim().isEmpty()) {
                throw new EldenException("Corrupted line");
            }
            t.setDeadline(p[3].trim());
        } else if (type.equals("E")) {
            if (p.length < 5 || p[3].trim().isEmpty() || p[4].trim().isEmpty()) {
                throw new EldenException("Corrupted line");
            }
            t.setEvent(p[3].trim(), p[4].trim());
        } else {
            throw new EldenException("Corrupted line");
        }

        if (doneStr.equals("1")) {
            t.markAsDone();
        }

        return t;
    }
}