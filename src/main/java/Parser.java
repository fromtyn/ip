/**
 * Parses the raw user input into different command objects.
 */
public class Parser {

    /**
     * Parses the full command string and returns the matching command.
     *
     * @param fullCommand full command entered by the user
     * @return the command object created from the input
     * @throws EldenException if the input command is invalid
     */
    public static Command parse(String fullCommand) throws EldenException {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new EldenException("Input cannot be empty.");
        }

        String trimmed = fullCommand.trim();
        String[] parts = trimmed.split("\\s+", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "bye":
            if (!arguments.isEmpty()) {
                throw new EldenException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return new ByeCommand();

        case "list":
            if (!arguments.isEmpty()) {
                throw new EldenException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return new ListCommand();

        case "mark":
            return new MarkCommand(parseTaskNumber(arguments, "mark"));

        case "unmark":
            return new UnmarkCommand(parseTaskNumber(arguments, "unmark"));

        case "delete":
            return new DeleteCommand(parseTaskNumber(arguments, "delete"));

        case "todo":
            if (arguments.isEmpty()) {
                throw new EldenException("The description of a todo cannot be empty.");
            }
            return new TodoCommand(arguments);

        case "deadline":
            return parseDeadline(arguments);

        case "event":
            return parseEvent(arguments);

        case "find":
            if (arguments.isEmpty()) {
                throw new EldenException("The keyword of a find command cannot be empty.");
            }
            return new FindCommand(arguments);

        default:
            throw new EldenException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a task number for commands like mark, unmark and delete.
     *
     * @param arguments argument part after the command word
     * @param commandWord current command word
     * @return the task number entered by the user
     * @throws EldenException if the task number is missing or invalid
     */
    private static int parseTaskNumber(String arguments, String commandWord) throws EldenException {
        if (arguments.isEmpty()) {
            throw new EldenException("Missing task number. Usage: " + commandWord + " <taskNumber>");
        }

        String[] parts = arguments.split("\\s+");
        if (parts.length > 1) {
            throw new EldenException("Too many arguments. Usage: " + commandWord + " <taskNumber>");
        }

        try {
            return Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new EldenException("Task number must be a valid integer.");
        }
    }

    /**
     * Parses a deadline command and creates a DeadlineCommand object.
     *
     * @param arguments argument part after the command word
     * @return the parsed deadline command
     * @throws EldenException if the deadline format is wrong
     */
    private static Command parseDeadline(String arguments) throws EldenException {
        String[] parts = arguments.split("\\s*/by\\s*", 2);
        if (parts.length < 2) {
            throw new EldenException("Deadline format: deadline <description> /by <time>");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty()) {
            throw new EldenException("The description of a deadline cannot be empty.");
        }

        if (by.isEmpty()) {
            throw new EldenException("The /by time of a deadline cannot be empty.");
        }

        return new DeadlineCommand(description, by);
    }

    /**
     * Parses an event command and creates an EventCommand object.
     *
     * @param arguments argument part after the command word
     * @return the parsed event command
     * @throws EldenException if the event format is wrong
     */
    private static Command parseEvent(String arguments) throws EldenException {
        String[] firstSplit = arguments.split("\\s*/from\\s*", 2);
        if (firstSplit.length < 2) {
            throw new EldenException("Event format: event <description> /from <start> /to <end>");
        }

        String description = firstSplit[0].trim();
        String[] secondSplit = firstSplit[1].split("\\s*/to\\s*", 2);

        if (secondSplit.length < 2) {
            throw new EldenException("Event format: event <description> /from <start> /to <end>");
        }

        String from = secondSplit[0].trim();
        String to = secondSplit[1].trim();

        if (description.isEmpty()) {
            throw new EldenException("The description of an event cannot be empty.");
        }

        if (from.isEmpty()) {
            throw new EldenException("The /from time of an event cannot be empty.");
        }

        if (to.isEmpty()) {
            throw new EldenException("The /to time of an event cannot be empty.");
        }

        return new EventCommand(description, from, to);
    }
}