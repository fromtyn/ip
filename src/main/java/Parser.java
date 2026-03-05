public class Parser {

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